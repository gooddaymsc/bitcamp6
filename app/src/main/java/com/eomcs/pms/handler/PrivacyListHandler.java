package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;

public class PrivacyListHandler extends AbstractPrivacyHandler {

  public PrivacyListHandler(List<Privacy> memberList) {
    super(memberList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != 3) {
      System.out.println("해당 메뉴는 관리자만 접근 가능합니다.");
      return;
    }
    System.out.println("\n[회원 목록]");

    Privacy[] list = memberList.toArray(new Privacy[0]);

    for (Privacy member : list) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          member.getId(), 
          member.getName(), 
          member.getEmail(), 
          member.getPhoneNumber(), 
          member.getRegisteredDate());
    }
  }
}







