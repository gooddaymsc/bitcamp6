package com.eomcs.pms.handler;


import java.util.List;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;


public class LoginHandler {
  List<Privacy> memberList;
  List<SellerPrivacy> sellerList;
  Manager manager = new Manager("관리자","1234",3);

  public LoginHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    this.memberList = memberList;
    this.sellerList = sellerList;
  }


  public Privacy memberInputId() {
    String id = Prompt.inputString("아이디를 입력해주세요 : ");

    if (id == null) {
      System.out.println("다시 입력해주세요.");
      return null;
    }

    for (Privacy s : memberList) {
      if (id.equals(s.getId())) {
        String password = Prompt.inputString("비밀번호를 입력해주세요 : ");

        if (password.equals(s.getPassword())) {
          System.out.println("로그인이 완료되었습니다.");
          return s;
        } else {
          System.out.println("비밀번호가 일치하지 않습니다.");
          return null;
        }
      }
    }
    System.out.println("해당 아이디는 없는 아이디입니다.");
    return null;
  }

  public Privacy sellerInputId() {
    String id = Prompt.inputString("아이디를 입력해주세요 : ");

    if (id == null) {
      System.out.println("다시 입력해주세요.");
      return null;
    }

    for (Privacy s : sellerList) {
      if (id.equals(s.getId())) {
        String password = Prompt.inputString("비밀번호를 입력해주세요 : ");

        if (password.equals(s.getPassword())) {
          System.out.println("로그인이 완료되었습니다.");
          return s;
        } else {
          System.out.println("비밀번호가 일치하지 않습니다.");
          return null;
        }
      }
    }
    System.out.println("해당 아이디는 없는 아이디입니다.");
    return null;
  }

  public Manager managerInputId() {
    String id = Prompt.inputString("아이디를 입력해주세요 : ");

    if (manager.getId().equals(id)) {
      String password = Prompt.inputString("비밀번호를 입력해주세요 : ");
      if (manager.getPassword().equals(password)) {
        return manager;
      } else {
        System.out.println("비밀번호가 일치하지 않습니다.");
        return null;
      }
    } else {
      System.out.println("해당 아이디는 없는 아이디입니다.");
      return null;
    }
  }
}