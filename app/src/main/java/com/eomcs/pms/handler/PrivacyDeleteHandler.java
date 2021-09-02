package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyDeleteHandler extends AbstractPrivacyHandler {

  public PrivacyDeleteHandler(List<Privacy> memberList,  List<Manager> managerList) {
    super(memberList, managerList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[회원 삭제]");
    String id = Prompt.inputString("삭제할 아이디를 입력해주세요: ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }
    //  if (!loginPrivacy.getId().equals(member.getId()) && (loginPrivacy.getAuthority()==3)) {
    //      System.out.println("현재 로그인 된 아이디로 입력하여주세요.");
    //      return;
    //    }
    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) "); 

    if (input.equalsIgnoreCase("y")) {
      memberList.remove(member);
      managerList.remove(delMember(id));    
      System.out.println("회원을 삭제하였습니다.");
      return;
    } else {
      System.out.println("회원 삭제를 취소하였습니다.");
      return;
    } 

  }
}






