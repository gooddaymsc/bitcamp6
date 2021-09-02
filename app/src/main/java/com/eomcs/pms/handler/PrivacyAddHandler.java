package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyAddHandler extends AbstractPrivacyHandler {

  static int size = 1;
  List<Manager> managerList;

  public PrivacyAddHandler(List<Privacy> privacyList, List<Manager> managerList) {
    super(privacyList);
    this.managerList = managerList;
    Privacy privacy = new Privacy();
    Manager manager = new Manager();

    privacy.setId("aa");
    privacy.setPassword("aa");
    privacy.setAuthority(1);
    privacy.setName("aa");
    privacy.setNickname("aa");
    privacy.setEmail("aa");
    privacy.setBirthday(Date.valueOf("2021-1-1"));
    privacy.setPhoto("aa.gif");
    privacy.setPhoneNumber("010-1111-1111");
    privacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    privacyList.add(privacy);

    manager.setId("aa");
    manager.setPassword("aa");
    manager.setAuthority(1);
    managerList.add(manager);

    privacy = new Privacy();
    privacy.setId("bb");
    privacy.setPassword("bb");
    privacy.setAuthority(1);
    privacy.setName("bb");
    privacy.setNickname("bb");
    privacy.setEmail("bb");
    privacy.setBirthday(Date.valueOf("2021-1-1"));
    privacy.setPhoto("bb.gif");
    privacy.setPhoneNumber("010-2222-2222");
    privacy.setRegisteredDate(new Date(System.currentTimeMillis()));

    privacyList.add(privacy);
    manager = new Manager();
    privacy.setId("bb");
    privacy.setPassword("bb");
    privacy.setAuthority(1);
    managerList.add(privacy);

    privacy = new Privacy();
    privacy.setId("cc");
    privacy.setPassword("cc");
    privacy.setAuthority(1);
    privacy.setName("cc");
    privacy.setNickname("cc");
    privacy.setEmail("cc");
    privacy.setBirthday(Date.valueOf("2021-1-1"));
    privacy.setPhoto("cc.gif");
    privacy.setPhoneNumber("010-3333-3333");
    privacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    privacyList.add(privacy);

    manager = new Manager();
    manager.setId("cc");
    manager.setPassword("cc");
    manager.setAuthority(1);
    managerList.add(manager);


  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != 0) {
      System.out.println("해당 메뉴는 로그아웃 후 가능합니다.");
      return;
    }

    System.out.println("\n[회원 등록]");

    Privacy privacy = new Privacy();
    privacy.setAuthority(1);
    privacy.setNumber(size++);

    //아이디가 중복되면 다시 아이디 재설정.
    String id = Prompt.inputString("아이디를 입력해주세요: ");

    int listSize = managerList.size();

    for (int i=0; i<listSize; i++) {

      if (managerList.get(i).getId().equals(id)) {
        System.out.println("중복되는 아이디입니다.");
        return;
      }
    }

    privacy.setId(id);
    privacy.setName(Prompt.inputString("이름을 입력해주세요: "));
    privacy.setNickname(Prompt.inputString("닉네임을 입력해주세요: "));
    privacy.setEmail(Prompt.inputString("이메일을 입력해주세요: "));
    privacy.setBirthday(Prompt.inputDate("생일을 입력해주세요: "));
    privacy.setPassword(Prompt.inputString("암호를 입력해주세요: "));
    privacy.setPhoto(Prompt.inputString("사진을 등록해주세요: "));
    privacy.setPhoneNumber(Prompt.inputString("전화를 입력해주세요: "));
    privacy.setRegisteredDate(new Date(System.currentTimeMillis()));

    privacyList.add(privacy);
    managerList.add(new Manager(privacy.getId(), privacy.getPassword(), privacy.getAuthority()));
  }
}





