package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.SellerPrivacy;

public class SellerPrivacyListHandler extends AbstractSellerPrivacyHandler {

  public SellerPrivacyListHandler(List<SellerPrivacy> sellerList) {
    super(sellerList);
  }


  @Override
  public void execute(){
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN ) {
      System.out.println("해당 메뉴는 관리자만 접근 가능합니다.");
      return;
    }
    System.out.println("\n[판매자 목록]");
    for (SellerPrivacy member : sellerList) {
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







