package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyAddHandler extends AbstractPrivacyHandler {

  int privacyNumber = 1;

  @Override
  public void execute() {
    System.out.println("\n[회원 등록]");

    Privacy privacy = new Privacy();
    privacy.setAuthority(Menu.ACCESS_PRIVACY);
    privacy.setNumber(privacyNumber++);

    //아이디가 중복되면 다시 아이디 재설정.
    String id = Prompt.inputString("등록할 아이디: ");

    int listSize = App.managerList.size();

    for (int i=0; i<listSize; i++) {

      if (App.managerList.get(i).getId().equals(id)) {
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
    BookingList.setId(privacy.getId());
    App.allBookingList.add(BookingList);

    CartList CartList = new CartList();
    CartList.setId(privacy.getId());
    App.allCartList.add(CartList);

    App.privacyList.add(privacy);
    App.managerList.add(new Manager(privacy.getId(), privacy.getPassword(), privacy.getAuthority()));
  }
}





