package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.SellerPrivacy;

public abstract class AbstractCartHandler implements Command {
  CartPrompt cartPrompt;

  public AbstractCartHandler(CartPrompt cartPrompt) {
    this.cartPrompt = cartPrompt;
  }

  protected SellerPrivacy findSellerInfo(String sellerId) {
    for (SellerPrivacy member : App.sellerPrivacyList) {
      if (member.getName().equals(sellerId)){
        return member;
      }
    }
    return null;
  }

}
