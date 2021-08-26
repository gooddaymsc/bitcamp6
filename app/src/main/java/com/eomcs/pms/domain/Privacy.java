package com.eomcs.pms.domain;

import java.sql.Date;

public class Privacy {
  private int no;
  private String name;
  private String nickname;
  private String email;
  private Date birthday;
  private String password;
  private String photo;
  private String phoneNumber;
  private Date registeredDate;
  /* private String businessNo;
   * private String businessAddress;
   * private String businessTel;
   */

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
    return phoneNumber;
  }
  public void setTel(String tel) {
    this.phoneNumber = tel;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public String getNickName() {
    return nickname;
  }
  public void setNickName(String nickName) {
    this.nickname = nickName;
  }
  public Date getBirthDay() {
    return birthday;
  }
  public void setBirthDay(Date birthDay) {
    this.birthday = birthDay;
  }
}