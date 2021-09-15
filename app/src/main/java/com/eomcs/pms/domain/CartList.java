package com.eomcs.pms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CartList implements Serializable {
  private String id; //구매자 id
  private List<Cart> privacyCart = new ArrayList<>();
  private int cartListNumber = 1;

  public int getCartListNumber() {
    return cartListNumber;
  }
  public void setCartListNumber(int cartListNumber) {
    this.cartListNumber = cartListNumber;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public List<Cart> getPrivacyCart() {
    return privacyCart;
  }
  public void setPrivacyCart(List<Cart> privacyCart) {
    this.privacyCart = privacyCart;
  }
}
