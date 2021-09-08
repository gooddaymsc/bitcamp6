package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.SellerPrivacy;

public class SellerPrivacyListHandler extends AbstractSellerPrivacyHandler {

  @Override
  public void execute(){
    System.out.println("\n[판매자 목록]");
    for (SellerPrivacy member : App.sellerPrivacyList) {
      System.out.printf("판매자번호-%d, %s, %s, %s, %s, 등급[%d], %s\n", 
          member.getNumber(), 
          member.getId(),
          member.getBusinessName(),
          member.getName(), 
          member.getNickname(),
          member.getLevel(),
          member.getRegisteredDate());
    }
  }
}







