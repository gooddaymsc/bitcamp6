package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyDeleteHandler extends AbstractPrivacyHandler {

  List<Manager> managerList;
  public PrivacyDeleteHandler(List<Privacy> privacyList,  List<Manager> managerList) {
    super(privacyList);
    this.managerList = managerList;
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("\n[탈퇴하기]");

      Privacy member = findById(App.getLoginUser().getId());
      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) "); 

      if (input.equalsIgnoreCase("y")) {
        privacyList.remove(member);
        managerList.remove(member);    
        System.out.println("탈퇴가 완료되었습니다.");
        return;
      } else {
        System.out.println("탈퇴를 취소하였습니다.");
        return;
      } 
    } else {
      System.out.println("[회원 탈퇴]");
      String id = Prompt.inputString("삭제할 아이디: ");

      Privacy member = findById(id);

      if (member == null) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }
      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        privacyList.remove(member);
        managerList.remove(member);
        System.out.println("회원을 탈퇴시켰습니다.");
        return;
      }
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;

    }
  }
}






