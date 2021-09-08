package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;

public class PrivacyListHandler extends AbstractPrivacyHandler {

  @Override
  public void execute() {
    System.out.println("\n[회원 목록]");

    for (Privacy member : App.privacyList) {
      System.out.printf("회원번호-%d, %s, %s, %s, 등급[%d], %s\n", 
          member.getNumber(),
          member.getId(), 
          member.getName(),
          member.getNickname(),
          member.getLevel(),
          member.getRegisteredDate());
    }
  }
}







