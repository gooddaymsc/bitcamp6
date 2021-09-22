package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class BookingAddHandler extends AbstractBookingHandler implements Cloneable{

  CartPrompt cartPrompt;
  StockPrompt stockPrompt;
  BookingPrompt bookingPrompt;
  MemberPrompt memberPrompt;
  public BookingAddHandler(List<BookingList> allBookingList, CartPrompt cartPrompt, 
      StockPrompt stockPrompt, BookingPrompt bookingPrompt, MemberPrompt memberPrompt) { 
    super(allBookingList);
    this.cartPrompt = cartPrompt;
    this.stockPrompt = stockPrompt;
    this.memberPrompt = memberPrompt;
    this.bookingPrompt = bookingPrompt;

  }

  @Override
  public void execute(CommandRequest request) {
    String nowLoginId = App.getLoginUser().getId();
    System.out.println("[예약 등록]");

    Booking booking = new Booking();

    // 해당 상품명이 장바구니에 담겨있는지 확인.
    String productName = (String) request.getAttribute("cart");
    HashMap<Cart, Seller> sellerInfo = cartPrompt.findByCartList(productName);

    String sellerId = "";
    Cart bookingProduct = null;

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
          bookingProduct = entry.getKey();
        }
      }
    } else {
      // 해당 상품명이 장바구니에 하나만 담겨있을때
      bookingProduct = cartPrompt.findByCart(productName);
      sellerId = bookingProduct.getSellerId();
    }

    // 판매자 id 를 넣었을때 해당되는 Stock 찾기
    Stock sellerStock = stockPrompt.findStockById(sellerId, productName);
    if (sellerStock.getStocks() - bookingProduct.getCartStocks()<0) {
      System.out.println("재고가 부족합니다. 구매 수량을 확인해주세요.");
      return;
    }
    booking.setCart(bookingProduct);

    booking.setBookingStocks(bookingProduct.getCartStocks());
    booking.setBookingPrice(bookingProduct.getCartPrice());

    booking.setBookingDate(Prompt.inputDate("픽업 예정 날짜: "));
    booking.setBookingHour(checkHour("픽업시간(시): "));
    booking.setBookingMinute(checkMinute("픽업시간(분): "));
    booking.setRegisteredDate(new Date(System.currentTimeMillis()));
    booking.setBuyerId(nowLoginId);
    // 판매자 재고에서 예약(결제)한 상품 재고 수 빼기
    sellerStock.setStocks(sellerStock.getStocks() - bookingProduct.getCartStocks());
    // 구매자 장바구니에서 예약(결제)한 상품 빼기
    cartPrompt.findCartListById(nowLoginId).getPrivacyCart().remove(bookingProduct);
    // All.allBookingList에 구매자의 Id에 예약내역 추가.
    putBookingListById(nowLoginId, booking);

    Booking booking2 = new Booking();
    booking2.setCart(booking.getCart());

    booking2.setBookingStocks(booking.getBookingStocks());
    booking2.setBookingPrice(booking.getBookingPrice());

    booking2.setBookingDate(booking.getBookingDate());
    booking2.setBookingHour(booking.getBookingHour());
    booking2.setBookingMinute(booking.getBookingMinute());
    booking2.setRegisteredDate(booking.getRegisteredDate());
    booking2.setBuyerId(booking.getBuyerId());
    // All.allBookingList에 판매자의 Id에 예약내역 추가.
    putBookingListById(sellerId, booking2);

    memberPrompt.changeBookingUpdate(sellerId, true);
    System.out.println("픽업예약을 완료하였습니다.");
  }

}






