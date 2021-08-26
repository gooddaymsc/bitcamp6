package com.eomcs.pms.domain;

public class Alcohol {
  //상품번호
  //상품명
  //설명
  //원산지
  //품종
  //알콜도수
  //테이스팅 노트 : 당도, 산도, 바디감
  //가격
  private int no;
  private String name;
  private String kind;
  private String made;
  private String grapes;
  private int abv;
  private int sweet;
  private int acidic;
  private int body;
  private int price;

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getKind() {
    return kind;
  }
  public void setKind(String kind) {
    this.kind = kind;
  }
  public String getMade() {
    return made;
  }
  public void setMade(String made) {
    this.made = made;
  }
  public String getGrapes() {
    return grapes;
  }
  public void setGrapes(String grapes) {
    this.grapes = grapes;
  }
  public int getAbv() {
    return abv;
  }
  public void setAbv(int abv) {
    this.abv = abv;
  }
  public int getSweet() {
    return sweet;
  }
  public void setSweet(int sweet) {
    this.sweet = sweet;
  }
  public int getAcidic() {
    return acidic;
  }
  public void setAcidic(int acidic) {
    this.acidic = acidic;
  }
  public int getBody() {
    return body;
  }
  public void setBody(int body) {
    this.body = body;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }

}
