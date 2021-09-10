package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class BookingDeleteHandler extends AbstractBookingHandler {

  public BookingDeleteHandler(List <Seller> sellerList, List <BookingList> allBookingList) {
    super(sellerList, allBookingList);
  }
  @Override
  public void execute() {
    System.out.println("[예약 취소]");

    String productName = Prompt.inputString("예약 취소할 상품명 : ");

    Booking booking = findBooking(productName);

    if (booking == null) {
      System.out.println("예약이 없는 상품입니다.");
      return;
    }

    String input = Prompt.inputString("정말 취소하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      BookingList bookingList = findById(App.getLoginUser().getId());

      bookingList.getBooking().remove(booking);
      System.out.println("예약을 취소하였습니다.");
      return;
    } else {
      System.out.println("예약 삭제를 취소하였습니다.");
      return;
    }
  }
}







