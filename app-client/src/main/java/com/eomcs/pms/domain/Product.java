package com.eomcs.pms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Product implements Serializable{
  private List<Review> reviewList = new ArrayList<>();
  private int productNumber;        //상품번
  private String productName;       //상품명
  private int typeNumber;   
  private String countryOrigin;     //원산지
  private String variety;           //품종
  private int volume;               //용량 
  private float alcoholLevel;       //알콜도수
  private int sugarLevel;           //당도
  private int acidity;              //산도
  private int weight;               //바디감
  private float rate;               //평점
  private int reviewerNum;          //댓글 남긴 사람의 
  private int totalReviewNumber = 1; 
  private ProductType productType;

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
  public int getTypeNumber() {
    return typeNumber;
  }
  public void setTypeNumber(int typeNumber) {
    this.typeNumber = typeNumber;
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
  public int getSugarLevel() {
    return sugarLevel;
  }
  public void setSugarLevel(int sugarLevel) {
    this.sugarLevel = sugarLevel;
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

  public void setRate(float rate) {
    this.rate = rate;
  }

  public float getRate() {
    //    if (this.reviewList.size()==0) {
    //      return 0;
    //    } else {
    //      int sum = 0;
    //      for (Review review : this.reviewList) {
    //        sum += review.getScore();
    //      }
    //      this.rate = (float) sum/(reviewList.size());
    //      return this.rate;
    return rate;
  }
  //  }
  public int getReviewerNum() {
    return reviewerNum;
  }
  public void setReviewerNum(int reviewerNum) {
    this.reviewerNum = reviewerNum;
  }
  public int getTotalReviewNumber() {
    return totalReviewNumber;
  }
  public void setTotalReviewNumber(int totalReviewNumber) {
    this.totalReviewNumber = totalReviewNumber;
  }
  public ProductType getProductType() {
    return productType;
  }
  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

}
