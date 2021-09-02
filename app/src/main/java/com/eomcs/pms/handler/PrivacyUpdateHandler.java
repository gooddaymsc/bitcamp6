package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyUpdateHandler extends AbstractPrivacyHandler {

  public PrivacyUpdateHandler(List<Privacy> privacyList) {
    super(privacyList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[회원 변경]");
    String id = Prompt.inputString("변경할 아이디를 입력해주세요: ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("변경 전의 이름(" + member.getName()  + ") 변경 하실 이름을 입력해주세요. ");
    String nickName = Prompt.inputString("변경 전의 닉네임(" + member.getNickname()  + ") 변경 하실 닉네임 입력해주세요. ");
    String email = Prompt.inputString("변경 전의 이메일(" + member.getEmail() + ") 변경 하실 이메일주소를 입력해주세요. ");
    Date birthDay = Prompt.inputDate("변경 전의 생일(" + member.getBirthday() + ") 변경 하실 생일을 입력해주세요. ");
    String password = Prompt.inputString("변경 전의 암호(" + member.getPassword() + ") 변경 하실 암호를 입력해주세요. ");
    String photo = Prompt.inputString("변경 전의 사진(" + member.getPhoto() + ") 변경 하실 사진을 등록해주세요. ");
    String tel = Prompt.inputString("변경 전의 전화(" + member.getPhoneNumber() + ") 변경 하실 번호를 입력해주세요. ");

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







