package com.eomcs.pms.domain;

public class Product {
  //상품번호
  //상품명
  //설명
  //원산지
  //품종
  //알콜도수
  //테이스팅 노트 : 당도, 산도, 바디감
  //가격
  private int productNumber;
  private String productName;
  private String productType;
  private String countryOrigin;
  private String variety;
  private int alcoholLevel;
  private int sugerLevel;
  private int acidity;
  private int weight;
  private int price;

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
    this.productType = productType;
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
  public int getAlcoholLevel() {
    return alcoholLevel;
  }
  public void setAlcoholLevel(int alcoholLevel) {
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
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }



}
