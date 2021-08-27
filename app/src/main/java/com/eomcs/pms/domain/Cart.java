package com.eomcs.pms.domain;

import java.sql.Date;

public class Cart {
  private int cartNumber;
  private String productName;
  private String productType;
  private String countryOrigin;
  private String productPhoto;
  private String price;
  private Date registeredDate;

  public int getNo() {
    return cartNumber;
  }
  public void setNo(int no) {
    this.cartNumber = no;
  }
  public String getName() {
    return productName;
  }
  public void setName(String name) {
    this.productName = name;
  }
  public String getKind() {
    return productType;
  }
  public void setKind(String kind) {
    this.productType = kind;
  }
  public String getMadeIn() {
    return countryOrigin;
  }
  public void setMadeIn(String madeIn) {
    this.countryOrigin = madeIn;
  }
  public String getPhoto() {
    return productPhoto;
  }
  public void setPhoto(String photo) {
    this.productPhoto = photo;
  }
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}