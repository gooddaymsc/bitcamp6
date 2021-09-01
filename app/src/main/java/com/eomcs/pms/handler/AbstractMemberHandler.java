package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;

public abstract class AbstractMemberHandler {

  protected List<Privacy> memberList;
  protected List<SellerPrivacy> sellerList;

  public AbstractMemberHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    this.memberList = memberList;
    this.sellerList = sellerList;
  }

  protected Privacy findByNo(int no) {                          // 구매자 번호찾기
    Privacy[] arr = memberList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
      if (member.getNumber() == no) {
        return member;
      }
    }
    return null;
  }

  protected SellerPrivacy findByNo2(int no) {                         // 판매자 번호찾기
    SellerPrivacy[] arr = sellerList.toArray(new SellerPrivacy[0]);
    for (SellerPrivacy member : arr) {
      if (member.getNumber() == no) {
        return member;
      }
    }
    return null;
  }
}
