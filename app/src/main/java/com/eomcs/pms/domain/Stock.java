package com.eomcs.pms.domain;

public class Stock {

  private int stockNumber;          //재고번호
  private String productType;       //주종
  private String productName;       //상품명    
  private int stock;                //재고 수량
  private String countryOrigin;     //원산지
  private int price;                //가격

  public int getStockNumber() {
    return stockNumber;
  }
  public void setStockNumber(int stockNumber) {
    this.stockNumber = stockNumber;
  }
  public String getProductType() {
    return productType;
  }
  public void setProductType(String productType) {
    this.productType = productType;
  }
  public String getProductName() {
    return productName;
  }
  public void setProductName(String productName) {
    this.productName = productName;
  }
  public int getStock() {
    return stock;
  }
  public void setStock(int stock) {
    this.stock = stock;
  }
  public String getCountryOrigin() {
    return countryOrigin;
  }
  public void setCountryOrigin(String countryOrigin) {
    this.countryOrigin = countryOrigin;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }



}