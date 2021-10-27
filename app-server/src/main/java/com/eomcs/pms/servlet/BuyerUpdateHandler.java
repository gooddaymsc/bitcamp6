package com.eomcs.pms.servlet;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.util.Prompt;

public class BuyerUpdateHandler implements Command {
  BuyerDao buyerDao;
  SqlSession sqlSession;
  public BuyerUpdateHandler (BuyerDao buyerDao, SqlSession sqlSession) {
    this.buyerDao = buyerDao;
    this.sqlSession = sqlSession;
  } 
  @Override
  public void execute(CommandRequest request) throws Exception {
    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 변경]");
      String id = ClientApp.getLoginUser().getId();

      Buyer buyer = buyerDao.findById(id);

      String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s) : ", buyer.getMember().getNickname()));
      String email = Prompt.inputString(String.format("이메일(변경 전 : %s) : ", buyer.getMember().getEmail()));
      String password = Prompt.inputString(String.format("암호(변경 전 : %s) : ", buyer.getMember().getPassword()));
      String photo = Prompt.inputString(String.format("사진(변경 전 : %s) : ", buyer.getMember().getPhoto()));
      String address = Prompt.inputString(String.format("주소(변경 전 : %s) : ", buyer.getAddress()));
      String tel = Prompt.inputString(String.format("전화(변경 전 : %s) : ", buyer.getMember().getPhoneNumber()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        buyer.getMember().setNickname(nickName);
        buyer.getMember().setEmail(email);
        buyer.getMember().setPassword(password);
        buyer.getMember().setPhoto(photo);
        buyer.setAddress(address);
        buyer.getMember().setPhoneNumber(tel);
        buyerDao.update(buyer);
        sqlSession.commit();
        System.out.println("개인정보를 변경하였습니다.\n");
        return;
      } 
      System.out.println("개인정보 변경을 취소하였습니다.\n");
    } else {
      System.out.println("[회원 변경]\n");
      String id = (String) request.getAttribute("id");

      Buyer buyer = buyerDao.findById(id);


      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      int level =BuyerValidation.checkLevel(String.format("등급(변경 전 : %d) : ", buyer.getMember().getLevel())); 
      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        buyer.getMember().setLevel(level);
        buyerDao.update(buyer);
        sqlSession.commit();
        System.out.println("회원정보를 변경했습니다.\n");
        return;
      }
      System.out.println("회원정보 변경을 취소하였습니다.\n");
    }
  }
}







