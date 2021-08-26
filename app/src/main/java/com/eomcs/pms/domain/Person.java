package com.eomcs.pms.domain;

import java.sql.Date;

public class Person {
  private int no;
  private String name;
  private String nickName;
  private String email;
  private String password;
  private String photo;
  private String tel;
  private String address;
  private Date registeredDate;
  private int level;
  private String buyerSeller;
  private int cNo;
  private int cTel;

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
  public String getNickName() {
    return nickName;
  }
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public int getLevel() {
    return level;
  }
  public void setLevel(int level) {
    this.level = level;
  }
  public String getBuyerSeller() {
    return buyerSeller;
  }
  public void setBuyerSeller(String buyerSeller) {
    this.buyerSeller = buyerSeller;
  }
  public int getcNo() {
    return cNo;
  }
  public void setcNo(int cNo) {
    this.cNo = cNo;
  }
  public int getcTel() {
    return cTel;
  }
  public void setcTel(int cTel) {
    this.cTel = cTel;
  }


}