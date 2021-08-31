package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class FindHandler {

  List<Privacy> memberList;
  List<SellerPrivacy> sellerList;

  public FindHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
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
      String phoneNumber = Prompt.inputString("전화번호를 입력하세요: ");
      if (phoneNumber == null) {
        System.out.println("다시 입력해주세요.");
        return;
      }
      for (Privacy s : memberList) {
        if (phoneNumber.equals(s.getPhoneNumber())) {
          System.out.printf("회원의 아이디는 %s 입니다.\n", s.getId());
          return;
        }
      }
      System.out.println("입력하신 전화번호는 회원이 아닙니다.");
      return;
    }

    if (buyerSeller == 2) {
      String phoneNumber = Prompt.inputString("전화번호를 입력하세요: ");
      if (phoneNumber == null) {
        System.out.println("다시 입력해주세요.");
        return;
      }
      for (Privacy s : sellerList) {
        if (phoneNumber.equals(s.getPhoneNumber())) {
          System.out.printf("회원의 아이디는 %s 입니다.\n", s.getId());
          return;
        }
      }
      System.out.println("입력하신 전화번호는 회원이 아닙니다.");
      return;
    }
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
        if (id.equals(s.getId())) {
          String email = Prompt.inputString("이메일을 입력해주세요: ");

          if (email.equals(s.getEmail())) {
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
        if (id.equals(s.getId())) {
          String email = Prompt.inputString("이메일을 입력해주세요: ");

          if (email.equals(s.getEmail())) {
            String businessNumber = Prompt.inputString("사업자번호를 입력해주세요: ");

            if (businessNumber.equals(s.getBusinessNumber())) {
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

