package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class LoginHandler {

  public static final int CHANCE_LOG = 5; //로그인 기회

  List<Member> memberList;

  public LoginHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  public Member InputId() {

    Loop: while (true) {
      String id = Prompt.inputString("아이디를 입력해주세요: ");

      if (id.equals("")) {
        System.out.println("다시 입력해주세요.\n");
        continue;
      }

      for (Member member : memberList) {
        if (id.equals(member.getId())) {
          int i = 0;
          while (i < CHANCE_LOG) {

            String password = Prompt.inputString("비밀번호를 입력해주세요: ");

            if (password.equals("")) {
              System.out.println("다시 입력해주세요.\n");
              continue;
            }

            if (password.equals(member.getPassword())) {
              System.out.println("로그인이 완료되었습니다.\n");
              return member;
            } else if (i < 4) {
              System.out.println();
              System.out.printf("비밀번호가 일치하지 않습니다. (%d 회 남았습니다.)\n", (4-i++));
              System.out.println("--------------------------------------------------");
              continue;
            } else {
              System.out.println();
              System.out.println("비밀번호가 일치하지 않습니다.");
              System.out.println("--------------------------------------------------");
              break;
            }
          }
          System.out.println("비밀번호를 5회이상 틀려 개인정보를 확인하겠습니다.\n");

          Member checkMember = reCheckName(id);
          if (checkMember == null) {
            return null;
          } else {
            System.out.printf("%s 회원님 아이디와 비번을 다시 확인하시고 입력해주세요.\n",
                checkMember.getName());
            System.out.println("--------------------------------------------------");
            continue Loop;
          }
        }
      }
      System.out.println("가입된 아이디가 아닙니다.");
      return null;
    }
  }

  public Member reCheckName(String id) {

    while (true) {
      String name = Prompt.inputString("이름을 입력해주세요: ");

      if (name.equals("")) {
        System.out.println("다시 입력해주세요.\n");
        continue;
      }

      for (Member member : memberList) {
        if (member.getId().equals(id)) {
          if (member.getName().equals(name)) {
            return member;
          } else {
            System.out.println("입력하신 아이디의 회원 이름과 일치하지 않습니다.\n");
            return null;
          }}}
    }
  }
}