package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;

public class BookingUpdateHandler extends AbstractBookingHandler {
  StockPrompt stockPrompt;
  BookingPrompt bookingPrompt;
  MemberPrompt memberPrompt;
  public BookingUpdateHandler(List <BookingList> allBookingList, BookingPrompt bookingPrompt, StockPrompt stockPrompt, MemberPrompt memberPrompt) {
    super(allBookingList);
    this.stockPrompt = stockPrompt;
    this.bookingPrompt = bookingPrompt;
    this.memberPrompt = memberPrompt;
  }
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[예약 변경]");
    int No = (int) request.getAttribute("bookingNo");
    Booking booking = bookingPrompt.findBookingByNo(No, App.getLoginUser().getId());

    List<Booking> bookingList = null;
    String sellerId = booking.getCart().getSellerId();
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER) {
      bookingList = bookingPrompt.findBookingBuyer(
          No, App.getLoginUser().getId(), booking.getCart().getSellerId(), false);
    } else {
      bookingList = bookingPrompt.findBookingSeller(
          No, App.getLoginUser().getId(), booking.getBuyerId(), false);
    }

    Date reservationDate = Prompt.inputDate("픽업날짜 변경 (기존 : " + booking.getBookingDate() + ") : ");
    int reservationHour = memberPrompt.checkHours("픽업시간 변경 (기존 : " + booking.getBookingHour() + "시"+ ") : ", sellerId);
    int reservationMinute = memberPrompt.checkMinutes("픽업시간 변경 (기존 : " + booking.getBookingMinute() + "분"+ ") : ", reservationHour, sellerId);

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      bookingList.get(0).setBookingDate(reservationDate);
      bookingList.get(0).setBookingHour(reservationHour);
      bookingList.get(0).setBookingMinute(reservationMinute);

      bookingList.get(1).setBookingDate(reservationDate);
      bookingList.get(1).setBookingHour(reservationHour);
      bookingList.get(1).setBookingMinute(reservationMinute);
      System.out.println("픽업 예약을 변경하였습니다.");
      return;
    } else {
      System.out.println("픽업 예약 변경을 취소하였습니다.");
      return;
    }
  }
}






