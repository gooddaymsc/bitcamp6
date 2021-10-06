package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

public class BookingDeleteHandler  implements Command {
  BookingDao bookingDao;

  public BookingDeleteHandler(BookingDao bookingDao) {
    this.bookingDao = bookingDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[예약 취소]");

    int no = (int) request.getAttribute("bookingNo");
    Booking booking = bookingDao.findByNoId(no, ClientApp.getLoginUser().getId());

    String input = Prompt.inputString("정말 취소하시겠습니까(y/N)");

    if (input.equalsIgnoreCase("y")) {
      if (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_BUYER) {
        bookingDao.findBookingBuyer(
            no, ClientApp.getLoginUser().getId(), booking.getCart().getSellerId(), true);

      } else {
        bookingDao.findBookingSeller(
            no, ClientApp.getLoginUser().getId(), booking.getCart().getId(), true);
      }
      System.out.println("예약을 취소하였습니다.");
      return;
    } else {
      System.out.println("예약 삭제를 취소하였습니다.");
      return;
    }
  }
}







