package com.eomcs.pms.domain;

import java.sql.Date;

public class Privacy extends Manager {
  private int number;
  private String id;

  private String name;
  private String nickname;
  private String email;
  private Date birthday;
  private String photo;
  private String phoneNumber;
  private String address;
  private Date registeredDate;
<<<<<<< HEAD
  private int authority;
=======
  private int level; //add... sc
  private String buyerSeller; //add... sc
>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
<<<<<<< HEAD
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
=======

>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
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

  public int getAuthority() {
    return authority;
  }
  public void setAuthority(int authority) {
    this.authority = authority;
  }



}