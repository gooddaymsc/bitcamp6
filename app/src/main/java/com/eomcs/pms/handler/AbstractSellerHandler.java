package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;

public abstract class AbstractSellerHandler implements Command {

  protected List<Seller> sellerList;
  protected List<Member> memberList;

  public AbstractSellerHandler(List<Seller> sellerList, List<Member> memberList) {
    this.sellerList = sellerList;
    this.memberList = memberList;
  }

  int size=1;

  protected Seller findById(String id) {
    for (Seller seller : sellerList) {
      if (seller.getId().equals(id)) {
        return seller;
      }
    }
    return null;
  }
  //--------상품 검색시 판매자정보 반환
  //  @SuppressWarnings("unlikely-arg-type")
  //  protected seller findBySellerId (String StockName) {
  //    for (seller member : App.sellerList) {
  //      if (member.getStockList().equals(StockName)){
  //        System.out.printf("판매자번호-%d\n", 
  //            member.getBusinessNumber());
  //        return member;
  //      }
  //    }
  //    return null;
  //  }

  protected int removeSellerPrivateById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<sellerList.size(); i++) {
      if (sellerList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
  protected int removeManagerById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<memberList.size(); i++) {
      if (memberList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
}







