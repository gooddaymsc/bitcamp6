package com.eomcs.pms.handler;
import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

public class BookingUpdateHandler implements Command {
  BookingDao bookingDao;
  BookingHandlerHelper bookingHelper;
  SqlSession sqlSession;
  public BookingUpdateHandler(BookingDao bookingDao, BookingHandlerHelper bookingHelper, SqlSession sqlSession) {
    this.bookingDao = bookingDao;
    this.bookingHelper = bookingHelper;
    this.sqlSession = sqlSession;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[예약 변경]");
    int No = (int) request.getAttribute("bookingNo");
    Booking booking = bookingDao.findByNo1(No, nowLoginId);

    Date reservationDate = Prompt.inputDate("픽업날짜 변경 (기존 : " + booking.getBookingDate() + ") : ");
    String reservationTime = bookingHelper.checkTime("픽업시간 변경 (기존 : " + booking.getBookingTime()+ ")", booking.getCart().getStock());

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      booking.setBookingDate(reservationDate);
      booking.setBookingTime(reservationTime);
      bookingDao.update(booking);
      sqlSession.commit();
      System.out.println("픽업 예약을 변경하였습니다.\n");
      return;
    } else {
      System.out.println("픽업 예약 변경을 취소하였습니다.\n");
      return;
    }
  }
}






