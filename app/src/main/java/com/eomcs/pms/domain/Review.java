package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Review implements Serializable {

  private int no;
  private float score;              //평점
  private String comment;           //코멘트        
  private String id;                //작성자    
  private Date RegisteredDate;      //등록일



  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public float getScore() {
    return score;
  }
  public void setScore(float score) {
    this.score = score;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Date getRegisteredDate() {
    return RegisteredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    RegisteredDate = registeredDate;
  }


}
