package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.SellerPrivacy;

public class SellerPrivacyListHandler extends AbstractSellerPrivacyHandler {

  public SellerPrivacyListHandler(List<SellerPrivacy> memberList) {
    super(memberList);
  }


  @Override
  public void execute(){
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN ) {
      System.out.println("해당 메뉴는 관리자만 접근 가능합니다.");
      return;
    }
    System.out.println("\n[판매자 목록]");
    for (SellerPrivacy member : memberList) {
      System.out.printf("판매자번호-%d, %s, %s, %s, 등급[%d], %s\n", 
          member.getNumber(), 
          member.getId(),
          member.getName(), 
          member.getNickname(),
          member.getLevel(),
          //          member.getEmail(), 
          //          member.getPhoneNumber(), 
          //          member.getBusinessNumber(),
          //          member.getBusinessAddress(),
          //          member.getBusinessPlaceNumber(),
          member.getRegisteredDate());
    }
  }
}







