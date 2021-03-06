package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;


public class SellerDeleteHandler implements Command {
  SellerDao sellerDao;
  SqlSession sqlSession;
  public SellerDeleteHandler(SellerDao sellerDao, SqlSession sqlSession) {
    this.sellerDao = sellerDao;
    this.sqlSession = sqlSession;
  } 

  @Override
  public void execute(CommandRequest request) throws Exception {

    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[탈퇴하기]");
      String id = ClientApp.getLoginUser().getId();
      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        Seller seller = sellerDao.findById(id);

        try {
          sellerDao.delete(seller.getMember().getNumber());
          sellerDao.deleteSeller(seller.getMember().getNumber());
          sqlSession.commit();
        }   catch (Exception e) {
          sqlSession.rollback();
        }
        //        deleteMemberList.add(seller);
        //        memberPrompt.removeMemberById(nowLoginId);
        //        bookingPrompt.removeBookingListById(nowLoginId);
        //        stockPrompt.removeStockListById(nowLoginId);
        //        messagePrompt.removeMessageListById(nowLoginId);
        System.out.println("탈퇴가 완료되었습니다.\n");
        ClientApp.loginMember = new Member();
        return;
      }
      System.out.println("탈퇴를 취소하였습니다.\n");

    } else {
      System.out.println("[회원 탈퇴]");
      String id = (String) request.getAttribute("id");
      //      String nowLoginId = seller.getId();

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        Seller seller = sellerDao.findById(id);
        try {
          sellerDao.delete(seller.getMember().getNumber());
          sellerDao.deleteSeller(seller.getMember().getNumber());
          sqlSession.commit();
        }   catch (Exception e) {
          sqlSession.rollback();
        }
        //        deleteMemberList.add(seller);
        //        memberPrompt.removeMemberById(sellerId);
        //        bookingPrompt.removeBookingListById(sellerId);
        //        stockPrompt.removeStockListById(sellerId);
        //        messagePrompt.removeMessageListById(sellerId);
        System.out.println("판매자를 탈퇴시켰습니다.\n");
        return;
      }
      System.out.println("판매자 탈퇴를 취소하였습니다.\n");
    }
  }
}






