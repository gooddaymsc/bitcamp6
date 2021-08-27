package com.eomcs.pms.domain;

public class SellerPrivacy extends Privacy {

  private String businessNumber;
  private String businessAddress;
  private String businessPlaceNumber;

  public String getBusinessNo() {
    return businessNumber;
  }
  public void setBusinessNo(String businessNo) {
    this.businessNumber = businessNo;
  }
  public String getBusinessAddress() {
    return businessAddress;
  }
  public void setBusinessAddress(String businessAddress) {
    this.businessAddress = businessAddress;
  }
  public String getBusinessTel() {
    return businessPlaceNumber;
  }
  public void setBusinessTel(String businessTel) {
    this.businessPlaceNumber = businessTel;
  }
}
