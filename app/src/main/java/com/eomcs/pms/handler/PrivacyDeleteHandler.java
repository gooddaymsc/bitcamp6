package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.util.Prompt;

public class PrivacyDeleteHandler extends AbstractPrivacyHandler {

  @Override
  public void execute() {
    String nowLoginId = App.getLoginUser().getId();
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("\n[탈퇴하기]");

      String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) "); 

      if (input.equalsIgnoreCase("y")) {
        App.privacyList.remove(removePrivateById(nowLoginId));
        App.managerList.remove(removemanagerById(nowLoginId));
        System.out.println("탈퇴가 완료되었습니다.");
        // 현재로그인 상태 초기화
        App.loginPrivacy = new Manager();
        return;
      } else {
        System.out.println("탈퇴를 취소하였습니다.");
        return;
      } 
    } else {
      System.out.println("[회원 탈퇴]");
      String id = Prompt.inputString("삭제할 아이디: ");
      Manager member = findById(id);
      if (member == null) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        App.privacyList.remove(removePrivateById(id));
        App.managerList.remove(removemanagerById(id));
        System.out.println("회원을 탈퇴시켰습니다.");
        return;
      }
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;

    }
  }
}






