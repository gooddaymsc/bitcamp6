package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;

public class BookingDeleteHandler extends AbstractBookingHandler {
  BookingPrompt bookingPrompt;

  public BookingDeleteHandler(List <BookingList> allBookingList,BookingPrompt bookingPrompt) {
    super(allBookingList);
    this.bookingPrompt = bookingPrompt;
  }
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[예약 취소]");

    int No = (int) request.getAttribute("bookingNo");
    Booking booking = bookingPrompt.findBookingByNo(No, App.getLoginUser().getId());

    String input = Prompt.inputString("정말 취소하시겠습니까(y/N)");

    if (input.equalsIgnoreCase("y")) {
      if (App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER) { 
        bookingPrompt.findBookingBuyer(
            No, App.getLoginUser().getId(), booking.getCart().getSellerId(), true);

      } else {
        bookingPrompt.findBookingSeller(
            No, App.getLoginUser().getId(), booking.getBuyerId(), true);
      }
      System.out.println("예약을 취소하였습니다.");
      return;
    } else {
      System.out.println("예약 삭제를 취소하였습니다.");
      return;
    }
  }
}







