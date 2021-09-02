package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

public class BookingUpdateHandler extends AbstractBookingHandler {

  public BookingUpdateHandler(List<Booking> bookList) {
    super(bookList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0 || App.getLoginUser().getAuthority() == 3) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 변경]");

    Booking booking = findBooking(Prompt.inputString("상품명을 입력해주세요: "));

    if (booking == null) {
      System.out.println("예약이 없는 상품입니다.");
      return;
    }

    String resevation = Prompt.inputString("예약시간 변경 (기존 : " + booking.getReservation() + ") ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      booking.setReservation(resevation);
      System.out.println("예약을 변경하였습니다.");
      return;
    } else {
      System.out.println("예약 변경을 취소하였습니다.");
      return;
    }
  }
}






