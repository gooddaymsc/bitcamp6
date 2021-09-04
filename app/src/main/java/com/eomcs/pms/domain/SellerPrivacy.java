package com.eomcs.pms.domain;

import java.util.List;

public class SellerPrivacy extends Privacy {

  private String businessNumber;
  private String businessAddress;
  private String businessPlaceNumber;
  private List<Stock> stock;

  public String getBusinessNumber() {
    return businessNumber;
  }
  public void setBusinessNumber(String businessNumber) {
    this.businessNumber = businessNumber;
  }
  public String getBusinessAddress() {
    return businessAddress;
  }
  public void setBusinessAddress(String businessAddress) {
    this.businessAddress = businessAddress;
  }
  public String getBusinessPlaceNumber() {
    return businessPlaceNumber;
  }
  public void setBusinessPlaceNumber(String businessPlaceNumber) {
    this.businessPlaceNumber = businessPlaceNumber;
  }
  public List<Stock> getStock() {
    return stock;
  }
  public void setStock(List<Stock> stock) {
    this.stock = stock;
  }

}