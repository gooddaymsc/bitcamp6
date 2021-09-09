package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Seller;

public abstract class AbstractCartHandler implements Command {
  CartPrompt cartPrompt;

  public AbstractCartHandler(CartPrompt cartPrompt) {
    this.cartPrompt = cartPrompt;
  }

  protected Seller findSellerInfo(String sellerId) {
    for (Seller seller : App.sellerList) {
      if (seller.getName().equals(sellerId)){
        return seller;
      }
    }
    return null;
  }

}
