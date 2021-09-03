package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;

public class PrivacyListHandler extends AbstractPrivacyHandler {

  public PrivacyListHandler(List<Privacy> privacyList) {
    super(privacyList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("해당 메뉴는 관리자만 접근 가능합니다.");
      return;
    }
    System.out.println("\n[회원 목록]");

    for (Privacy member : privacyList) {
      System.out.printf("회원번호-%s, %s, %s, %s, 등급[%d], %s\n", 
          member.getName(),
          member.getId(), 
          member.getName(),
          member.getNickname(),
          member.getLevel(),
          //member.getEmail(), 
          //member.getPhoneNumber(), 
          member.getRegisteredDate());
    }
  }
}







