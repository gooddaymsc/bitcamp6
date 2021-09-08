package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.SellerPrivacy;

public abstract class AbstractSellerPrivacyHandler implements Command {

  int size=1;

  protected SellerPrivacy findById(String id) {
    for (SellerPrivacy member : App.sellerPrivacyList) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }
  //--------상품 검색시 판매자정보 반환
  @SuppressWarnings("unlikely-arg-type")
  protected SellerPrivacy findBySellerId (String StockName) {
    for (SellerPrivacy member : App.sellerPrivacyList) {
      if (member.getStockList().equals(StockName)){
        System.out.printf("판매자번호-%d\n", 
            member.getBusinessNumber());
        return member;
      }
    }
    return null;
  }
  protected int removeSellerPrivateById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<App.sellerPrivacyList.size(); i++) {
      if (App.sellerPrivacyList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
  protected int removemanagerById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<App.managerList.size(); i++) {
      if (App.managerList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
}







