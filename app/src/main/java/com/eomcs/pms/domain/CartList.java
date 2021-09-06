package com.eomcs.pms.domain;

import java.util.ArrayList;
import java.util.List;

public class CartList {
  private String id; //구매자 id
  private List<Cart> privacyCart = new ArrayList<>();

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
