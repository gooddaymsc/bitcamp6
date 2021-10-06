package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Booking;


public class BookingValidation {


  public static String bookingStatue(Booking booking) {
    String statue ;
    if(booking.isConfirm() == true) {
      statue = "픽업완료";
    }
    else {
      statue = "픽업예정";
    }
    return statue;
  }
}
