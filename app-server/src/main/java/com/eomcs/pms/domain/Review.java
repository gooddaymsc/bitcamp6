package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Review implements Serializable {

  private int no;
  private int productNo;
  private String reviewProduct;     //리뷰상품
  private float score;              //평점
  private String comment;           //코멘트        
  private Member member;            //작성자    
  private Date registeredDate;      //등록일
  private boolean purchase;         //구매여부


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getProductNo() {
    return productNo;
  }
  public void setProductNo(int productNo) {
    this.productNo = productNo;
  }
  public String getReviewProduct() {
    return reviewProduct;
  }
  public void setReviewProduct(String reviewProduct) {
    this.reviewProduct = reviewProduct;
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
  public Member getMember() {
    return member;
  }
  public void setMember(Member member) {
    this.member = member;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public boolean isPurchase() {
    return purchase;
  }
  public void setPurchase(boolean purchase) {
    this.purchase = purchase;
  }
}
