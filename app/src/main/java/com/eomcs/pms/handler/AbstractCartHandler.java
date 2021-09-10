package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.CartList;

public abstract class AbstractCartHandler implements Command {
  CartPrompt cartPrompt;
  List<CartList> allCartList;

  public AbstractCartHandler(List<CartList> allCartList, CartPrompt cartPrompt) {
    this.cartPrompt = cartPrompt;
    this.allCartList = allCartList;
  }


}
