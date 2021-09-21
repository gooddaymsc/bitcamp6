package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;

public abstract class AbstractSellerHandler implements Command {

  protected List<Member> memberList;

  public AbstractSellerHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  int size=1;

  protected Member findById(String id) {
    for (Member seller : memberList) {
      if (seller.getId().equals(id)) {
        return seller;
      }
    }
    return null;
  }
}







