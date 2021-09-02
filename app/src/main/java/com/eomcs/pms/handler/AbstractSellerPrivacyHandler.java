package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.SellerPrivacy;

public abstract class AbstractSellerPrivacyHandler implements Command {

  protected List<SellerPrivacy> memberList;
  int size=1;

  public AbstractSellerPrivacyHandler(List<SellerPrivacy> memberList) {
    this.memberList = memberList;
  }

  protected SellerPrivacy findById(String id) {
    SellerPrivacy[] arr = memberList.toArray(new SellerPrivacy[0]);
    for (SellerPrivacy member : arr) {
      if (member.getId() == id) {
        return member;
      }
    }
    return null;
  }
}







