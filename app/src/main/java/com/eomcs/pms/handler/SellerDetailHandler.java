package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerDetailHandler extends AbstractSellerHandler{

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("\n[개인정보 상세보기]");

      Seller seller = findById(App.getLoginUser().getId());

      System.out.printf("이름 : %s\n", seller.getName());
      System.out.printf("닉네임 : %s\n", seller.getNickname());
      System.out.printf("이메일 : %s\n", seller.getEmail());
      System.out.printf("생일 : %s\n", seller.getBirthday());
      System.out.printf("사진 : %s\n", seller.getPhoto());
      System.out.printf("전화 : %s\n", seller.getPhoneNumber());
      System.out.printf("가게명 : %s\n", seller.getBusinessName());
      System.out.printf("사업자번호 : %s\n", seller.getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", seller.getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", seller.getBusinessPlaceNumber());
      System.out.printf("등록일 : %s\n", seller.getRegisteredDate());
      System.out.printf("권한등급 : %d", seller.getAuthority());
    } else {
      System.out.println("\n[판매자 상세보기]");

      Seller seller = findById(Prompt.inputString("상세보기할 판매자 아이디: "));

      if (seller == null) {
        System.out.println("해당 아이디의 판매자가 없습니다.");
        return;
      }
      System.out.printf("이름 : %s\n", seller.getName());
      System.out.printf("닉네임 : %s\n", seller.getNickname());
      System.out.printf("등급 : %s\n", seller.getLevel());
      System.out.printf("이메일 : %s\n", seller.getEmail());
      System.out.printf("사진 : %s\n", seller.getPhoto());
      System.out.printf("전화 : %s\n", seller.getPhoneNumber());
      System.out.printf("사업자번호 : %s\n", seller.getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", seller.getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", seller.getBusinessPlaceNumber());
    }
  }
}







