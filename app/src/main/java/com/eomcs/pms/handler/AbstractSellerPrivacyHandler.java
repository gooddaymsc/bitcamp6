package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.SellerPrivacy;

public abstract class AbstractSellerPrivacyHandler implements Command {

  protected List<SellerPrivacy> sellerList;
  int size=1;

  public AbstractSellerPrivacyHandler(List<SellerPrivacy> sellerList) {
    this.sellerList = sellerList;
  }

  protected SellerPrivacy findById(String id) {
    for (SellerPrivacy member : sellerList) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }
  //--------상품 검색시 판매자정보 반환
  protected SellerPrivacy findBySellerId (String StockName) {
    for (SellerPrivacy member : sellerList) {
      if (member.getStockList().equals(StockName)){
        System.out.printf("판매자번호-%d\n", 
            member.getBusinessNumber());
        return member;
      }
    }
    return null;
  }
}







