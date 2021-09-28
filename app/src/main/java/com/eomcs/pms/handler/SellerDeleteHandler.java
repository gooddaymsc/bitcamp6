package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;


public class SellerDeleteHandler extends AbstractSellerHandler{

  MemberPrompt memberPrompt;
  BookingPrompt bookingPrompt;
  StockPrompt stockPrompt;
  MessagePrompt messagePrompt;
  List<Member> deleteMemberList;

  public SellerDeleteHandler(List<Member> memberList,   List<Member> deleteMemberList, MemberPrompt memberPrompt, 
      BookingPrompt bookingPrompt, StockPrompt stockPrompt, MessagePrompt messagePrompt) {
    super(memberList);
    this.deleteMemberList = deleteMemberList;
    this.memberPrompt = memberPrompt;
    this.bookingPrompt = bookingPrompt;
    this.stockPrompt = stockPrompt;
    this.messagePrompt = messagePrompt;

  }

  @Override
  public void execute(CommandRequest request) {

    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[탈퇴하기]");
      Member seller = (Seller) request.getAttribute("seller");
      String nowLoginId = seller.getId();

      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        deleteMemberList.add(seller);
        memberPrompt.removeMemberById(nowLoginId);
        bookingPrompt.removeBookingListById(nowLoginId);
        stockPrompt.removeStockListById(nowLoginId);
        messagePrompt.removeMessageListById(nowLoginId);
        System.out.println("탈퇴가 완료되었습니다.\n");
        App.loginMember = new Member();
        return;
      } else {
        System.out.println("탈퇴를 취소하였습니다.\n");
        return;
      } 
    } else {
      System.out.println("[회원 탈퇴]");
      Member seller = (Seller) request.getAttribute("seller");
      String sellerId = seller.getId();

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        deleteMemberList.add(seller);
        memberPrompt.removeMemberById(sellerId);
        bookingPrompt.removeBookingListById(sellerId);
        stockPrompt.removeStockListById(sellerId);
        messagePrompt.removeMessageListById(sellerId);
        System.out.println("판매자를 탈퇴시켰습니다.\n");
        return;
      }
      System.out.println("판매자 탈퇴를 취소하였습니다.\n");
      return;
    }
  }

}







