package com.eomcs.pms.handler;
import java.sql.Date;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

public class BookingUpdateHandler implements Command {
  BookingDao bookingDao;
  public BookingUpdateHandler(BookingDao bookingDao) {
    this.bookingDao = bookingDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[예약 변경]");
    int No = (int) request.getAttribute("bookingNo");
    Booking booking = bookingDao.findByNoId(No, nowLoginId);

    Date reservationDate = Prompt.inputDate("픽업날짜 변경 (기존 : " + booking.getBookingDate() + ") : ");
    int reservationHour = bookingDao.checkHours("픽업시간 변경 (기존 : " + booking.getBookingHour() + "시"+ ") : ", booking.getCart().getSellerId());
    int reservationMinute = bookingDao.checkMinutes("픽업시간 변경 (기존 : " + booking.getBookingMinute() + "분"+ ") : ", reservationHour, booking.getCart().getSellerId());

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      booking.setBookingDate(reservationDate);
      booking.setBookingHour(reservationHour);
      booking.setBookingMinute(reservationMinute);
      bookingDao.update(booking);
      System.out.println("픽업 예약을 변경하였습니다.");
      return;
    } else {
      System.out.println("픽업 예약 변경을 취소하였습니다.");
      return;
    }
  }
}






