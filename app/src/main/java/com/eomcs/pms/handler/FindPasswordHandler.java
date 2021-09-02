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
      String id = Prompt.inputString("아이디를 입력하세요: ");

      if (id == null) {
        System.out.println("다시 입력해주세요.");
        return;
      }
      for (Privacy s : memberList) {
        if (s.getId().equals(id)) {
          String email = Prompt.inputString("이메일을 입력해주세요: ");

          if (s.getEmail().equals(email)) {
            System.out.printf("회원의 비밀번호는 %s 입니다.\n", s.getPassword());
            return;

          } else {
            System.out.println("해당되는 이메일이 없습니다.");
            return;
          }
        } else {
          System.out.println("가입하신 아이디를 확인해주세요.");
          return;          
        }
      }
    }

    if (buyerSeller == 2) {
      String id = Prompt.inputString("아이디를 입력하세요: ");
      if (id == null) {
        System.out.println("다시 입력해주세요.");
        return;
      }
      for (SellerPrivacy s : sellerList) {
        if (s.getId().equals(id)) {
          String email = Prompt.inputString("이메일을 입력해주세요: ");

          if (s.getEmail().equals(email)) {
            String businessNumber = Prompt.inputString("사업자번호를 입력해주세요: ");
            if (s.getBusinessPlaceNumber().equals(businessNumber)) {
              System.out.printf("회원의 비밀번호는 %s 입니다.\n", s.getPassword());
              return;
            } else {
              System.out.println("해당되는 사업자번호가 없습니다.");
              return;              
            }

          } else {
            System.out.println("해당되는 이메일이 없습니다.");
            return;
          }

        } else {
          System.out.println("가입하신 아이디를 확인해주세요.");
          return;
        }
      }
    }
  }
}

