package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Booking implements Serializable {
  public Cart cart;
  private String buyerId;
  private int bookingNumber;     //예약번호
  private Date bookingDate;      //예약날짜
  private int bookingHour;      
  private int bookingMinute;
  private String bookingTime;    //예약시간
  private Date registeredDate;   //등록일시


  public Cart getCart() {
    return cart;
  }
  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public String getBuyerId() {
    return buyerId;
  }
  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId;
  }
  public int getBookingNumber() {
    return bookingNumber;
  }
  public void setBookingNumber(int bookingNumber) {
    this.bookingNumber = bookingNumber;
  }
  public Date getBookingDate() {
    return bookingDate;
  }
  public void setBookingDate(Date bookingDate) {
    this.bookingDate = bookingDate;
  }
  public int getBookingHour() {
    return bookingHour;
  }
  public void setBookingHour(int bookingHour) {
    this.bookingHour = bookingHour;
  }
  public int getBookingMinute() {
    return bookingMinute;
  }
  public void setBookingMinute(int bookingMinute) {
    this.bookingMinute = bookingMinute;
  }
  public String getBookingTime() {
    return bookingTime;
  }
  public void setBookingTime(String bookingTime) {
    this.bookingTime = bookingTime;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }




}