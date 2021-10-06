package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;

public class BookingDeleteHandler  implements Command {
  BookingDao bookingDao;

  public BookingDeleteHandler(BookingDao bookingDao) {
    this.bookingDao = bookingDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[예약 취소]");

    int no = (Integer) request.getAttribute("bookingNo");
    BookingList bookingList = bookingDao.findAll(ClientApp.getLoginUser().getId());

    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == no) {
        String input = Prompt.inputString("정말 취소하시겠습니까?(y/N) \n");
        if (input.equalsIgnoreCase("y")) {
          bookingDao.delete(booking);
          System.out.println("예약을 취소하였습니다.\n");
          return;
        }
        System.out.println("예약 삭제를 취소하였습니다.\n");
        return;
      }
    }
  }
}







