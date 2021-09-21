package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.List;
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

  // 먼저 buyer 기준으로만.
  protected List<Booking> findBookingBuyer (int No, String firstId, String secondId, boolean delete) {
    List<Booking> twoBookingList = new ArrayList<>();
    BookingList bookingList = findBookingList(firstId);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == No) {
        twoBookingList.add(booking);
        if (delete) {
          bookingList.getBooking().remove(booking);
        }
        bookingList = findBookingList(secondId);
        for (Booking booking2 : bookingList.getBooking()) {
          if (booking2.getBuyerId().equals(firstId)
              && booking2.getCart().getStock().getProduct().getProductName().equals(
                  booking.getCart().getStock().getProduct().getProductName())) {
            twoBookingList.add(booking2);
            if (delete) {
              bookingList.getBooking().remove(booking2);
            }
            return twoBookingList;
          }
        }
      }
    }
    return null;
  }

  // 먼저 seller 기준으로만.
  protected List<Booking> findBookingSeller (int No, String firstId, String secondId, boolean delete) {
    List<Booking> twoBookingList = new ArrayList<>();
    BookingList bookingList = findBookingList(firstId);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == No) {
        twoBookingList.add(booking);
        if (delete) {
          bookingList.getBooking().remove(booking);
        }
        bookingList = findBookingList(secondId);
        for (Booking booking2 : bookingList.getBooking()) {
          if (booking2.getCart().getSellerId().equals(firstId)
              && booking2.getCart().getStock().getProduct().getProductName().equals(
                  booking.getCart().getStock().getProduct().getProductName())) {
            twoBookingList.add(booking2);
            if (delete) {
              bookingList.getBooking().remove(booking2);
            }
            return twoBookingList;
          }
        }
      }
    }
    return null;
  }
  protected Booking findBookingByNo (int No, String id) {
    BookingList bookingList = findBookingList(id);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == No) {
        return booking;
      }
    }
    return null;
  }

  protected BookingList findBookingList(String id) {
    for (BookingList bookingList : allBookingList) {
      if (bookingList.getId().equals(id)) {
        return bookingList;
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
