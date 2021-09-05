package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;

public abstract class AbstractBookingHandler implements Command {

  protected Booking findBooking(String bookingName) {
    BookingList bookingList = findById(App.getLoginUser().getId());
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getCart().getStock().getProduct().getProductName().equals(bookingName)) {
        return booking;
      }
    }
    return null;
  }

  protected BookingList findById(String id) {
    for (BookingList bookingList : App.allBookingList) {
      if (bookingList.getId().equals(id)) {
        return bookingList;
      }
    }
    return null;
  }
}






