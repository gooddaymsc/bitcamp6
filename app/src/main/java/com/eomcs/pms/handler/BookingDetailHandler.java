package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;

public class BookingDetailHandler extends AbstractBookingHandler {

  BookingPrompt bookingPrompt;
  MemberPrompt memberPrompt;
  public BookingDetailHandler(List <BookingList> allBookingList, BookingPrompt bookingPrompt, 
      MemberPrompt memberPrompt) {
    super(allBookingList);
    this.bookingPrompt = bookingPrompt;
    this.memberPrompt = memberPrompt;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    if (App.getLoginUser().getAuthority()==Menu.ACCESS_BUYER) {
      System.out.println("\n[내 픽업 예약 상세보기]");

      String bookingName = Prompt.inputString("상품명 :");
      if (bookingName == null) { return; }
      Booking booking = bookingPrompt.findByBooking(bookingName);

      if (booking == null) {
        System.out.println("해당 상품의 예약이 없습니다.");
        return;
      }

      String sellerId = booking.getCart().getSellerId();
      System.out.printf( "예약번호: %d\n", booking.getBookingNumber());
      System.out.printf("가게명: %s\n", memberPrompt.findBySellerInfo(sellerId).getBusinessName());
      System.out.printf("상품명: %s\n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("예약일시: %s\n", booking.getRegisteredDate());
      System.out.printf("픽업 예약날짜: %s\n",  booking.getBookingDate());
      System.out.printf("픽업 예약시간: %d시 %d분\n", booking.getBookingHour(), booking.getBookingMinute());
      System.out.println();

      request.setAttribute("booking", bookingName);
      while(true) {
        System.out.println("1. 예약변경 / 2. 예약취소 / 이전(0)");
        int choose = Prompt.inputInt("선택 > ");
        System.out.println();
        switch(choose) {
          case 1 : request.getRequestDispatcher("/booking/update").forward(request); return;
          case 2 : request.getRequestDispatcher("/booking/delete").forward(request); return;
          case 0 : return;
        }
      }

    } else if (App.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {
      System.out.println("\n[고객 예약 상세보기]");

      String bookingName = Prompt.inputString("상품명 :");
      if (bookingName == null) { return; }
      Booking booking = bookingPrompt.findByBooking(bookingName);

      if (booking == null) {
        System.out.println("해당 상품의 예약이 없습니다.");
        return;
      }

      System.out.printf( "예약번호: %d\n", booking.getBookingNumber());
      System.out.printf("예약자: %s\n", booking.getBuyerId());
      System.out.printf("상품명: %s\n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("예약일시: %s\n", booking.getRegisteredDate());
      System.out.printf("픽업 예약날짜: %s\n",  booking.getBookingDate());
      System.out.printf("픽업 예약시간: %d시 %d분\n", booking.getBookingHour(), booking.getBookingMinute());
      System.out.println();

      request.setAttribute("booking", bookingName);
      System.out.println();
      while(true) {
        System.out.println("1. 예약변경 / 이전(0)");
        int choose = Prompt.inputInt("선택 > ");
        System.out.println();
        switch(choose) {
          case 1 : request.getRequestDispatcher("/booking/update").forward(request); return;
          case 0 : return;
        }
      }
    }
  }
}
