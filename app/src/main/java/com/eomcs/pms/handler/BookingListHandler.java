package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;

public class BookingListHandler extends AbstractBookingHandler{

  public BookingListHandler(List<Booking> bookingList) {
    super(bookingList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT || App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 목록]");
    for (Booking booking : bookList) {
      System.out.printf("%d, %s, %s\n", 
          booking.getBookingNumber(), 
          booking.getCart().getStock().getProduct().getProductName(),
          booking.getReservation());
    }
  }

}






