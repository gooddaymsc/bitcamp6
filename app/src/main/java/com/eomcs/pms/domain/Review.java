package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Review implements Serializable {

  private float score;              //평점
  private String comment;           //코멘트        
  private String reviewer;          //작성자    
  private int reviewerNum;          //작성자 수
  private Date RegisteredDate;      //등록일


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
  public String getReviewer() {
    return reviewer;
  }
  public void setReviewer(String reviewer) {
    this.reviewer = reviewer;
  }
  public int getReviewerNum() {
    return reviewerNum;
  }
  public void setReviewerNum(int reviewerNum) {
    this.reviewerNum = reviewerNum;
  }
  public Date getRegisteredDate() {
    return RegisteredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    RegisteredDate = registeredDate;
  }


}
