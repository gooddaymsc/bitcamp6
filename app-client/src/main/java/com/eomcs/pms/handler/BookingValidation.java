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

  public static String paymentType(Booking booking) {
    String payment = null;
    switch(booking.getPaymentType()) {
      case 1: payment = "카드결제"; break;
      case 2: payment = "실시간 계좌이체"; break;
      case 3: payment = "현장결제"; break;
    }
    return payment;
  }
}
