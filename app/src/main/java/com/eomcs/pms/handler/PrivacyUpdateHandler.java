package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyUpdateHandler extends AbstractPrivacyHandler {

  public PrivacyUpdateHandler(List<Privacy> privacyList) {
    super(privacyList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[회원 변경]");
    String id = Prompt.inputString("변경할 아이디: ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString(String.format("이름(변경 전 : %s) : ", member.getName()));
    String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s) : ", member.getNickname()));
    String email = Prompt.inputString(String.format("이메일(변경 전 : %s) : ", member.getEmail()));
    Date birthDay = Prompt.inputDate(String.format("생일(변경 전 : %s) : ", member.getBirthday()));
    String password = Prompt.inputString(String.format("암호(변경 전 : %s) : ", member.getPassword()));
    String photo = Prompt.inputString(String.format("사진(변경 전 : %s) : ", member.getPhoto()));
    String tel = Prompt.inputString(String.format("전화(변경 전 : %s) : ", member.getPhoneNumber()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      member.setName(name);
      member.setNickname(nickName);
      member.setEmail(email);
      member.setBirthday(birthDay);
      member.setPassword(password);
      member.setPhoto(photo);
      member.setPhoneNumber(tel);

      System.out.println("회원정보를 변경하였습니다.");
      return;
    } else {
      System.out.println("회원정보 변경을 취소하였습니다.");
      return;
    } 


  }
}







