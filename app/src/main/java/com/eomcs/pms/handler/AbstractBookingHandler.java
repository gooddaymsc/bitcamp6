package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;


public abstract class AbstractBookingHandler implements Command {

  List <BookingList> allBookingList;
  public AbstractBookingHandler(List <BookingList> allBookingList) {
    this.allBookingList = allBookingList;
  }

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
    for (BookingList bookingList : allBookingList) {
      if (bookingList.getId().equals(id)) {
        return bookingList;
      }
    }
    return null;
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





