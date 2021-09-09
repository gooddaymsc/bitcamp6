package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerDeleteHandler extends AbstractBuyerHandler {
  List<Buyer> buyerList;
  List<Member> memberList;
  public BuyerDeleteHandler(List<Buyer> buyerList, List<Member> memberList) {
    this.buyerList = buyerList;
    this.memberList = memberList;
  }
  @Override
  public void execute() {
    String nowLoginId = App.getLoginUser().getId();
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("\n[탈퇴하기]");

      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) "); 

      if (input.equalsIgnoreCase("y")) {
        buyerList.remove(removePrivateById(nowLoginId));
        memberList.remove(removemanagerById(nowLoginId));
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
      String id = Prompt.inputString("삭제할 아이디: ");
      Member member = findById(id);
      if (member == null) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        buyerList.remove(removePrivateById(id));
        memberList.remove(removemanagerById(id));
        System.out.println("회원을 탈퇴시켰습니다.");
        return;
      }
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;

    }
  }
}






