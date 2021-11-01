package com.eomcs.pms.domain;

import java.sql.Timestamp;

public class Comment {
  private int commentNumber;
  private int boardNumber;
  private Member writer;
  private String content;
  private Timestamp registrationDate;

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
  public Member getWriter() {
    return writer;
  }
  public void setWriter(Member writer) {
    this.writer = writer;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Timestamp getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(Timestamp registrationDate) {
    this.registrationDate = registrationDate;
  }

}
