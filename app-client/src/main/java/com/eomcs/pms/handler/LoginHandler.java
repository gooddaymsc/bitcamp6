package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class LoginHandler implements Command {
  // 횟수 관련 메서드 구현해야함.
  public static final int CHANCE_LOG = 5; //로그인 기회

  RequestAgent requestAgent;
  List<Buyer> memberList;
  public LoginHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    String id = Prompt.inputString("아이디를 입력해주세요: ");
    String password = Prompt.inputString("비밀번호를 입력해주세요: ");

    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);
    params.put("password", password);

    requestAgent.request("buyer.selectOneByLogin", params);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      Buyer member = requestAgent.getObject(Buyer.class);
      System.out.printf("%s님 환영합니다!\n", member.getId());
      ClientApp.loginMember = member;
    } else {
      System.out.println(requestAgent.getObject(String.class));
    }
  }
}