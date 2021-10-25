package com.eomcs.pms.domain;

import java.sql.Date;

public class LikeMember {
  private int number;
  private Date registeredDate;

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }


}
