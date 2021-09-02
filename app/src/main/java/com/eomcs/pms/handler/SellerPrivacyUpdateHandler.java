package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyUpdateHandler extends AbstractSellerPrivacyHandler {

  public SellerPrivacyUpdateHandler(List<SellerPrivacy> memberList, List<Manager> managerList) {
    super(memberList, managerList);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[판매자 변경]");
    String id = Prompt.inputString("번호를 입력하세요: ");

    SellerPrivacy member = findById(id);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("변경 후의 이름(" + member.getName()  + ")를 입력해주세요: ");
    String nickName = Prompt.inputString("변경 후의 닉네임(" + member.getNickname()  + ")를 입력해주세요: ");
    String email = Prompt.inputString("변경 후의 이메일(" + member.getEmail() + ")를 입력해주세요: ");
    Date birthDay = Prompt.inputDate("변경 후의 생일(" + member.getBirthday() + ")를 입력해주세요: ");
    String password = Prompt.inputString("변경 후의 암호를 입력해주세요: ");
    String photo = Prompt.inputString("변경 후의 사진(" + member.getPhoto() + ")를 입력해주세요: ");
    String tel = Prompt.inputString("변경 후의 전화(" + member.getPhoneNumber() + ")를 입력해주세요: ");
    String bussinessNo = Prompt.inputString("변경 후의 사업자번호(" + member.getBusinessNumber() + ")를 입력해주세요: ");
    String bussinessAddress = Prompt.inputString("변경 후의 사업장주소(" + member.getBusinessAddress() + ")를 입력해주세요: ");
    String bussinessTel = Prompt.inputString("변경 후의 사업장번호(" + member.getBusinessPlaceNumber() + ")를 입력해주세요: ");
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {     
      member.setName(name);
      member.setNickname(nickName);
      member.setEmail(email);
      member.setBirthday(birthDay);
      member.setPassword(password);
      member.setPhoto(photo);
      member.setPhoneNumber(tel);
      member.setBusinessNumber(bussinessNo);
      member.setBusinessAddress(bussinessAddress);
      member.setBusinessPlaceNumber(bussinessTel);  
      System.out.println("판매자 정보를 변경하였습니다.");
      return;
    } else {
      System.out.println("판매자 정보 변경을 취소하였습니다.");
      return;
    }
  }
}







