package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;


public abstract class AbstractBookingHandler implements Command {

  List <BookingList> allBookingList;
  public AbstractBookingHandler(List <BookingList> allBookingList) {
    this.allBookingList = allBookingList;
  }

  protected void putBookingListById(String id, Booking buyerBooking) {
    for (BookingList bookingList : allBookingList) {
      if (bookingList.getId().equals(id)) {
        int totalBookingNumber = bookingList.getTotalBookingNumber();
        buyerBooking.setBookingNumber(totalBookingNumber);
        bookingList.getBooking().add(buyerBooking);
        bookingList.setTotalBookingNumber(++totalBookingNumber);
      }
    }
  }

  protected int checkHour (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1 || num > 24) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      }           
      return num;       
    }
  }

  protected int checkMinute (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 0 || num > 59) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      }           
      return num;       
    }
  }

}





