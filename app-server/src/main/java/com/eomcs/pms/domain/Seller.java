package com.eomcs.pms.domain;

@SuppressWarnings("serial")
public class Seller extends Member {
  private String businessName;
  private String businessNumber;
  private String businessAddress;
  private String businessPlaceNumber;
  //  private int businessOpeningHours;
  //  private int businessOpeningMinutes;
  //  private int businessClosingHours;
  //  private int businessClosingMinutes;
  private String businessOpeningTime;
  private String businessClosingTime;


  //  public int getBusinessOpeningHours() {
  //    return businessOpeningHours;
  //  }
  //  public void setBusinessOpeningHours(int businessOpeningHours) {
  //    this.businessOpeningHours = businessOpeningHours;
  //  }
  //  public int getBusinessOpeningMinutes() {
  //    return businessOpeningMinutes;
  //  }
  //  public void setBusinessOpeningMinutes(int businessOpeningMinutes) {
  //    this.businessOpeningMinutes = businessOpeningMinutes;
  //  }
  //  public int getBusinessClosingHours() {
  //    return businessClosingHours;
  //  }
  //  public void setBusinessClosingHours(int businessClosingHours) {
  //    this.businessClosingHours = businessClosingHours;
  //  }
  //  public int getBusinessClosingMinutes() {
  //    return businessClosingMinutes;
  //  }
  //  public void setBusinessClosingMinutes(int businessClosingMinutes) {
  //    this.businessClosingMinutes = businessClosingMinutes;
  //  }
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
  public String getBusinessOpeningTime() {
    return businessOpeningTime;
  }
  public void setBusinessOpeningTime(String businessOpeningTime) {
    this.businessOpeningTime = businessOpeningTime;
  }
  public String getBusinessClosingTime() {
    return businessClosingTime;
  }
  public void setBusinessClosingTime(String businessClosingTime) {
    this.businessClosingTime = businessClosingTime;
  }


}