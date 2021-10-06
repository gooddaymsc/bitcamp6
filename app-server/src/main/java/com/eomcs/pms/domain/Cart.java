package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Cart implements Serializable {
  private Stock stock; // 장바구니 담을 상품
  private int cartStocks; // 장바구니상품의 갯수
  private int cartPrice; // 총액
  private int cartNumber; //장바구니번호
  private String sellerId; //판매자 id
  private String id; // 구매자 id
  private Date registeredDate;


  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public int getCartStocks() {
    return cartStocks;
  }
  public void setCartStocks(int cartStocks) {
    this.cartStocks = cartStocks;
  }


  public Stock getStock() {
    return stock;
  }
  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public int getCartPrice() {
    return cartPrice;
  }
  public void setCartPrice(int cartPrice) {
    //    if (stock != null) {
    //      cartPrice = (stock.getPrice())*getCartStocks();
    //    }
    this.cartPrice = cartPrice;
  }
  public int getCartNumber() {
    return cartNumber;
  }
  public void setCartNumber(int cartNumber) {
    this.cartNumber = cartNumber;
  }
  public Date getRegistrationDate() {
    return registeredDate;
  }
  public void setRegistrationDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public String getSellerId() {
    return sellerId;
  }
  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

}