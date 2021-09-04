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
    System.out.println("[내 픽업 예약 목록]");
    System.out.println();
    for(Booking booking : bookingList ) {
      System.out.printf("예약번호 : %s \n", booking.getBookingNumber());
      System.out.printf("예약일시 : %s \n", booking.getRegisteredDate());
      System.out.printf("상품명 : %s \n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("결제 금액 : %d \n", booking.getCart().getCartPrice());
      System.out.printf("픽업 예약날짜 : %s\n", booking.getBookingDate());
      System.out.printf("픽업 예약시간: %d시 %d분 \n", booking.getBookingHour(), booking.getBookingMinute());
      System.out.println("==================================");
    }
  }

}






