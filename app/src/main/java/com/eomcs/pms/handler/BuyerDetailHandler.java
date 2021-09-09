package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.util.Prompt;

public class BuyerDetailHandler extends AbstractBuyerHandler {

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority()!=Menu.ACCESS_ADMIN) {
      System.out.println("\n[개인정보 상세보기]");

      Buyer buyer = findById(App.getLoginUser().getId());

      System.out.printf("이름 : %s\n", buyer.getName());
      System.out.printf("닉네임 : %s\n", buyer.getNickname());
      System.out.printf("이메일 : %s\n", buyer.getEmail());
      System.out.printf("생일 : %s\n", buyer.getBirthday());
      System.out.printf("사진 : %s\n", buyer.getPhoto());
      System.out.printf("전화 : %s\n", buyer.getPhoneNumber());
      System.out.printf("주소 : %s\n", buyer.getAddress());
      System.out.printf("등록일 : %s\n", buyer.getRegisteredDate());
      System.out.printf("권한등급 : %d", buyer.getAuthority());
    } else {
      System.out.println("\n[회원 상세보기]");

      Buyer buyer = findById(Prompt.inputString("상세보기할 아이디: "));

      if (buyer == null) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }
      System.out.printf("회원번호 : %s\n", buyer.getNumber());
      System.out.printf("이름 : %s\n", buyer.getName());
      System.out.printf("닉네임 : %s\n", buyer.getNickname());
      System.out.printf("등급 : %s\n", buyer.getLevel());
      System.out.printf("이메일 : %s\n", buyer.getEmail());
      System.out.printf("사진 : %s\n", buyer.getPhoto());
      System.out.printf("전화 : %s\n", buyer.getPhoneNumber());
      System.out.printf("주소 : %s\n", buyer.getAddress());
      System.out.printf("등록일 : %s\n", buyer.getRegisteredDate());
    }
  }
}







