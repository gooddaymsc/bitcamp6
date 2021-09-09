package com.eomcs.pms.domain;

import java.util.List;

@SuppressWarnings("serial")
public class Seller extends Member {
  private String businessName;
  private String businessNumber;
  private String businessAddress;
  private String businessPlaceNumber;
  //  private String businessHours;
  private List<Stock> stockList;

  public String getBusinessName() {
    return businessName;
  }
  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }
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
  public List<Stock> getStockList() {
    return stockList;
  }
  public void setStockList(List<Stock> stockList) {
    this.stockList = stockList;
  }

}