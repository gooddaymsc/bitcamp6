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
    String reservationTime = bookingDao.checkTime("픽업시간 변경 (기존 : " + booking.getBookingTime()+ ")", booking.getCart().getSellerId());
    System.out.println("1. 카드결제 / 2.실시간 계좌이체 / 3.현장결제 ");
    int type = Prompt.inputInt("결제방법을 선택해주세요 (기존 :"+ booking.getPaymentType() +" > ");
    if(type != 1 && type != 2 && type != 3 ) {
      System.out.println("잘못 입력하셨습니다. 결제를 취소합니다. \n");
      return;
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      booking.setBookingDate(reservationDate);
      booking.setBookingTime(reservationTime);
      booking.setPaymentType(type);
      bookingDao.update(booking);
      System.out.println("픽업 예약을 변경하였습니다.");
      return;
    } else {
      System.out.println("픽업 예약 변경을 취소하였습니다.");
      return;
    }
  }
}






