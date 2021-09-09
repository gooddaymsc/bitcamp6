package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Seller;

public abstract class AbstractCartHandler implements Command {
  CartPrompt cartPrompt;
  List <Seller> sellerList;

  public AbstractCartHandler(CartPrompt cartPrompt) {
    this.cartPrompt = cartPrompt;
  }

  //    protected Seller findSellerInfo(String sellerId) {
  //      for (Seller seller : sellerList) {
  //        if (seller.getName().equals(sellerId)){
  //          return seller;
  //        }
  //      }
  //      return null;
  //    }

}
