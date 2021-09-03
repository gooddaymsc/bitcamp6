package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

  public MemberUpdateHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    super(memberList, sellerList);
  }

  @Override
  public void execute(int mem) {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_PRIVACY) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }
    System.out.println("[회원 변경]");

    if (mem == 1) {
      int no = Prompt.inputInt("회원번호 : ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s)", member.getNickname()));
      int level = Prompt.inputInt(String.format("등급(변경 전 : %d)", member.getLevel()));

      //      String buyerSeller = Prompt.inputString("구매자/판매자(" + member.getBuyerSeller()  + ")? ");
      // 관리자가 구매자를 판매자로 변경시키는 방법 구현 예정

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        member.setNickname(nickName);
        member.setLevel(level);
        //      member.setBuyerSeller(buyerSeller);
        System.out.println("회원 변경을 완료하였습니다.");
        return;
      }
      System.out.println("회원 변경을 취소하였습니다.");
      return;

    }

    if (mem == 2) {
      int no = Prompt.inputInt("판매자번호: ");

      Privacy member = findByNo2(no);

      if (member == null) {
        System.out.println("해당 번호의 판매자가 없습니다.");
        return;
      }

      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s)", member.getNickname()));
      int level = Prompt.inputInt(String.format("등급(변경 전 : %d)", member.getLevel()));
      //      String buyerSeller = Prompt.inputString("구매자/판매자(" + member.getBuyerSeller()  + ")? ");
      // 관리자가 판매자를 구매자로 변경시키는 방법 구현 예정

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        member.setNickname(nickName);
        member.setLevel(level);
        //      member.setBuyerSeller(buyerSeller);
        System.out.println("회원 변경(판매자)을 완료하였습니다.");
        return;
      }
      System.out.println("회원 변경(판매자)을 취소하였습니다.");
      return;

    }

    System.out.println("회원(판매자)을 변경하였습니다.");
  }
}






