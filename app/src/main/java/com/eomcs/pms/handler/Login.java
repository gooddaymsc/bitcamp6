package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class Login {
  List<Privacy> memberList;

  public Login(List<Privacy> memberList) {
    this.memberList = memberList;
  }


  public void inputId() {
    String id = Prompt.inputString("아이디를 입력해주세요 : ");

    if (id == null) {
      System.out.println("해당 아이디는 없는 아이디입니다.");
      return;
    }

    for (Privacy s : memberList) {
      if (id.equals(s.getId())) {
        String password = Prompt.inputString("비밀번호를 입력해주세요 : ");

        if (password == null) {
          System.out.println("비밀번호가 일치하지 않습니다.");
          continue;
        }
        for (Privacy s2 : memberList) {
          if (password.equals(s2.getPassword())) {
            System.out.println("로그인이 완료되었습니다.");
            return;
          }
        }
        System.out.println("비밀번호가 일치하지 않습니다.");
        return;
      }
    }
    System.out.println("해당 아이디는 없는 아이디입니다.");
  }

}
