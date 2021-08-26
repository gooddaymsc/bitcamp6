package com.eomcs.pms.domain;

//판매자
public class Member2 extends Member {

  private String businessNo;
  private String businessAddress;
  private String businessTel;

  public String getBusinessNo() {
    return businessNo;
  }
  public void setBusinessNo(String businessNo) {
    this.businessNo = businessNo;
  }
  public String getBusinessAddress() {
    return businessAddress;
  }
  public void setBusinessAddress(String businessAddress) {
    this.businessAddress = businessAddress;
  }
  public String getBusinessTel() {
    return businessTel;
  }
  public void setBusinessTel(String businessTel) {
    this.businessTel = businessTel;
  }


}