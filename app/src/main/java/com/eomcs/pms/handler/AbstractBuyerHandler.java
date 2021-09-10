package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Buyer;

public abstract class AbstractBuyerHandler implements Command {
  List<Buyer> buyerList;

  public AbstractBuyerHandler(List<Buyer> buyerList) {
    this.buyerList = buyerList;
  }

  protected Buyer findById(String id) {
    Buyer[] arr = buyerList.toArray(new Buyer[0]);
    for (Buyer buyer : arr) {
      if (buyer.getId().equals(id)) {
        return buyer;
      }
    }
    return null;
  }

  protected int removePrivateById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i< buyerList.size(); i++) {
      if (buyerList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

}






