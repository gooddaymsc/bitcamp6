package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
<<<<<<< HEAD
import com.eomcs.util.Prompt;
=======
import com.eomcs.pms.domain.BookingList;
>>>>>>> ddc34eb72d81c3e55b270f8e3fe6293e6772e13d

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






