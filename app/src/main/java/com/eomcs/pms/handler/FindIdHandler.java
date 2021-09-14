package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class FindIdHandler implements Command {

  MemberPrompt memberPrompt;
  public FindIdHandler(MemberPrompt memberPrompt) {
    this.memberPrompt = memberPrompt;
  }

  @Override
  public void execute() {
    while(true) {
      String name = Prompt.inputString("이름을 입력하세요(취소 > 0): ");
      if (name.equals("0")) {
        return;
      }
      Member member = memberPrompt.findIdByName(name);

      if (member==null) {
        System.out.println("회원가입 된 이름이 아닙니다.\n");
        continue;
      }

      String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");
      if (member.getPhoneNumber().equals(phoneOrEmail) || (member.getEmail().equals(phoneOrEmail))) {
        System.out.printf("회원의 아이디는 %s 입니다.\n", member.getId());
        return;
      } else {
        System.out.println("입력하신 전화번호 또는 이메일에 해당하는 회원이 없습니다.\n");
        continue;
      }
    }
  }
}
