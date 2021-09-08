package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyUpdateHandler extends AbstractPrivacyHandler {

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("\n[개인정보 변경]");
      Privacy member = findById(App.getLoginUser().getId());

      String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s) : ", member.getNickname()));
      String email = Prompt.inputString(String.format("이메일(변경 전 : %s) : ", member.getEmail()));
      String password = Prompt.inputString(String.format("암호(변경 전 : %s) : ", member.getPassword()));
      String photo = Prompt.inputString(String.format("사진(변경 전 : %s) : ", member.getPhoto()));
      String address = Prompt.inputString(String.format("주소(변경 전 : %s) : ", member.getAddress()));
      String tel = Prompt.inputString(String.format("전화(변경 전 : %s) : ", member.getPhoneNumber()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        member.setNickname(nickName);
        member.setEmail(email);
        member.setPassword(password);
        member.setPhoto(photo);
        member.setAddress(address);
        member.setPhoneNumber(tel);


        System.out.println("개인정보를 변경하였습니다.");
        return;
      } else {
        System.out.println("개인정보 변경을 취소하였습니다.");
        return;
      } 
    } else {
      System.out.println("\n[회원 변경]");
      String id = Prompt.inputString("변경할 아이디: ");

      Privacy member = findById(id);

      if (member == null) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }
      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      int level = Prompt.inputInt(String.format("등급(변경 전 : %d)", member.getLevel()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        member.setLevel(level);
        System.out.println("회원 변경을 완료하였습니다.");
        return;
      }
      System.out.println("회원 변경을 취소하였습니다.");
      return;

    }
  }
}







