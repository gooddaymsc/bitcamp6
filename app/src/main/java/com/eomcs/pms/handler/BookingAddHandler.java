package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class BookingAddHandler extends AbstractBookingHandler {

  int bookingNumber = 1;
  AbstractCartHandler abstractCartHandler;

  public BookingAddHandler(List<Booking> bookingList , AbstractCartHandler abstractCartHandler) {
    super(bookingList);
    this.abstractCartHandler = abstractCartHandler;

    Booking testBooking = new Booking();
    testBooking.setBookingNumber(bookingNumber++);
    testBooking.setCart(abstractCartHandler.cartList.get(0));
    testBooking.setBookingNumber(11);
    testBooking.setBookingDate(Date.valueOf("2021-03-02"));
    testBooking.setBookingHour(19);
    testBooking.setBookingMinute(25);   
    testBooking.setRegisteredDate(new Date(System.currentTimeMillis()));

    bookingList.add(testBooking);

    testBooking = new Booking();
    testBooking.setBookingNumber(bookingNumber++);
    testBooking.setCart(abstractCartHandler.cartList.get(1));
    testBooking.setBookingNumber(22);
    testBooking.setBookingDate(Date.valueOf("2021-01-01"));
    testBooking.setBookingHour(10);
    testBooking.setBookingMinute(30);   
    testBooking.setRegisteredDate(new Date(System.currentTimeMillis()));

    bookingList.add(testBooking);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_PRIVACY) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[예약 등록]");

    Booking booking = new Booking();

    booking.setBookingNumber(bookingNumber++);

    Cart bookingProduct = abstractCartHandler.findByCart(Prompt.inputString("상품명 : "));
    if (bookingProduct == null) {
      System.out.println("해당 상품이 없습니다.");
      return;
    }
    booking.setCart(bookingProduct);
    booking.setBookingDate(Prompt.inputDate("픽업 예정 날짜: "));
    booking.setBookingHour(Prompt.inputInt("픽업시간(시): "));
    booking.setBookingMinute(Prompt.inputInt("픽업시간(분): "));
    booking.setRegisteredDate(new Date(System.currentTimeMillis()));


    bookingList.add(booking);
    System.out.println("픽업예약을 완료하였습니다.");
  }

}






