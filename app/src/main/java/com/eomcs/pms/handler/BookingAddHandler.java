package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class BookingAddHandler extends AbstractBookingHandler {

  //  int bookingNumber = 1;
  CartPrompt cartPrompt;

  public BookingAddHandler(CartPrompt cartPrompt) {
    //    super(bookList);
    this.cartPrompt = cartPrompt;

    //    BookingList testbookingList = findById("aaaa");
    //    Booking testBooking = new Booking();
    //    testBooking.setBookingNumber(testbookingList.getBooking().size());
    //    testBooking.setCart(CartListHandler.cartList.get(0));
    //    testBooking.setBookingNumber(11);
    //    testBooking.setBookingDate(Date.valueOf("2021-03-02"));
    //    testBooking.setBookingHour(19);
    //    testBooking.setBookingMinute(25);   
    //    testBooking.setRegisteredDate(new Date(System.currentTimeMillis()));
    //
    //    App.allBookingList.get(0).getBooking().add(testBooking);
    //
    //    testbookingList = findById("aaa");
    //    testBooking = new Booking();
    //    testBooking.setBookingNumber(testbookingList.getBooking().size());
    //    testBooking.setCart(CartListHandler.cartList.get(0));
    //    testBooking.setBookingNumber(22);
    //    testBooking.setBookingDate(Date.valueOf("2021-01-01"));
    //    testBooking.setBookingHour(10);
    //    testBooking.setBookingMinute(30);   
    //    testBooking.setRegisteredDate(new Date(System.currentTimeMillis()));
    //
    //    App.allBookingList.get(1).getBooking().add(testBooking);

  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_PRIVACY) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[예약 등록]");

    Booking booking = new Booking();

    Cart bookingProduct = cartPrompt.findByCart(Prompt.inputString("상품명 : "));
    if (bookingProduct == null) {
      System.out.println("해당 상품이 없습니다.");
      return;
    }
    booking.setCart(bookingProduct);
    BookingList bookingList = findById(App.getLoginUser().getId());
    booking.setBookingNumber(bookingList.getBooking().size()+1);
    booking.setBookingDate(Prompt.inputDate("픽업 예정 날짜: "));
    booking.setBookingHour(checkHour("픽업시간(시): "));
    booking.setBookingMinute(checkMinute("픽업시간(분): "));
    booking.setRegisteredDate(new Date(System.currentTimeMillis()));

    bookingList.getBooking().add(booking);

    System.out.println("픽업예약을 완료하였습니다.");
  }

}






