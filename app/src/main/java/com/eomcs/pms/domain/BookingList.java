package com.eomcs.pms.domain;

import java.util.ArrayList;
import java.util.List;

public class BookingList {
  private String id;
  private List<Booking> booking = new ArrayList<>();

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public List<Booking> getBooking() {
    return booking;
  }
  public void setBooking(List<Booking> booking) {
    this.booking = booking;
  }

}
