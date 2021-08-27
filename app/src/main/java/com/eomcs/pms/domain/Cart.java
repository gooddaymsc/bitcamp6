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
  public int getCartNumber() {
    return cartNumber;
  }
  public void setCartNumber(int cartNumber) {
    this.cartNumber = cartNumber;
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
  public Date getRegistrationDate() {
    return registeredDate;
  }
  public void setRegistrationDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

}