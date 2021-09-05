package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

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

  protected int checkHour (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1 || num > 24) {  
        System.out.println("입력하신 수는 유효하지 않습니다"); 
        continue;
      }           
      return num;       
    }
  }

  protected int checkMinute (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1 || num > 60) {  
        System.out.println("입력하신 수는 유효하지 않습니다"); 
        continue;
      }           
      return num;       
    }
  }

}






