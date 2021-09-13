package com.eomcs.pms.domain;

@SuppressWarnings("serial")
public class Buyer extends Member {
  private String address;

  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
}