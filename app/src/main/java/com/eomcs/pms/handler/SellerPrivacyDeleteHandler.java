package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyDeleteHandler extends AbstractSellerPrivacyHandler{
  List<Manager> managerList;
  public SellerPrivacyDeleteHandler(List<SellerPrivacy> memberList, List<Manager> managerList) {
    super(memberList);
    this.managerList = managerList;
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[판매자 삭제]");
    String id = Prompt.inputString("아이디를 입력하세요: ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디를 갖는 판매자가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {

      memberList.remove(member);
      managerList.remove(member);

      System.out.println("판매자를 삭제하였습니다.");
      return;
    } else {
      System.out.println("판매자를 삭제를 취소하였습니다.");
      return;
    } 
  }

}







