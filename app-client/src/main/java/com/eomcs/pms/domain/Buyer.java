package com.eomcs.pms.domain;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Buyer extends Member {
  private String address;
  private int point;
  private List<Coupon> coupon = new ArrayList<>();

  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public int getPoint() {
    return point;
  }
  public void setPoint(int point) {
    this.point = point;
  }
  public List<Coupon> getCoupon() {
    return coupon;
  }
  public void setCoupon(List<Coupon> coupon) {
    this.coupon = coupon;
  }

}