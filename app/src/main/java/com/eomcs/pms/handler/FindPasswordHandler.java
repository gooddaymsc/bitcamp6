package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class FindPasswordHandler {

  List<Privacy> memberList;
  List<SellerPrivacy> sellerList;

  public FindPasswordHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    this.memberList = memberList;
    this.sellerList = sellerList;
  }

  public void findPassword() {

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
          String id = Prompt.inputString("아이디를 입력해주세요: ");
          if (name.equals("")) {
            System.out.println("다시 입력해주세요.");
            return;
          }
          if (s.getId().equals(id)) {
            String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
            if (id.equals("")) {
              System.out.println("다시 입력해주세요.");
              return;
            }
            if (s.getPhoneNumber().equals(phoneOrEmail) || (s.getEmail().equals(phoneOrEmail))) {
              System.out.printf("회원의 비밀번호는 %s 입니다.\n", s.getPassword());
              return;
            } else {
              System.out.println("입력하신 전화번호 또는 이메일에 해당하는 회원이 없습니다.");
              return;
            }
          } else {
            System.out.println("아이디를 확인해주세요.");
            return;           
          }
        }
      }
      System.out.println("회원이 아닙니다.");
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
          String id = Prompt.inputString("아이디를 입력해주세요: ");
          if (name.equals("")) {
            System.out.println("다시 입력해주세요.");
            return;
          }
          if (s.getId().equals(id)) {
            String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
            if (id.equals("")) {
              System.out.println("다시 입력해주세요.");
              return;
            }
            if (s.getPhoneNumber().equals(phoneOrEmail) || (s.getEmail().equals(phoneOrEmail))) {
              System.out.printf("회원의 비밀번호는 %s 입니다.\n", s.getPassword());
              return;
            } else {
              System.out.println("입력하신 전화번호 또는 이메일에 해당하는 회원이 없습니다.");
              return;
            }
          } else {
            System.out.println("아이디를 확인해주세요.");
            return;           
          }
        }
      }
      System.out.println("회원이 아닙니다.");
      return;
    }
  }
}

