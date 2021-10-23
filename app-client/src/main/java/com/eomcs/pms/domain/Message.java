package com.eomcs.pms.domain;

import java.sql.Timestamp;

public class Message {
  private int roomNumber;
  private int messageNumber;
  private String content;
  private Timestamp registrationDate;
  private String theOtherId; // 대화상대.
  private String id;


  public int getRoomNumber() {
    return roomNumber;
  }
  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
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
  public Timestamp getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(Timestamp registrationDate) {
    this.registrationDate = registrationDate;
  }
  public String getTheOtherId() {
    return theOtherId;
  }
  public void setTheOtherId(String theOtherId) {
    this.theOtherId = theOtherId;
  }


}