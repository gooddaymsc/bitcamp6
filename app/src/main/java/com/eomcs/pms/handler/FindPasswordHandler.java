package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class FindPasswordHandler {

  List<Buyer> memberList;
  List<Seller> sellerList;

  public FindPasswordHandler(List<Buyer> memberList, List<Seller> sellerList) {
    this.memberList = memberList;
    this.sellerList = sellerList;
  }

  public void findPassword() {

    int member = Prompt.inputInt("일반회원 1번, 판매자 2번 : ");

    if (member != 1 && member != 2) {
      System.out.println("다시 입력해주세요.");
      return;    
    }

    if (member == 1) {
      String name = Prompt.inputString("이름을 입력하세요: ");

      if (name.equals("")) {
        System.out.println("다시 입력해주세요.");
        return;
      }
      for (Buyer buyer : memberList) {
        if (buyer.getName().equals(name)) {
          String id = Prompt.inputString("아이디를 입력해주세요: ");
          if (name.equals("")) {
            System.out.println("다시 입력해주세요.");
            return;
          }
          if (buyer.getId().equals(id)) {
            String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
            if (id.equals("")) {
              System.out.println("다시 입력해주세요.");
              return;
            }
            if (buyer.getPhoneNumber().equals(phoneOrEmail) || (buyer.getEmail().equals(phoneOrEmail))) {
              System.out.printf("회원의 비밀번호는 %s 입니다.\n", buyer.getPassword());
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

    if (member == 2) {
      String name = Prompt.inputString("이름을 입력하세요: ");

      if (name.equals("")) {
        System.out.println("다시 입력해주세요.");
        return;
      }
      for (Seller seller : sellerList) {
        if (seller.getName().equals(name)) {
          String id = Prompt.inputString("아이디를 입력해주세요: ");
          if (name.equals("")) {
            System.out.println("다시 입력해주세요.");
            return;
          }
          if (seller.getId().equals(id)) {
            String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
            if (id.equals("")) {
              System.out.println("다시 입력해주세요.");
              return;
            }
            if (seller.getPhoneNumber().equals(phoneOrEmail) || (seller.getEmail().equals(phoneOrEmail))) {
              System.out.printf("회원의 비밀번호는 %s 입니다.\n", seller.getPassword());
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

