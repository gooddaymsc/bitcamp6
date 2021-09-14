package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;

public abstract class AbstractBuyerHandler implements Command {
  List<Member> memberList;

  public AbstractBuyerHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  protected Member findById(String id) {
    //    Buyer[] arr = memberList.toArray(new Buyer[0]);
    for (Member buyer : memberList) {
      if (buyer.getId().equals(id)) {
        return buyer;
      }
    }
    return null;
  }

  protected int removePrivateById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i< memberList.size(); i++) {
      if (memberList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

}






