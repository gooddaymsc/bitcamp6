package com.eomcs.pms.domain;

import java.sql.Date;
import java.util.List;

public class Privacy extends Manager {
  private int number;
  private String name;
  private String nickname;
  private String email;
  private Date birthday;
  private String photo;
  private String phoneNumber;
  private String address;
  private Date registeredDate;
  private int level;
  private List<Cart> cartList;
  private List<Booking> bookingList;

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

  public List<Cart> getCartList() {
    return cartList;
  }
  public void setCartList(List<Cart> cartList) {
    this.cartList = cartList;
  }
  public List<Booking> getBookingList() {
    return bookingList;
  }
  public void setBookingList(List<Booking> bookingList) {
    this.bookingList = bookingList;
  }

}