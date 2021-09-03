package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

public class BookingDeleteHandler extends AbstractBookingHandler {

  List<Booking> booktList;

  public BookingDeleteHandler(List<Booking> bookList) {
    super(bookList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT || App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
      System.out.println("권한이 없습니다.구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 취소]");

    Booking booking = findBooking(Prompt.inputString("예약 취소할 상품명 : "));

    if (booking == null) {
      System.out.println("예약이 없는 상품입니다.");
      return;
    }

    String input = Prompt.inputString("정말 취소하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      booktList.remove(booking);
      System.out.println("예약을 취소하였습니다.");
      return;
    } else {
      System.out.println("예약 삭제를 취소하였습니다.");
      return;
    }
  }
}






