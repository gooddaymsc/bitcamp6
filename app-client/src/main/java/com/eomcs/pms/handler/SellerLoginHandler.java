package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerLoginHandler implements Command {
  // 횟수 관련 메서드 구현해야함.
  public static final int CHANCE_LOG = 5; //로그인 기회

  SellerDao sellerDao;
  public SellerLoginHandler(SellerDao sellerDao) {
    this.sellerDao = sellerDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    String id = Prompt.inputString("아이디를 입력해주세요: ");
    String password = Prompt.inputString("비밀번호를 입력해주세요: ");


    Seller seller = sellerDao.login(id, password);
    if (seller == null) {
      return;
    }
    System.out.printf("%s님 환영합니다!\n", seller.getId());
    ClientApp.loginMember = seller;
  }
}