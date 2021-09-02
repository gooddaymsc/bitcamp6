package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;

public class BookingListHandler extends AbstractBookingHandler{

  public BookingListHandler(List<Booking> bookList, List<Cart> cartList) {
    super(bookList, cartList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0 || App.getLoginUser().getAuthority() == 3) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 목록]");
    for (Booking book : bookList) {
      System.out.printf("%d, %s, %s\n", 
          book.getBookingNumber(), 
          book.getCart().getStock().getProduct().getProductName(),
          book.getReservation());
    }
  }

}






