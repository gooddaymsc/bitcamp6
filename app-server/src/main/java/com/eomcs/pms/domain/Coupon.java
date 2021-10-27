package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Coupon implements Serializable{
  private int couponNumber;
  private int price;
  private int percent;
  private int minimum;
  private Date expirationDate;
  private boolean use;

  public int getCouponNumber() {
    return couponNumber;
  }
  public void setCouponNumber(int couponNumber) {
    this.couponNumber = couponNumber;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public int getPercent() {
    return percent;
  }
  public void setPercent(int percent) {
    this.percent = percent;
  }
  public int getMinimum() {
    return minimum;
  }
  public void setMinimum(int minimum) {
    this.minimum = minimum;
  }
  public Date getExpirationDate() {
    return expirationDate;
  }
  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }
  public boolean isUse() {
    return use;
  }
  public void setUse(boolean use) {
    this.use = use;
  }

}
