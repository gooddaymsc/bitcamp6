package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.SellerPrivacy;

public class SellerPrivacyListHandler extends AbstractSellerPrivacyHandler {

  public SellerPrivacyListHandler(List<SellerPrivacy> memberList, List<Manager> managerList) {
    super(memberList, managerList);
  }


  @Override
  public void execute(){
    if (App.getLoginUser().getAuthority() == 0 || App.getLoginUser().getAuthority()== 1 || 
        App.getLoginUser().getAuthority() == 2 ) {
      System.out.println("해당 메뉴는 관리자만 접근 가능합니다.");
      return;
    }
    System.out.println("\n[판매자 목록]");

    SellerPrivacy[] list = memberList.toArray(new SellerPrivacy[0]);

    for (SellerPrivacy member : list) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s\n", 
          member.getNumber(), 
          member.getName(), 
          member.getEmail(), 
          member.getPhoneNumber(), 
          member.getBusinessNumber(),
          member.getBusinessAddress(),
          member.getBusinessPlaceNumber(),
          member.getRegisteredDate());
    }
  }
}







