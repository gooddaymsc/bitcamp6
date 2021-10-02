package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerDeleteHandler implements Command {
  BuyerDao buyerDao;
  public BuyerDeleteHandler (BuyerDao buyerDao) {
    this.buyerDao = buyerDao;
  } 
  @Override
  public void execute(CommandRequest request) throws Exception {

    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[탈퇴하기]");
      String id = ClientApp.getLoginUser().getId();

      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) "); 

      if (input.equalsIgnoreCase("y")) {
        //        deleteMemberList.add(buyer);
        //        memberPrompt.removeMemberById(nowLoginId);
        //        cartPrompt.removeCartListById(nowLoginId);
        //        bookingPrompt.removeBookingListById(nowLoginId);
        //        messagePrompt.removeMessageListById(nowLoginId);
        buyerDao.delete(id);
        System.out.println("탈퇴가 완료되었습니다.\n");
        // 현재로그인 상태 초기화
        ClientApp.loginMember = new Member();
      } 
      System.out.println("탈퇴를 취소하였습니다.\n");
    } else {
      System.out.println("[회원 탈퇴]");
      String id = (String) request.getAttribute("id");

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {

        //        deleteMemberList.add(buyer);
        //        memberPrompt.removeMemberById(buyerId);
        //        cartPrompt.removeCartListById(buyerId);
        //        bookingPrompt.removeBookingListById(buyerId);
        //        messagePrompt.removeMessageListById(buyerId);
        buyerDao.delete(id);
        System.out.println("회원을 탈퇴시켰습니다.\n");
        return;
      }
    }
  }
}






