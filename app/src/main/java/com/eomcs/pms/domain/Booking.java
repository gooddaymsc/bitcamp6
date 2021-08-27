package com.eomcs.pms.domain;

import java.sql.Date;

public class Booking {
  private int bookingNumber;
  private String productName;
  private String productType;
  private String countryOrigin;
  private String productPhoto;
  private String price;
  private String reservation;
  private Date registeredDate;

  public int getBookingNumber() {
    return bookingNumber;
  }
  public void setBookingNumber(int bookingNumber) {
    this.bookingNumber = bookingNumber;
  }
  public String getProductName() {
    return productName;
  }
  public void setProductName(String productName) {
    this.productName = productName;
  }
  public String getProductType() {
    return productType;
  }
  public void setProductType(String productType) {
    this.productType = productType;
  }
  public String getCountryOrigin() {
    return countryOrigin;
  }
  public void setCountryOrigin(String countryOrigin) {
    this.countryOrigin = countryOrigin;
  }
  public String getProductPhoto() {
    return productPhoto;
  }
  public void setProductPhoto(String productPhoto) {
    this.productPhoto = productPhoto;
  }
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
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