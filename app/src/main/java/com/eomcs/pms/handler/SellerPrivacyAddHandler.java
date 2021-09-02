package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyAddHandler extends AbstractSellerPrivacyHandler {

  public SellerPrivacyAddHandler(List<SellerPrivacy> memberList, List<Manager> managerList) {
    super(memberList, managerList);

    SellerPrivacy sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setId("aaaa");
    sellerPrivacy.setPassword("aaaa");
    sellerPrivacy.setAuthority(2);
    sellerPrivacy.setName("aaaa");
    sellerPrivacy.setNickname("aaaa");
    sellerPrivacy.setEmail("aaaa");
    sellerPrivacy.setBirthday(Date.valueOf("2021-1-1"));
    sellerPrivacy.setPhoto("aaaa.gif");
    sellerPrivacy.setPhoneNumber("010-1111-1111");
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(sellerPrivacy);

    sellerPrivacy.setId("aaaa");
    sellerPrivacy.setPassword("aaaa");
    sellerPrivacy.setAuthority(2);
    managerList.add(sellerPrivacy);

    sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setId("bbbb");
    sellerPrivacy.setPassword("bbbb");
    sellerPrivacy.setAuthority(2);
    sellerPrivacy.setName("bbbb");
    sellerPrivacy.setNickname("bbbb");
    sellerPrivacy.setEmail("bbbb");
    sellerPrivacy.setBirthday(Date.valueOf("2021-1-1"));
    sellerPrivacy.setPhoto("bbbb.gif");
    sellerPrivacy.setPhoneNumber("010-2222-2222");
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(sellerPrivacy);

    sellerPrivacy.setId("bbbb");
    sellerPrivacy.setPassword("bbbb");
    sellerPrivacy.setAuthority(2);
    managerList.add(sellerPrivacy);

    sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setId("cccc");
    sellerPrivacy.setPassword("cccc");
    sellerPrivacy.setAuthority(2);
    sellerPrivacy.setName("cccc");
    sellerPrivacy.setNickname("cccc");
    sellerPrivacy.setEmail("cccc");
    sellerPrivacy.setBirthday(Date.valueOf("2021-1-1"));
    sellerPrivacy.setPhoto("cccc.gif");
    sellerPrivacy.setPhoneNumber("010-3333-3333");
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(sellerPrivacy);

    sellerPrivacy.setId("cccc");
    sellerPrivacy.setPassword("cccc");
    sellerPrivacy.setAuthority(2);
    managerList.add(sellerPrivacy);

    sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setId("dddd");
    sellerPrivacy.setPassword("dddd");
    sellerPrivacy.setAuthority(2);
    sellerPrivacy.setName("dddd");
    sellerPrivacy.setNickname("dddd");
    sellerPrivacy.setEmail("dddd");
    sellerPrivacy.setBirthday(Date.valueOf("2021-1-1"));
    sellerPrivacy.setPhoto("dddd.gif");
    sellerPrivacy.setPhoneNumber("010-4444-4444");
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(sellerPrivacy);

    sellerPrivacy.setId("dddd");
    sellerPrivacy.setPassword("dddd");
    sellerPrivacy.setAuthority(2);
    managerList.add(sellerPrivacy);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 1 || App.getLoginUser().getAuthority() == 2 
        || App.getLoginUser().getAuthority() == 3) {
      System.out.println("해당 메뉴는 로그아웃 후 가능합니다.");
      return;
    }
    System.out.println("\n[판매자 등록]");

    SellerPrivacy sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setAuthority(2);
    sellerPrivacy.setNumber(size++);
    //sellerPrivacy.setNumber(Prompt.inputInt("번호를 입력하세요: "));

    String id = Prompt.inputString("아이디를 입력하세요: ");
    int size = managerList.size();
    if (size!=0) {
      Manager checkmanager = addMember(id, size);
      if (checkmanager != null) {
        return;
      }
    }
    sellerPrivacy.setId(id);

    sellerPrivacy.setName(Prompt.inputString("이름을 입력하세요: "));
    sellerPrivacy.setNickname(Prompt.inputString("닉네임을 입력하세요: "));
    sellerPrivacy.setEmail(Prompt.inputString("이메일을 입력하세요: "));
    sellerPrivacy.setBirthday(Prompt.inputDate("생일을 입력하세요: "));
    sellerPrivacy.setPassword(Prompt.inputString("암호를 입력하세요: "));
    sellerPrivacy.setPhoto(Prompt.inputString("사진을 등록하세요: "));
    sellerPrivacy.setPhoneNumber(Prompt.inputString("전화를 입력하세요: "));
    sellerPrivacy.setBusinessNumber(Prompt.inputString("사업자번호를 입력하세요: "));
    sellerPrivacy.setBusinessAddress(Prompt.inputString("사업장주소를 입력하세요: "));
    sellerPrivacy.setBusinessPlaceNumber(Prompt.inputString("사업장번호를 입력하세요: "));
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(sellerPrivacy);
    managerList.add(new Manager(sellerPrivacy.getId(), sellerPrivacy.getPassword(), sellerPrivacy.getAuthority()));

  }
}







