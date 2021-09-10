package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Member implements Serializable {
  private String id;
  private String password;
  private int authority;
  private int number;
  private String name;
  private String nickname;
  private String email;
  private Date birthday;
  private String photo;
  private String phoneNumber;
  private Date registeredDate;
  private int level;
  private int memberType;
  private List<Booking> bookingList;

  public Member() {
    this("-","-",0x01);
  }
  public Member(String id, String password, int authority) {
    this.id = id;
    this.password = password;
    this.authority = authority;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public int getAuthority() {
    return authority;
  }
  public void setAuthority(int authority) {
    this.authority = authority;
  }
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
  public int getMemberType() {
    return memberType;
  }
  public void setMemberType(int memberType) {
    this.memberType = memberType;
  }
  public List<Booking> getBookingList() {
    return bookingList;
  }
  public void setBookingList(List<Booking> bookingList) {
    this.bookingList = bookingList;
  }


}
