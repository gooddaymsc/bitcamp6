package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;


public class SellerDeleteHandler extends AbstractSellerHandler{

  public SellerDeleteHandler(List<Seller> sellerList, List<Member> memberList) {
    super(sellerList, memberList);
  }

  @Override
  public void execute() {
    String nowLoginId = App.getLoginUser().getId();

    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("\n[탈퇴하기]");
      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        sellerList.remove(removeSellerPrivateById(nowLoginId));
        memberList.remove(removeManagerById(nowLoginId));
        System.out.println("탈퇴가 완료되었습니다.");
        App.loginMember = new Member();
        return;
      } else {
        System.out.println("탈퇴를 취소하였습니다.");
        return;
      } 
    } else {
      String id = Prompt.inputString("삭제할 아이디 : ");
      Seller seller = findById(id);

      if (seller == null) {
        System.out.println("해당 아이디를 갖는 판매자가 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        sellerList.remove(removeSellerPrivateById(id));
        memberList.remove(removeManagerById(id));
        System.out.println("판매자를 탈퇴시켰습니다.");
        return;
      }
      System.out.println("판매자 탈퇴를 취소하였습니다.");
      return;
    }
  }

}







