package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BuyerDeleteHandler implements Command {
  RequestAgent requestAgent;
  public BuyerDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {

    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[탈퇴하기]");
      String id = ClientApp.getLoginUser().getId();

      HashMap<String, String> params = new HashMap<>();
      params.put("id", id);
      //      String nowLoginId = buyer.getId();

      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) "); 

      if (input.equalsIgnoreCase("y")) {
        //        deleteMemberList.add(buyer);
        //        memberPrompt.removeMemberById(nowLoginId);
        //        cartPrompt.removeCartListById(nowLoginId);
        //        bookingPrompt.removeBookingListById(nowLoginId);
        //        messagePrompt.removeMessageListById(nowLoginId);
        requestAgent.request("buyer.delete", params);
        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("탈퇴 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }
        System.out.println("탈퇴가 완료되었습니다.\n");
        // 현재로그인 상태 초기화
        ClientApp.loginMember = new Member();
      } 
    } else {
      System.out.println("[회원 탈퇴]");
      String id = ((Buyer) request.getAttribute("buyer")).getId();
      HashMap<String, String> params = new HashMap<>();
      params.put("id", id);
      //      String buyerId = buyer.getId();

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        requestAgent.request("buyer.delete", params);
        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("회원 삭제 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }
        //        deleteMemberList.add(buyer);
        //        memberPrompt.removeMemberById(buyerId);
        //        cartPrompt.removeCartListById(buyerId);
        //        bookingPrompt.removeBookingListById(buyerId);
        //        messagePrompt.removeMessageListById(buyerId);
        System.out.println("회원을 탈퇴시켰습니다.\n");
        return;
      }
    }
  }
}






