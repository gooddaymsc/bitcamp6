package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.BookingList;

public class BookingPrompt {

  List<BookingList> allBookingList;
  public BookingPrompt(List<BookingList> allBookingList) {
    this.allBookingList = allBookingList;
  }

  public void addBookingListById(String id) {
    BookingList BookingList = new BookingList();
    BookingList.setId(id);
    allBookingList.add(BookingList);
  }

  protected int getBookingIndexById(String id) {
    for (int i=0; i< allBookingList.size(); i++) {
      if (allBookingList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  public void removeBookingListById(String nowLoginid) {
    allBookingList.remove(getBookingIndexById(nowLoginid));
  }
}
