package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;

public class BookingUpdateHandler extends AbstractBookingHandler {

  BookingPrompt bookingPrompt;
  public BookingUpdateHandler(List <BookingList> allBookingList, BookingPrompt bookingPrompt) {
    super(allBookingList);
    this.bookingPrompt = bookingPrompt;
  }
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[예약 변경]");

    String bookingName = (String) request.getAttribute("booking");
    Booking booking = bookingPrompt.findByBooking(bookingName);

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






