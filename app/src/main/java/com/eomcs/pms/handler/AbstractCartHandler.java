package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;

public abstract class AbstractCartHandler implements Command {


  protected Cart findByCart (String ProductName) {
    CartList cartList = findById(App.getLoginUser().getId());
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        return cart;
      }
    }
    return null;
  }

<<<<<<< HEAD
=======
  protected CartList findById(String id) {
    for (CartList cartList : App.allCartList) {
      if (cartList.getId().equals(id)) {
        return cartList;
      }
    }
    return null;
  }
>>>>>>> ddc34eb72d81c3e55b270f8e3fe6293e6772e13d
}
