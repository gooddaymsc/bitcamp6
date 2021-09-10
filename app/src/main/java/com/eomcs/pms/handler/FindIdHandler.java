package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class FindIdHandler {

  List<Buyer> memberList;
  List<Seller> sellerList;

  public FindIdHandler(List<Buyer> memberList, List<Seller> sellerList) {
    this.memberList = memberList;
    this.sellerList = sellerList;
  }

  public void findId() {

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
          String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
          if (phoneOrEmail.equals("")) {
            System.out.println("다시 입력해주세요.");
            return;
          }
          if (buyer.getPhoneNumber().equals(phoneOrEmail) || (buyer.getEmail().equals(phoneOrEmail))) {
            System.out.printf("회원의 아이디는 %s 입니다.\n", buyer.getId());
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

    if (member == 2) {
      String name = Prompt.inputString("이름을 입력하세요: ");
      if (name.equals("")) {
        System.out.println("다시 입력해주세요.");
        return;
      } 
      for (Seller seller : sellerList) {
        if (seller.getName().equals(name)) {
          String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
          if (phoneOrEmail.equals("")) {
            System.out.println("다시 입력해주세요.");
            return;
          }
          if (seller.getPhoneNumber().equals(phoneOrEmail) || (seller.getEmail().equals(phoneOrEmail))) {
            System.out.printf("회원의 아이디는 %s 입니다.\n", seller.getId());
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
