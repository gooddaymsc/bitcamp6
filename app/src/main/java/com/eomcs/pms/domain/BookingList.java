package com.eomcs.pms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class BookingList implements Serializable {
  private String id; //본인
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
