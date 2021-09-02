package com.eomcs.pms.domain;


public class Stock {

  public Product product;
  private int stockNumber;            //재고번호
  private int stocks;                 //재고 수량
  private int price;                  //가격

  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }
  public int getStockNumber() {
    return stockNumber;
  }
  public void setStockNumber(int stockNumber) {
    this.stockNumber = stockNumber;
  }
  public int getStocks() {
    return stocks;
  }
  public void setStocks(int stocks) {
    this.stocks = stocks;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }

}