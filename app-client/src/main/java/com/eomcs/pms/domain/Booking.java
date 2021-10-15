package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Booking implements Serializable {
  public Cart cart;
  private int bookingNumber;     //예약번호
  private Date bookingDate;      //예약날짜
  private String bookingTime;    //예약시간
  private Date registeredDate;   //등록일시
  private int bookingStocks;     //예약 상품의 갯수
  private int bookingPrice;      //총액
  private boolean confirm;       //픽업확정여부. 구매상태
  private String theOtherId;     // 상대방 id
  private String id; 
  private int paymentType;       //결제방법
  private int paymentStatus;     //결제상태
  private Date bookingStartTime;  //픽업가능 시작시간
  private Date bookingEndTime;  //픽업가능 종료시간


  public String getTheOtherId() {
    return theOtherId;
  }
  public void setTheOtherId(String theOtherId) {
    this.theOtherId = theOtherId;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getBookingStocks() {
    return bookingStocks;
  }
  public void setBookingStocks(int bookingStocks) {
    this.bookingStocks = bookingStocks;
  }
  public int getBookingPrice() {
    return bookingPrice;
  }
  public void setBookingPrice(int bookingPrice) {
    this.bookingPrice = bookingPrice;
  }
  public Cart getCart() {
    return cart;
  }
  public void setCart(Cart cart) {
    this.cart = cart;
  }
  //
  //  public String getBuyerId() {
  //    return buyerId;
  //  }
  //  public void setBuyerId(String buyerId) {
  //    this.buyerId = buyerId;
  //  }
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
  public boolean isConfirm() {
    return confirm;
  }
  public void setConfirm(boolean confirm) {
    this.confirm = confirm;
  }
  public int getPaymentType() {
    return paymentType;
  }
  public void setPaymentType(int paymentType) {
    this.paymentType = paymentType;
  }
  public int getPaymentStatus() {
    return paymentStatus;
  }
  public void setPaymentStatus(int paymentStatus) {
    this.paymentStatus = paymentStatus;
  }
  public Date getBookingStartTime() {
    return bookingStartTime;
  }
  public void setBookingStartTime(Date bookingStartTime) {
    this.bookingStartTime = bookingStartTime;
  }
  public Date getBookingEndTime() {
    return bookingEndTime;
  }
  public void setBookingEndTime(Date bookingEndTime) {
    this.bookingEndTime = bookingEndTime;
  }



}