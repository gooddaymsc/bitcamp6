package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class BookingDetailHandler extends AbstractBookingHandler {
  @Override
  public void execute(int i) {}

  public BookingDetailHandler(List<Booking> bookList, List<Cart> cartList) {
    super(bookList, cartList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0 || App.getLoginUser().getAuthority() == 3) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 상세보기]");

    Booking book = findBooking(Prompt.inputString("상품명을 입력해주세요: "));

    if (book == null) {
      System.out.println("예약이 없는 상품입니다.");
      return;
    }

    System.out.printf("예약번호 : %s", book.getBookingNumber());
    System.out.printf("상품명 : %s", book.getCart().getStock().getProduct().getProductName());
    System.out.printf("결제 금액 : %d", book.getCart().getCartPrice());
    System.out.printf("예약시간 : %s\n", book.getReservation());
  }

}






