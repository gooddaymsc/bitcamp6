package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Booking;

public abstract class AbstractBookingHandler implements Command {

  List<Booking> bookList;

  public AbstractBookingHandler(List<Booking> bookList) {
    this.bookList = bookList;
  }

  protected Booking findBooking(String name) {
    for (Booking book : bookList) {
      if (book.getCart().getStock().getProduct().getProductName().equals(name)) {
        return book;
      }
    }
    return null;
  }
}






