package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Manager;
import com.eomcs.util.Prompt;

public class LoginHandler {

  List<Manager> managerList;

  public LoginHandler(List<Manager> managerList) {
    this.managerList = managerList;
  }


  public Manager InputId() {
    String id = Prompt.inputString("아이디를 입력해주세요: ");

    if (id == null) {
      System.out.println("다시 입력해주세요.");
      return null;
    }

    for (Manager s : managerList) {
      if (id.equals(s.getId())) {
        String password = Prompt.inputString("비밀번호를 입력해주세요: ");

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
}