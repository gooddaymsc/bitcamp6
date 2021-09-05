package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Cart;

public abstract class AbstractCartHandler implements Command {

  List<Cart> cartList;

  public AbstractCartHandler(List<Cart> cartList) {
    this.cartList = cartList;
  }

  protected Cart findByCart (String ProductName) {
    for (Cart cart : cartList) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        return cart;
      }
    }
    return null;
  }

}
