package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class BookingAddHandler extends AbstractBookingHandler {

  int bookingNumber = 1;
  public BookingAddHandler(List<Booking> bookList , List<Cart> cartList) {
    super(bookList, cartList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[예약 등록]");

    Booking booking = new Booking();

    booking.setBookingNumber(bookingNumber++);
    Cart cart = findStock(Prompt.inputString("상품명을 입력해주세요: "));

    if (cart == null) {
      System.out.println("해당 상품이 없습니다.");
      return;
    }
    booking.setCart(cart);
    booking.setReservation(Prompt.inputString("예약시간을 입력해주세요: "));
    booking.setRegisteredDate(new Date(System.currentTimeMillis()));


    bookList.add(booking);
    System.out.println("예약등록을 완료하였습니다.");
  }

}






