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
    String payment = "";
    switch(booking.getPaymentType()) {
      case 1: payment = "카드결제"; break;
      case 2: payment = "실시간 계좌이체"; break;
      case 3: payment = "무통장입금"; break;
      case 4: payment = "휴대폰결제"; break;
      case 5: payment = "현장결제"; break;
    }
    return payment;
  }

  public static int paymentStatus(Booking booking) {
    int type = 0;
    if(booking.getPaymentType() >= 1 && booking.getPaymentType() <=4) {
      type = 2;
      //System.out.println("결제완료");
    } else if(booking.getPaymentType() == 5) {
      type = 1;
      //System.out.println("미결제");
    }
    return type;
  } 

  public static String printPaymentStatus(Booking booking) {
    String status = null;
    if(booking.getPaymentStatus() == 2) {
      status = "결제완료";
    }
    if(booking.getPaymentStatus() == 1) {
      status = "미결제";
    }
    return status;
  }

}
