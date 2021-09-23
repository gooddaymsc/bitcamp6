package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Message implements Serializable {
  private int messageNumber;
  private String allContent;
  private String content;
  private Date registrationDate;
  private String recipientId; // 대화상대.


  public int getMessageNumber() {
    return messageNumber;
  }
  public void setMessageNumber(int messageNumber) {
    this.messageNumber = messageNumber;
  }

  public String getContent() {
    return content;
  }
  public void setContent(String content) {

    this.content = content;
  }

  public String getAllContent() {
    return allContent;
  }
  public void setAllContent(String content) {
    this.allContent +=  "/" + content;
    //    this.allContent = allContent;
  }
  public Date getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }
  public String getRecipientId() {
    return recipientId;
  }
  public void setRecipientId(String recipientId) {
    this.recipientId = recipientId;
  }





}