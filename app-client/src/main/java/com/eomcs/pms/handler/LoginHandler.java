package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class LoginHandler implements Command {
  // 횟수 관련 메서드 구현해야함.
  public static final int CHANCE_LOG = 5; //로그인 기회

  MemberDao memberDao;

  public LoginHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    String id = Prompt.inputString("아이디를 입력해주세요: ");
    String password = Prompt.inputString("비밀번호를 입력해주세요: ");

    //    if(id.equals("admin") && password.equals("0000")) {
    //      Member admin = new Member();
    //      admin.setId("admin");
    //      admin.setPassword("0000");
    //      admin.setAuthority(Menu.ACCESS_ADMIN);
    //      ClientApp.loginMember = admin;
    //      return;
    //    }

    Member member = memberDao.findByIdAndPassword(id, password);

    if (member != null) {
      System.out.printf("%s님 환영합니다!\n", member.getId());
      ClientApp.loginMember = member;
    } else {
      System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
    }
  }
}