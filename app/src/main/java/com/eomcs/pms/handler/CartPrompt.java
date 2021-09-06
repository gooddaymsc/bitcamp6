package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;

public class CartPrompt {

  public CartList findCartListById(String id) {
    for (CartList cartList : App.allCartList) {
      if (cartList.getId().equals(id)) {
        return cartList;
      }
    }
    return null;
  }
  protected Cart findByCart (String ProductName) {
    CartList cartList = findCartListById(App.getLoginUser().getId());
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        return cart;
      }
    }
    return null;
  }
}
