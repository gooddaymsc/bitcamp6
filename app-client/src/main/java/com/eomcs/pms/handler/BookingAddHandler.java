package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class BookingAddHandler implements Command {
  BookingDao bookingDao;
  StockDao stockDao;
  public BookingAddHandler(BookingDao bookingDao, StockDao stockDao) {
    this.bookingDao = bookingDao;
    this.stockDao = stockDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[예약 등록]");

    Booking booking = new Booking();

    // 해당 상품명이 장바구니에 담겨있는지 확인.
    String productName = (String) request.getAttribute("productName");
    HashMap<Cart, Seller> sellerInfo = bookingDao.findByCartList(productName, nowLoginId);

    String sellerId = "";
    Cart cart = null;

    if (sellerInfo.size()==0) {
      // 해당 상품이 내 장바구니에 담겨있지 않을때
      System.out.println("해당 상품이 장바구니에 담겨있지 않습니다.");
      return;
    } else if (sellerInfo.size() > 1) {
      // 해당 상품이 내 장바구니에 여러개가 담겨있을때(판매자가 다름)
      String StoreName = Prompt.inputString("\n가게 선택 > ");
      for (HashMap.Entry<Cart, Seller> entry : sellerInfo.entrySet()) {
        if (entry.getValue().getBusinessName().equals(StoreName)) {
          sellerId = entry.getKey().getSellerId();
          cart = entry.getKey();
        }
      }
    } else {
      // 해당 상품명이 장바구니에 하나만 담겨있을때
      cart = bookingDao.findByCart(productName, nowLoginId);
      sellerId = cart.getSellerId();
    }

    // 판매자 id 를 넣었을때 해당되는 Stock 찾기
    Stock sellerStock = stockDao.findByNameId(productName, sellerId);
    if (sellerStock.getStocks() - cart.getCartStocks()<0) {
      System.out.println("재고가 부족합니다. 구매 수량을 확인해주세요.");
      return;
    }
    booking.setCart(cart);
    booking.setBookingStocks(cart.getCartStocks());
    booking.setBookingPrice(cart.getCartPrice());
    booking.setBookingDate(Prompt.inputDate("픽업 예정 날짜: "));
    while(true) {
      try{
        booking.setBookingTime(bookingDao.checkTime("픽업시간(예 10:30) : ", sellerId));
        break;
      } catch(Exception e){
        System.out.println("예시대로 입력해주세요.");
      }
    }
    System.out.println("1.카드결제 / 2.실시간 계좌이체 / 3.무통장입금 / 4.휴대폰 결제 / 5.현장결제  ");
    int input = Prompt.inputInt("결제방법을 선택해주세요 > ");
    if(input < 1 && input > 5 ) {
      System.out.println("잘못 입력하셨습니다. 결제를 취소합니다. \n");
      return;
    } else{
      booking.setPaymentType(input);
      booking.setPaymentStatus(BookingValidation.paymentStatus(booking));
      booking.setRegisteredDate(new Date(System.currentTimeMillis()));
      booking.setId(nowLoginId);
      booking.setTheOtherId(sellerId);
      // 판매자 재고에서 예약(결제)한 상품 재고 수 빼기 > 서버에서 구현해야함.
      sellerStock.setStocks(sellerStock.getStocks() - cart.getCartStocks());
      stockDao.update(sellerStock);
      // 구매자 장바구니에서 예약(결제)한 상품 빼기
      bookingDao.deleteCart(nowLoginId, cart);
      // All.allBookingList에 구매자의 Id에 예약내역 추가.
      bookingDao.insert(nowLoginId, booking);

      Booking booking2 = new Booking();
      booking2.setCart(booking.getCart());
      booking2.setBookingStocks(booking.getBookingStocks());
      booking2.setBookingPrice(booking.getBookingPrice());
      booking2.setBookingDate(booking.getBookingDate());
      booking2.setBookingTime(booking.getBookingTime());
      booking2.setRegisteredDate(booking.getRegisteredDate());
      booking2.setConfirm(booking.isConfirm());
      booking2.setPaymentType(booking.getPaymentType());
      booking2.setId(sellerId);
      booking2.setTheOtherId(nowLoginId);

      // All.allBookingList에 판매자의 Id에 예약내역 추가.
      bookingDao.insert(sellerId, booking2);

      //    bookingDao.changeBookingUpdate(sellerId, true);
      System.out.println("픽업예약을 완료하였습니다.");
    }
  }
}






