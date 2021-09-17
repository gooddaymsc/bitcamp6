package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;

public class BookingUpdateHandler extends AbstractBookingHandler {

  public BookingUpdateHandler(List <BookingList> allBookingList) {
    super(allBookingList);
  }
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[예약 변경]");

    Booking booking = findBooking(Prompt.inputString("예약 변경할 상품명 : "));

    if (booking == null) {
      System.out.println("예약이 없는 상품입니다.");
      return;
    }

    Date reservationDate = Prompt.inputDate("픽업날짜 변경 (기존 : " + booking.getBookingDate() + ") : ");
    int reservationHour = checkHour("픽업시간 변경 (기존 : " + booking.getBookingHour() + "시"+ ") : ");
    int reservationMinute = checkMinute("픽업시간 변경 (기존 : " + booking.getBookingMinute() + "분"+ ") : ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      booking.setBookingDate(reservationDate);
      booking.setBookingHour(reservationHour);
      booking.setBookingMinute(reservationMinute);
      System.out.println("픽업 예약을 변경하였습니다.");
      return;
    } else {
      System.out.println("픽업 예약 변경을 취소하였습니다.");
      return;
    }
  }
}






