package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class LoginHandler {

  List<Member> memberList;
  public LoginHandler(List<Member> memberList) {
    this.memberList = memberList;
  }
  public Member InputId() {

    while (true) {
      String id = Prompt.inputString("아이디를 입력해주세요: ");

      if (id.equals("")) {
        System.out.println("다시 입력해주세요.\n");
        continue;
      }

      for (Member member : memberList) {
        if (id.equals(member.getId())) {
          int i = 0;
          while (i < 5) {

            String password = Prompt.inputString("비밀번호를 입력해주세요: ");

            if (password.equals("")) {
              System.out.println("다시 입력해주세요.\n");
              continue;
            }

            if (password.equals(member.getPassword())) {
              System.out.println("로그인이 완료되었습니다.\n");
              return member;
            } else {
              int a = (4 - i++);
              System.out.printf("비밀번호가 일치하지 않습니다. %d 회 남았습니다.\n", a);
              continue;
            }
          }
          System.out.println("비밀번호를 5회이상 틀리셨습니다.\n");
          return null;
        }
        System.out.println("가입된 아이디가 아닙니다.");
        return null;
      }
    }
  }
}