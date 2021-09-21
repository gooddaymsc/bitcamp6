package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Product;

public class BookingPrompt {

  List<BookingList> allBookingList;
  public BookingPrompt(List<BookingList> allBookingList) {
    this.allBookingList = allBookingList;
  }

  public Product findBookingByProduct(String productName, String nowLoginId) {
    int index = getBookingIndexById(nowLoginId);
    List<Booking> bookingList = allBookingList.get(index).getBooking();
    for (Booking booking : bookingList) {
      if (booking.getCart().getStock().getProduct().getProductName().equals(productName)) {
        return booking.getCart().getStock().getProduct();
      }
    }
    return null;
  }

  public BookingList findbookingListById(String id) {
    for (BookingList bookingList : allBookingList) {
      if (bookingList.getId().equals(id)) {
        return bookingList;
      }
    }
    return null;
  }
  protected Booking findByBooking (String ProductName) {
    BookingList bookingList = findbookingListById(App.getLoginUser().getId());
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getCart().getStock().getProduct().getProductName().equals(ProductName)) {
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
  protected Booking findByNo(int no) {
    BookingList bookingList = findById(App.getLoginUser().getId());
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == no) {
        return booking;
      }
    }
    return null;
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
