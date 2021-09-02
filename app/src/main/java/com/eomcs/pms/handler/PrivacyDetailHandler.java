package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyDetailHandler extends AbstractPrivacyHandler {


  public PrivacyDetailHandler(List<Privacy> memberList,  List<Manager> managerList) {
    super(memberList, managerList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[회원 상세보기]");

    Privacy member = findById(Prompt.inputString("아이디 정보보기 : "));

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    //  if (!loginPrivacy.getId().equals(member.getId()) && (loginPrivacy.getAuthority()==3)) {
    //      System.out.println("현재 로그인 된 아이디로 입력하여주세요.");
    //      return;
    //    }

    System.out.printf("이름: %s입니다.\n", member.getName());
    System.out.printf("닉네임: %s입니다.\n", member.getNickname());
    System.out.printf("이메일: %s입니다.\n", member.getEmail());
    System.out.printf("생일: %s입니다.\n", member.getBirthday());
    System.out.printf("사진: %s입니다.\n", member.getPhoto());
    System.out.printf("전화: %s입니다.\n", member.getPhoneNumber());
    System.out.printf("등록일: %s입니다.\n", member.getRegisteredDate());
    System.out.printf("권한등급: %d입니다.", member.getAuthority());
  }
}







