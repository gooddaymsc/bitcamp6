package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyDetailHandler extends AbstractSellerPrivacyHandler{
  @Override
  public void execute() {}

  public SellerPrivacyDetailHandler(List<SellerPrivacy> memberList, List<Manager> managerList) {
    super(memberList, managerList);
  }


  @Override
  public void execute(int i) {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[판매자 상세보기]");
    String id = Prompt.inputString("번호를 입력하세요: ");

    SellerPrivacy member = findById(id);

    if (member == null) {
      System.out.println("해당 번호의 판매자가 없습니다.");
      return;
    }

    System.out.printf("이름: %s입니다.\n", member.getName());
    System.out.printf("닉네임: %s입니다.\n", member.getNickname());
    System.out.printf("이메일: %s입니다.\n", member.getEmail());
    System.out.printf("생일: %s입니다.\n", member.getBirthday());
    System.out.printf("사진: %s입니다.\n", member.getPhoto());
    System.out.printf("전화: %s입니다.\n", member.getPhoneNumber());
    System.out.printf("사업자번호: %s입니다.\n", member.getBusinessNumber());
    System.out.printf("사업장주소: %s입니다.\n", member.getBusinessAddress());
    System.out.printf("사업장번호: %s입니다.\n", member.getBusinessPlaceNumber());
    System.out.printf("등록일: %s입니다.\n", member.getRegisteredDate());
    System.out.printf("권한등급: %d입니다.", member.getAuthority());
  }
}







