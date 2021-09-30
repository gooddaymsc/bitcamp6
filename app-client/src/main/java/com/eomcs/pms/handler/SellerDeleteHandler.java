package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;


public class SellerDeleteHandler implements Command {

  RequestAgent requestAgent;

  public SellerDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[탈퇴하기]");

      String id = Prompt.inputString("아이디 >");
      HashMap<String, String> params = new HashMap<>();
      params.put("id", id);

      requestAgent.request("seller.selectOne", params);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      //      Member seller = (Seller) request.getAttribute("seller");
      //      String nowLoginId = seller.getId();

      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        //        deleteMemberList.add(seller);
        //        memberPrompt.removeMemberById(nowLoginId);
        //        bookingPrompt.removeBookingListById(nowLoginId);
        //        stockPrompt.removeStockListById(nowLoginId);
        //        messagePrompt.removeMessageListById(nowLoginId);
        requestAgent.request("seller.delete", params);
        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("회원 삭제 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }
        System.out.println("탈퇴가 완료되었습니다.\n");
        ClientApp.loginMember = new Member();
        return;
      } else {
        System.out.println("탈퇴를 취소하였습니다.\n");
        return;
      } 
    } else {
      System.out.println("[회원 탈퇴]");
      String id = Prompt.inputString("아이디 >");
      HashMap<String, String> params = new HashMap<>();
      params.put("id", id);

      requestAgent.request("seller.selectOne", params);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      //      Member seller = (Seller) request.getAttribute("seller");
      //      String sellerId = seller.getId();

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        requestAgent.request("seller.delete", params);
        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("회원 삭제 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
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
      return;
    }
  }

}







