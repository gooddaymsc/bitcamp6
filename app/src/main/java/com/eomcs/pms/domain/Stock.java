package com.eomcs.pms.domain;

public class Stock {
  //재고번호
  //상품명
  //재고수량
  //원산지
  //가격
  private int no;
  private String kind;
  private String name;
  private int stock;
  private String made;
  private int price;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getKind() {
    return kind;
  }
  public void setKind(String kind) {
    this.kind = kind;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getStock() {
    return stock;
  }
  public void setStock(int stock) {
    this.stock = stock;
  }
  public String getMade() {
    return made;
  }
  public void setMade(String made) {
    this.made = made;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }

}