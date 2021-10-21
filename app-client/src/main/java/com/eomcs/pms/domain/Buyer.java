package com.eomcs.pms.domain;

import java.util.ArrayList;
import java.util.List;

public class Buyer {
  private Member member;
  private String zipcode;
  private String address;
  private int point;
  private List<Coupon> coupon = new ArrayList<>();
  private String detailAddress;
  //  private List<Point> pointList = new ArrayList<>();
  //  private List<Coupon> couponList = new ArrayList<>();


  public Member getMember() {
    return member;
  }
  public void setMember(Member member) {
    this.member = member;
  }
  public String getZipcode() {
    return zipcode;
  }
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
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

  public String getDetailAddress() {
    return detailAddress;
  }
  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
  }
}