package com.eomcs.pms.domain;

import java.util.List;

@SuppressWarnings("serial")
public class Buyer extends Member {
  private String address;
  private List<Cart> cartList;  

  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public List<Cart> getCartList() {
    return cartList;
  }
  public void setCartList(List<Cart> cartList) {
    this.cartList = cartList;
  }

}