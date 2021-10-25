package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class UpdatePasswordHandler implements Command {

  MemberDao memberDao;
  SqlSession sqlSession;

  public UpdatePasswordHandler(MemberDao memberDao, SqlSession sqlSession) {
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    while (true) {
      String name = Prompt.inputString("이름을 입력하세요 / 취소(0) : ");
      if (name.equals("0")) {
        return;
      }
      Member member = memberDao.findByName(name);
      if (member==null) {
        System.out.println("회원가입 된 이름이 아닙니다.\n");
        continue;
      }

      String id = Prompt.inputString("아이디를 입력해주세요: ");
      if (!member.getId().equals(id)) {
        System.out.println("아이디가 일치하지 않습니다.\n");
        continue;
      }

      String phoneOrEmail = Prompt.inputString("전화번호 또는 이메일을 입력하세요: ");

      if (member.getPhoneNumber().equals(phoneOrEmail) || (member.getEmail().equals(phoneOrEmail))) {
        String password = Prompt.inputString(String.format("변경할 비밀번호를 입력해주세요 :"));
        member.setPassword(password);
        memberDao.update(member);
        sqlSession.commit();
        System.out.println("비밀번호 변경이 완료되었습니다.");
        return;
      } else {
        System.out.println("입력하신 전화번호 또는 이메일에 해당하는 회원이 없습니다.\n");
        continue;
      }
    }
  }
}

