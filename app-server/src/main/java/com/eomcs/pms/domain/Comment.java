package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Comment implements Serializable{
  private int commentNumber;
  private int boardNumber;
  private String id;
  private String content;
  private Date registrationDate;

  public int getCommentNumber() {
    return commentNumber;
  }
  public void setCommentNumber(int commentNumber) {
    this.commentNumber = commentNumber;
  }
  public int getBoardNumber() {
    return boardNumber;
  }
  public void setBoardNumber(int boardNumber) {
    this.boardNumber = boardNumber;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Date getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }


}
