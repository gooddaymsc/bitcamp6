package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;

public abstract class AbstractCartHandler implements Command {

  List<Cart> cartList;  

  protected Cart findByCart (String ProductName) {
    CartList cartList = findById(App.getLoginUser().getId());
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        return cart;
      }
    }
    return null;
  }

  protected CartList findById(String id) {
    for (CartList cartList : App.allCartList) {
      if (cartList.getId().equals(id)) {
        return cartList;
      }
    }
    return null;
  }
}
