package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class BookingAddHandler implements Command {

  BookingDao bookingDao;
  StockDao stockDao;
  CartDao cartDao;
  BookingHandlerHelper bookingHelper;
  SqlSession sqlSession;
  public BookingAddHandler(BookingDao bookingDao, StockDao stockDao, CartDao cartDao,
      BookingHandlerHelper bookingHelper, SqlSession sqlSession) {
    this.bookingDao = bookingDao;
    this.stockDao = stockDao;
    this.cartDao = cartDao;
    this.bookingHelper = bookingHelper;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[예약 등록]");

    Booking booking = new Booking();
    int cartNo = (Integer) request.getAttribute("cartNo");

    // 해당 상품명이 장바구니에 하나만 담겨있을때
    Cart cart = cartDao.findByNo(cartNo, nowLoginId);

    // 판매자 id 를 넣었을때 해당되는 Stock 찾기
    int rest = cart.getStock().getStocks() - cart.getCartStocks();
    if (rest<0) {
      System.out.printf("재고가 부족합니다. %d개 이하로 담아주세요.", -rest);
      return;
    }
    booking.setCart(cart);
    booking.setBookingDate(Prompt.inputDate("픽업 예정 날짜: "));
    while(true) {
      try{
        booking.setBookingTime(bookingHelper.checkTime("픽업시간(예 10:30) : ", cart.getStock()));
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
      booking.setBookingStocks(cart.getCartStocks());
      booking.setBookingPrice(cart.getCartPrice());
      booking.setPaymentType(input);
      booking.setPaymentStatus(BookingValidation.paymentStatus(booking));
      // 판매자 재고에서 예약(결제)한 상품 재고 수 빼기 > 서버에서 구현해야함.
      cart.getStock().setStocks(cart.getStock().getStocks() - cart.getCartStocks());

      try {
        // 구매자 장바구니에서 예약(결제)한 상품 빼기
        stockDao.update(cart.getStock());

        bookingDao.insert(booking);
        bookingDao.insertList(booking);

        cartDao.delete(cart);

        System.out.println("픽업예약을 완료하였습니다.");
        sqlSession.commit();

        //    bookingDao.changeBookingUpdate(sellerId, true);
      } catch (Exception e) {
        sqlSession.rollback();
      }
    }
  }
}






