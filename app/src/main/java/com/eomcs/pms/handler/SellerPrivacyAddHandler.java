package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyAddHandler extends AbstractSellerPrivacyHandler {

  int sellerPrivacyNumber = 1;
  List<Manager> managerList;

  public SellerPrivacyAddHandler(List<SellerPrivacy> sellerList, List<Manager> managerList) {
    super(sellerList);
    this.managerList = managerList;
    SellerPrivacy testsellerPrivacy = new SellerPrivacy();
    Manager testmanager = new Manager();

    testsellerPrivacy.setNumber(sellerPrivacyNumber++);
    testsellerPrivacy.setId("aaaa");
    testsellerPrivacy.setPassword("aaaa");
    testsellerPrivacy.setAuthority(Menu.ACCESS_SELLER);
    testsellerPrivacy.setName("aaaa");
    testsellerPrivacy.setNickname("aaaa");
    testsellerPrivacy.setEmail("aaaa");
    testsellerPrivacy.setBirthday(Date.valueOf("2021-1-1"));
    testsellerPrivacy.setPhoto("aaaa.gif");
    testsellerPrivacy.setPhoneNumber("010-1111-1111");
    testsellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    testsellerPrivacy.setBusinessNumber("aaaa");
    testsellerPrivacy.setBusinessAddress("aaaa");
    testsellerPrivacy.setBusinessPlaceNumber("010-1111-1111");

    sellerList.add(testsellerPrivacy);

    testmanager.setId("aaaa");
    testmanager.setPassword("aaaa");
    testmanager.setAuthority(Menu.ACCESS_SELLER);
    managerList.add(testmanager);

    testsellerPrivacy = new SellerPrivacy();

    testsellerPrivacy.setNumber(sellerPrivacyNumber++);
    testsellerPrivacy.setId("aaa");
    testsellerPrivacy.setPassword("aaa");
    testsellerPrivacy.setAuthority(Menu.ACCESS_SELLER);
    testsellerPrivacy.setName("aaa");
    testsellerPrivacy.setNickname("aaa");
    testsellerPrivacy.setEmail("aaa");
    testsellerPrivacy.setBirthday(Date.valueOf("2021-1-2"));
    testsellerPrivacy.setPhoto("aaaa.gif");
    testsellerPrivacy.setPhoneNumber("010-1111-1112");
    testsellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    testsellerPrivacy.setBusinessNumber("aaa");
    testsellerPrivacy.setBusinessAddress("aaa");
    testsellerPrivacy.setBusinessPlaceNumber("010-1111-1112");

    sellerList.add(testsellerPrivacy);

    testmanager = new Manager();
    testmanager.setId("aaa");
    testmanager.setPassword("aaa");
    testmanager.setAuthority(Menu.ACCESS_SELLER);
    managerList.add(testmanager);
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_LOGOUT) {
      System.out.println("해당 메뉴는 로그아웃 후 가능합니다.");
      return;
    }
    System.out.println("\n[판매자 등록]");

    SellerPrivacy sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setAuthority(0x04);
    sellerPrivacy.setNumber(sellerPrivacyNumber++);

    String id = Prompt.inputString("등록할 아이디: ");

    int listSize = managerList.size();

    for (int i = 0; i < listSize; i++) {
      if (managerList.get(i).getId().equals(id)) {
        System.out.println("중복되는 아이디입니다.");
        return;
      }
    }

    sellerPrivacy.setId(id);

    sellerPrivacy.setName(Prompt.inputString("이름 : "));
    sellerPrivacy.setNickname(Prompt.inputString("닉네임 : "));
    sellerPrivacy.setEmail(Prompt.inputString("이메일 : "));
    sellerPrivacy.setBirthday(Prompt.inputDate("생일 : "));
    sellerPrivacy.setPassword(Prompt.inputString("암호 : "));
    sellerPrivacy.setPhoto(Prompt.inputString("사진 : "));
    sellerPrivacy.setPhoneNumber(Prompt.inputString("전화 : "));
    sellerPrivacy.setBusinessNumber(Prompt.inputString("사업자번호 : "));
    sellerPrivacy.setBusinessAddress(Prompt.inputString("사업장주소 : "));
    sellerPrivacy.setBusinessPlaceNumber(Prompt.inputString("사업장번호 : "));
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    sellerList.add(sellerPrivacy);
    managerList.add(new Manager(sellerPrivacy.getId(), sellerPrivacy.getPassword(), sellerPrivacy.getAuthority()));
  }
}








