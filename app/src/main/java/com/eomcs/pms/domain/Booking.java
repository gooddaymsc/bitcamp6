package com.eomcs.pms.domain;

public class Booking {
  private int bookingNumber;
  private String productName;
  private String productType;
  private String countryOrigin;
  private String productPhoto;
  private String productPrice;
  private String reservation;

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
  public String getProductPrice() {
    return productPrice;
  }
  public void setProductPrice(String productPrice) {
    this.productPrice = productPrice;
  }
  public String getReservation() {
    return reservation;
  }
  public void setReservation(String reservation) {
    this.reservation = reservation;
  }



}