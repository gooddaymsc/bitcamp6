package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Point implements Serializable {
  private int pointNo;
  private int point;
  private Date expirationDate;
  private boolean use;
  public int getPointNo() {
    return pointNo;
  }
  public void setPointNo(int pointNo) {
    this.pointNo = pointNo;
  }
  public int getPoint() {
    return point;
  }
  public void setPoint(int point) {
    this.point = point;
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
