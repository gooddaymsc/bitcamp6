package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.util.Prompt;

public class BuyerLoginHandler implements Command {
  // 횟수 관련 메서드 구현해야함.
  public static final int CHANCE_LOG = 5; //로그인 기회

  BuyerDao buyerDao;

  public BuyerLoginHandler(BuyerDao buyerDao) {
    this.buyerDao = buyerDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    String id = Prompt.inputString("아이디를 입력해주세요: ");
    String password = Prompt.inputString("비밀번호를 입력해주세요: ");


    Buyer buyer = buyerDao.login(id, password);

    System.out.printf("%s님 환영합니다!\n", buyer.getId());
    ClientApp.loginMember = buyer;
  }

}