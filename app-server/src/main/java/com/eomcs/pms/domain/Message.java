package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Message implements Serializable {
  private int messageNumber;
  private String allContent = "";
  //  private String content;
  private Date registrationDate;
  private String theOtherId; // 대화상대.
  private String id;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public int getMessageNumber() {
    return messageNumber;
  }
  public void setMessageNumber(int messageNumber) {
    this.messageNumber = messageNumber;
  }
  public String getAllContent() {
    return allContent;
  }
  public void setAllContent(String allContent) {
    this.allContent = allContent;
  }
  public Date getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }
  public String getTheOtherId() {
    return theOtherId;
  }
  public void setTheOtherId(String theOtherId) {
    this.theOtherId = theOtherId;
  }


}