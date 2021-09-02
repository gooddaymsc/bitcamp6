package com.eomcs.pms.domain;

import java.sql.Date;

public class Booking {
  public Cart cart;
  private int bookingNumber;
  private String reservation;
  private Date registeredDate;


  public Cart getCart() {
    return cart;
  }
  public int getBookingNumber() {
    return bookingNumber;
  }
  public void setBookingNumber(int bookingNumber) {
    this.bookingNumber = bookingNumber;
  }
  public String getReservation() {
    return reservation;
  }
  public void setReservation(String reservation) {
    this.reservation = reservation;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }




}