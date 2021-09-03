package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class MemberDeleteHandler extends AbstractMemberHandler {

  public MemberDeleteHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    super(memberList, sellerList);
  }

  @Override
  public void execute(int mem) {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }
    System.out.println("[회원 탈퇴]");
    if (mem == 1) {
      int no = Prompt.inputInt("회원번호 : ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        memberList.remove(member);
        System.out.println("회원을 탈퇴시켰습니다.");
        return;
      }
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;

    }

    if (mem == 2) {
      int no = Prompt.inputInt("판매자번호 : ");

      Privacy member = findByNo2(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        sellerList.remove(member);
        System.out.println("판매자를 탈퇴시켰습니다.");
        return;
      }
      System.out.println("판매자 탈퇴를 취소하였습니다.");
      return;
    }
  }
}






