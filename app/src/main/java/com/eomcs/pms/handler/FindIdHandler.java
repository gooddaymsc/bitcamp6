package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class FindIdHandler {

  List<Privacy> memberList;
  List<SellerPrivacy> sellerList;

  public FindIdHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    this.memberList = memberList;
    this.sellerList = sellerList;
  }

  public void findId() {

    int buyerSeller = Prompt.inputInt("일반회원 1번, 판매자 2번 : ");

    if (buyerSeller != 1 && buyerSeller != 2) { 
      System.out.println("다시 입력해주세요.");
      return;
    }

    if (buyerSeller == 1) {
      String name = Prompt.inputString("이름을 입력하세요: ");
      if (name.equals("")) {
        System.out.println("다시 입력해주세요.");
        return;
      } 
      for (Privacy s : memberList) {
        if (s.getName().equals(name)) {
          String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
          if (phoneOrEmail.equals("")) {
            System.out.println("다시 입력해주세요.");
            return;
          }
          if (s.getPhoneNumber().equals(phoneOrEmail) || (s.getEmail().equals(phoneOrEmail))) {
            System.out.printf("회원의 아이디는 %s 입니다.\n", s.getId());
            return;
          } 
          else {
            System.out.println("입력하신 전화번호 또는 이메일에 해당하는 회원이 없습니다.");
            return;
          }
        }
      }
      System.out.println("입력하신 이름을 확인해 주세요.");
      return;
    }

    if (buyerSeller == 2) {
      String name = Prompt.inputString("이름을 입력하세요: ");
      if (name.equals("")) {
        System.out.println("다시 입력해주세요.");
        return;
      } 
      for (SellerPrivacy s : sellerList) {
        if (s.getName().equals(name)) {
          String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
          if (phoneOrEmail.equals("")) {
            System.out.println("다시 입력해주세요.");
            return;
          }
          if (s.getPhoneNumber().equals(phoneOrEmail) || (s.getEmail().equals(phoneOrEmail))) {
            System.out.printf("회원의 아이디는 %s 입니다.\n", s.getId());
            return;
          } else {
            System.out.println("입력하신 전화번호 또는 이메일에 해당하는 회원이 없습니다.");
            return;
          }
        }
      }
      System.out.println("입력하신 이름을 확인해 주세요.");
      return;
    }
  }


}

