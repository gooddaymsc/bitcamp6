package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerUpdateHandler implements Command {
  SellerDao sellerDao;
  SqlSession sqlSession;

  public SellerUpdateHandler(SellerDao sellerDao, SqlSession sqlSession) {
    this.sellerDao = sellerDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 변경]");
      String id = ClientApp.getLoginUser().getId();

      Seller seller = sellerDao.findById(id);

      String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s) : ", seller.getMember().getNickname()));
      String email = Prompt.inputString(String.format("이메일(변경 전 : %s) : ", seller.getMember().getEmail()));
      String password = Prompt.inputString(String.format("암호(변경 전 : %s) : ", seller.getMember().getPassword()));
      String photo = Prompt.inputString(String.format("사진(변경 전 : %s) : ", seller.getMember().getPhoto()));
      String tel = Prompt.inputString(String.format("전화(변경 전 : %s) : ", seller.getMember().getPhoneNumber()));
      String bussinessName = Prompt.inputString(String.format("가게명(변경 전 : %s) : ", seller.getBusinessName()));
      String bussinessNo = Prompt.inputString(String.format("사업자번호(변경 전 : %s) : ", seller.getBusinessNumber()));
      String bussinessAddress = Prompt.inputString(String.format("사업장주소(변경 전 : %s) : ", seller.getBusinessAddress()));
      String bussinessTel = Prompt.inputString(String.format("사업장번호(변경 전 : %s) : ", seller.getBusinessPlaceNumber()));
      String BusinessOpeningTimes = SellerValidation.openingTime(String.format("오픈시간(변경 전: %s) : ", seller.getBusinessOpeningTime()));
      String BusinessClosingTimes = SellerValidation.closingTime(String.format("마감시간(변경 전: %s) : ", seller.getBusinessClosingTime()));    

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {     
        seller.getMember().setNickname(nickName);
        seller.getMember().setEmail(email);
        seller.getMember().setPassword(password);
        seller.getMember().setPhoto(photo);
        seller.getMember().setPhoneNumber(tel);
        seller.setBusinessName(bussinessName);
        seller.setBusinessNumber(bussinessNo);
        seller.setBusinessAddress(bussinessAddress);
        seller.setBusinessPlaceNumber(bussinessTel);  
        seller.setBusinessOpeningTime(BusinessOpeningTimes);
        seller.setBusinessClosingTime(BusinessClosingTimes);

        try {
          sellerDao.update(seller.getMember());
          sellerDao.updateSeller(seller);
          sqlSession.commit();
        }   catch (Exception e) {
          sqlSession.rollback();
        }

        System.out.println("개인 정보를 변경하였습니다.\n");
        return;
      }
      System.out.println("개인 정보 변경을 취소하였습니다.\n");
    } else {
      System.out.println("[판매자 변경]");
      String id = (String) request.getAttribute("id");

      Seller seller = sellerDao.findById(id);

      int level = SellerValidation.checkLevel(String.format("등급(변경 전 : %d) : ", seller.getMember().getLevel())); 
      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        seller.getMember().setLevel(level);
        sellerDao.updateLevel(seller.getMember());
        sqlSession.commit();
        System.out.println("회원정보를 변경했습니다.\n");
        return;
      }
      System.out.println("회원정보 변경을 취소하였습니다.\n");
    }
  }


}
