package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerDeleteHandler extends AbstractBuyerHandler {
  MemberPrompt memberPrompt;
  CartPrompt cartPrompt;
  BookingPrompt bookingPrompt;
  MessagePrompt messagePrompt;
  public BuyerDeleteHandler(List<Member> memberList, MemberPrompt memberPrompt,
      CartPrompt cartPrompt, BookingPrompt bookingPrompt, MessagePrompt messagePrompt) {
    super(memberList);
    this.memberPrompt = memberPrompt;
    this.cartPrompt = cartPrompt;
    this.bookingPrompt = bookingPrompt;
    this.messagePrompt = messagePrompt;
  }
  @Override
  public void execute(CommandRequest request) {

    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[탈퇴하기]\n");
      Member buyer = (Buyer) request.getAttribute("buyer");
      String nowLoginId = buyer.getId();

      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) "); 

      if (input.equalsIgnoreCase("y")) {
        memberPrompt.removeMemberById(nowLoginId);
        cartPrompt.removeCartListById(nowLoginId);
        bookingPrompt.removeBookingListById(nowLoginId);
        messagePrompt.removeMessageListById(nowLoginId);
        System.out.println("탈퇴가 완료되었습니다.");
        // 현재로그인 상태 초기화
        App.loginMember = new Member();
        return;
      } else {
        System.out.println("탈퇴를 취소하였습니다.");
        return;
      } 
    } else {
      System.out.println("[회원 탈퇴]");
      Member buyer = (Buyer) request.getAttribute("buyer");
      String buyerId = buyer.getId();

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        memberPrompt.removeMemberById(buyerId);
        cartPrompt.removeCartListById(buyerId);
        bookingPrompt.removeBookingListById(buyerId);
        messagePrompt.removeMessageListById(buyerId);
        System.out.println("회원을 탈퇴시켰습니다.");
        return;
      }
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;

    }
  }
}






