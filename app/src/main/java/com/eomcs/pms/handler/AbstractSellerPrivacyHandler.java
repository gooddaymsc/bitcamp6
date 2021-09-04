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
    for (SellerPrivacy member : memberList) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }
}







