package com.eomcs.pms.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Stock implements Serializable{

  private Product product;
  private int stockNumber;            //재고번호
  private int stocks;                 //재고 수량
  private int price;                  //가격
  private String id;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

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