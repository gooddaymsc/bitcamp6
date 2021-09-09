package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Seller;

public class SellerListHandler extends AbstractSellerHandler {

  @Override
  public void execute(){
    System.out.println("\n[판매자 목록]");
    for (Seller seller : App.sellerList) {
      System.out.printf("판매자번호-%d, %s, %s, %s, %s, 등급[%d], %s\n", 
          seller.getNumber(), 
          seller.getId(),
          seller.getBusinessName(),
          seller.getName(), 
          seller.getNickname(),
          seller.getLevel(),
          seller.getRegisteredDate());
    }
  }
}







