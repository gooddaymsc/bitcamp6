package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyDetailHandler extends AbstractSellerPrivacyHandler{

  public SellerPrivacyDetailHandler(List<SellerPrivacy> sellerList) {
    super(sellerList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }


    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("\n[개인정보 상세보기]");

      SellerPrivacy member = findById(App.getLoginUser().getId());

      System.out.printf("이름 : %s\n", member.getName());
      System.out.printf("닉네임 : %s\n", member.getNickname());
      System.out.printf("이메일 : %s\n", member.getEmail());
      System.out.printf("생일 : %s\n", member.getBirthday());
      System.out.printf("사진 : %s\n", member.getPhoto());
      System.out.printf("전화 : %s\n", member.getPhoneNumber());
      System.out.printf("가게명 : %s\n", member.getBusinessName());
      System.out.printf("사업자번호 : %s\n", member.getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", member.getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", member.getBusinessPlaceNumber());
      System.out.printf("등록일 : %s\n", member.getRegisteredDate());
      System.out.printf("권한등급 : %d", member.getAuthority());
    } else {
      System.out.println("\n[판매자 상세보기]");

      SellerPrivacy member = findById(Prompt.inputString("상세보기할 판매자 아이디: "));

      if (member == null) {
        System.out.println("해당 아이디의 판매자가 없습니다.");
        return;
      }
      System.out.printf("이름 : %s\n", member.getName());
      System.out.printf("닉네임 : %s\n", member.getNickname());
      System.out.printf("등급 : %s\n", member.getLevel());
      System.out.printf("이메일 : %s\n", member.getEmail());
      System.out.printf("사진 : %s\n", member.getPhoto());
      System.out.printf("전화 : %s\n", member.getPhoneNumber());
      System.out.printf("사업자번호 : %s\n", member.getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", member.getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", member.getBusinessPlaceNumber());
    }
  }
}







