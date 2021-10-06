package com.eomcs.pms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MessageList implements Serializable {
  private String id; // nowLoginId
  private List<Message> message = new ArrayList<>();
  private int messageListNumber = 1;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public List<Message> getMessage() {
    return message;
  }
  public void setMessage(List<Message> message) {
    this.message = message;
  }
  public int getMessageListNumber() {
    return messageListNumber;
  }
  public void setMessageListNumber(int messageListNumber) {
    this.messageListNumber = messageListNumber;
  }

}