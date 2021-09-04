package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Booking;

public abstract class AbstractBookingHandler implements Command {

  List<Booking> bookingList;

  public AbstractBookingHandler(List<Booking> bookingList) {
    this.bookingList = bookingList;
  }

  protected Booking findBooking(String name) {
    for (Booking booking : bookingList) {
      if (booking.getCart().getStock().getProduct().getProductName().equals(name)) {
        return booking;
      }
    }
    return null;
  }
}






