package com.eomcs.pms.domain;


public class Stock {

  public Product product;
  public Stock stock;
  private int stockNumber;            //재고번호
  private String stockType;           //주종
  private String stockName;           //상품명    
  private int stocks;                 //재고 수량
  private String stockOrigin;         //원산지
  private int price;                  //가격

  public int getStockNumber() {
    return stockNumber;
  }
  public void setStockNumber(int stockNumber) {
    this.stockNumber = stockNumber;
  }
  public String getStockType() {
    return stockType;
  }
  public void setStockType(String stockType) {
    this.stockType = stockType;
  }
  public String getStockName()  {
    return stockName;
  }
  public void setStockName(String stockName) {
    this.stockName = stockName;
  }
  public int getStocks() {
    return stocks;
  }
  public void setStocks(int stocks) {
    this.stocks = stocks;
  }
  public String getStockOrigin() {
    return stockOrigin;
  }
  public void setStockOrigin(String stockOrigin) {
    this.stockOrigin = stockOrigin;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }

}