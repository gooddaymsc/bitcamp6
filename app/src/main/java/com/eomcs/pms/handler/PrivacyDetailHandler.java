package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyDetailHandler extends AbstractPrivacyHandler {

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority()!=Menu.ACCESS_ADMIN) {
      System.out.println("\n[개인정보 상세보기]");

      Privacy member = findById(App.getLoginUser().getId());

      System.out.printf("이름 : %s\n", member.getName());
      System.out.printf("닉네임 : %s\n", member.getNickname());
      System.out.printf("이메일 : %s\n", member.getEmail());
      System.out.printf("생일 : %s\n", member.getBirthday());
      System.out.printf("사진 : %s\n", member.getPhoto());
      System.out.printf("전화 : %s\n", member.getPhoneNumber());
      System.out.printf("주소 : %s\n", member.getAddress());
      System.out.printf("등록일 : %s\n", member.getRegisteredDate());
      System.out.printf("권한등급 : %d", member.getAuthority());
    } else {
      System.out.println("\n[회원 상세보기]");

      Privacy member = findById(Prompt.inputString("상세보기할 아이디: "));

      if (member == null) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }
      System.out.printf("회원번호 : %s\n", member.getNumber());
      System.out.printf("이름 : %s\n", member.getName());
      System.out.printf("닉네임 : %s\n", member.getNickname());
      System.out.printf("등급 : %s\n", member.getLevel());
      System.out.printf("이메일 : %s\n", member.getEmail());
      System.out.printf("사진 : %s\n", member.getPhoto());
      System.out.printf("전화 : %s\n", member.getPhoneNumber());
      System.out.printf("주소 : %s\n", member.getAddress());
      System.out.printf("등록일 : %s\n", member.getRegisteredDate());
    }
  }
}







