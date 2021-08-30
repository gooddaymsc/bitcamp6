package com.eomcs.pms.domain;


public class Product {

  private int productNumber;        //상품번
  private String productName;       //상품명
  private String productType;       //주종
  private String countryOrigin;     //원산지
  private String variety;           //품종
  private float alcoholLevel;       //알콜도수
  private int sugerLevel;           //당도
  private int acidity;              //산도
  private int weight;               //바디감


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


}
