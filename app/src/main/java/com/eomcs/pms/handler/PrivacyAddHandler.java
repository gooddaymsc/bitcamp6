package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyAddHandler extends AbstractPrivacyHandler {

  int privacyNumber = 1;
  List<Manager> managerList;

  public PrivacyAddHandler(List<Privacy> privacyList, List<Manager> managerList) {
    super(privacyList);
    this.managerList = managerList;
    Privacy testprivacy = new Privacy();
    Manager testmanager = new Manager();

    testprivacy.setNumber(privacyNumber++);
    testprivacy.setId("aa");
    testprivacy.setPassword("aa");
    testprivacy.setAuthority(Menu.ACCESS_PRIVACY);
    testprivacy.setName("aa");
    testprivacy.setNickname("aa");
    testprivacy.setEmail("aa");
    testprivacy.setBirthday(Date.valueOf("2021-1-1"));
    testprivacy.setPhoto("aa.gif");
    testprivacy.setAddress("서울 강남구");
    testprivacy.setPhoneNumber("010-1111-1111");
    testprivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    privacyList.add(testprivacy);

    testmanager.setId("aa");
    testmanager.setPassword("aa");
    testmanager.setAuthority(Menu.ACCESS_PRIVACY);
    managerList.add(testmanager);

    //일반회원을 생성할때 일반회원의 예약목록들을 저장할 list를 생성함.
    BookingList testBookingList = new BookingList();
    CartList testCartList = new CartList();
    testBookingList.setId(testprivacy.getId());
    App.allCartList.add(testCartList);
    testCartList.setId(testprivacy.getId());
    App.allBookingList.add(testBookingList);

    testprivacy = new Privacy();

    testprivacy.setNumber(privacyNumber++);
    testprivacy.setId("a");
    testprivacy.setPassword("a");
    testprivacy.setAuthority(Menu.ACCESS_PRIVACY);
    testprivacy.setName("a");
    testprivacy.setNickname("a");
    testprivacy.setEmail("a");
    testprivacy.setBirthday(Date.valueOf("2021-1-3"));
    testprivacy.setPhoto("aa.gif");
    testprivacy.setAddress("서울 동작구");
    testprivacy.setPhoneNumber("010-1111-1113");
    testprivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    privacyList.add(testprivacy);

    testmanager = new Manager();

    testmanager.setId("a");
    testmanager.setPassword("a");
    testmanager.setAuthority(Menu.ACCESS_PRIVACY);
    managerList.add(testmanager);

    testCartList = new CartList();
    testBookingList = new BookingList();
    testBookingList.setId(testprivacy.getId());
    App.allBookingList.add(testBookingList);
    testCartList.setId(testprivacy.getId());
    App.allCartList.add(testCartList);


  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_LOGOUT) {
      System.out.println("해당 메뉴는 로그아웃 후 가능합니다.");
      return;
    }

    System.out.println("\n[회원 등록]");

    Privacy privacy = new Privacy();
    privacy.setAuthority(Menu.ACCESS_PRIVACY);
    privacy.setNumber(privacyNumber++);

    //아이디가 중복되면 다시 아이디 재설정.
    String id = Prompt.inputString("등록할 아이디: ");

    int listSize = managerList.size();

    for (int i=0; i<listSize; i++) {

      if (managerList.get(i).getId().equals(id)) {
        System.out.println("중복되는 아이디입니다.");
        return;
      }
    }

    privacy.setId(id);

    privacy.setName(Prompt.inputString("이름: "));
    privacy.setNickname(Prompt.inputString("닉네임: "));
    privacy.setEmail(Prompt.inputString("이메일: "));
    privacy.setBirthday(Prompt.inputDate("생일: "));
    privacy.setPassword(Prompt.inputString("암호: "));
    privacy.setPhoto(Prompt.inputString("사진: "));
    privacy.setPhoneNumber(Prompt.inputString("전화: "));
    privacy.setAddress(Prompt.inputString("주소: "));
    privacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    BookingList BookingList = new BookingList();
    CartList CartList = new CartList();
    App.allBookingList.add(BookingList);
    App.allCartList.add(CartList);
    privacyList.add(privacy);
    managerList.add(new Manager(privacy.getId(), privacy.getPassword(), privacy.getAuthority()));
  }
}





