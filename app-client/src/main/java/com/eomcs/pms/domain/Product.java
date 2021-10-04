package com.eomcs.pms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Product implements Serializable{
  private List<Review> reviewList = new ArrayList<>();
  private int productNumber;        //상품번
  private String productName;       //상품명
  private String productType;       //주종
  private String productSubType;    //세부주종    
  private String countryOrigin;     //원산지
  private String variety;           //품종
  private int volume;               //용량 
  private float alcoholLevel;       //알콜도수
  private int sugerLevel;           //당도
  private int acidity;              //산도
  private int weight;               //바디감
  private float rate;               //평점
  private int reviewerNum;          //댓글 남긴 사람의 

  public int getProductNumber() {
    return productNumber;
  }
  public void setProductNumber(int productNumber) {
    this.productNumber = productNumber;
  }
  public String getProductName() {
    return productName;
  }
  public void setProductName(String productName) {
    this.productName = productName;
  }
  public String getProductType() {
    return productType;
  }
  public void setProductType(String productType) {
    //조건문
    this.productType = productType;
  }
  public String getProductSubType() {
    return productSubType;
  }
  public void setProductSubType(String productSubType) {
    this.productSubType = productSubType;
  }
  public String getCountryOrigin() {
    return countryOrigin;
  }
  public void setCountryOrigin(String countryOrigin) {
    this.countryOrigin = countryOrigin;
  }
  public String getVariety() {
    return variety;
  }
  public void setVariety(String variety) {
    this.variety = variety;
  }
  public int getVolume() {
    return volume;
  }
  public void setVolume(int volume) {
    this.volume = volume;
  }
  public float getAlcoholLevel() {
    return alcoholLevel;
  }
  public void setAlcoholLevel(float alcoholLevel) {
    this.alcoholLevel = alcoholLevel;
  }
  public int getSugerLevel() {  
    return sugerLevel;
  }
  public void setSugerLevel(int sugerLevel) {
    this.sugerLevel = sugerLevel;
  }
  public int getAcidity() {
    return acidity;
  }
  public void setAcidity(int acidity) {
    this.acidity = acidity;
  }
  public int getWeight() {
    return weight;
  }
  public void setWeight(int weight) {
    this.weight = weight;
  }

  public List<Review> getReviewList() {
    return reviewList;
  }
  public void setReviewList(List<Review> reviewList) {
    this.reviewList = reviewList;
  }
  //  public float getRate() {
  //    if (this.reviewList.size()==0) {
  //      return 0;
  //    } else {
  //      int sum = 0;
  //      for (Review review : this.reviewList) {
  //        sum += review.getScore();
  //      }
  //      this.rate = (float) sum/(reviewList.size());
  //      return this.rate;
  //    }
  //  }
  public int getReviewerNum() {
    return reviewerNum;
  }
  public void setReviewerNum(int reviewerNum) {
    this.reviewerNum = reviewerNum;
  }



}
