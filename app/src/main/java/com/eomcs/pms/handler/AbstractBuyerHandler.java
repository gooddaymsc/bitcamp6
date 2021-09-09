package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Buyer;

public abstract class AbstractBuyerHandler implements Command {

  protected Buyer findById(String id) {
    Buyer[] arr = App.buyerList.toArray(new Buyer[0]);
    for (Buyer buyer : arr) {
      if (buyer.getId().equals(id)) {
        return buyer;
      }
    }
    return null;
  }

  protected int removePrivateById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<App.buyerList.size(); i++) {
      if (App.buyerList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
  protected int removemanagerById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<App.memberList.size(); i++) {
      if (App.memberList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
}






