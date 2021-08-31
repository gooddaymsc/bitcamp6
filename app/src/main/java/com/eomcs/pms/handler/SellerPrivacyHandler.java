package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyHandler {

  List<SellerPrivacy> memberList;
  List<Manager> managerList;
  int size=1;

  public SellerPrivacyHandler(List<SellerPrivacy> memberList, List<Manager> managerList) {
    this.memberList = memberList;
    this.managerList = managerList;

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


  public void sellerAdd(int i, int auth) {
    if (auth== 1 || auth == 2 || auth == 3) {
      System.out.println("해당 메뉴는 로그아웃 후 가능합니다.");
      return;
    }
    System.out.println("\n[판매자 등록]");

    SellerPrivacy sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setAuthority(i);
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

  public void list(int auth) {
    if (auth == 0 || auth== 1 || auth == 2 ) {
      System.out.println("해당 메뉴는 관리자만 접근 가능합니다.");
      return;
    }
    System.out.println("\n[판매자 목록]");

    SellerPrivacy[] list = memberList.toArray(new SellerPrivacy[0]);

    for (SellerPrivacy member : list) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s\n", 
          member.getNumber(), 
          member.getName(), 
          member.getEmail(), 
          member.getPhoneNumber(), 
          member.getBusinessNumber(),
          member.getBusinessAddress(),
          member.getBusinessPlaceNumber(),
          member.getRegisteredDate());
    }
  }

  public void sellerDetail(int auth) {
    if (auth==0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[판매자 상세보기]");
    String id = Prompt.inputString("번호를 입력하세요: ");

    SellerPrivacy member = findById(id);

    if (member == null) {
      System.out.println("해당 번호의 판매자가 없습니다.");
      return;
    }

    System.out.printf("이름: %s입니다.\n", member.getName());
    System.out.printf("닉네임: %s입니다.\n", member.getNickname());
    System.out.printf("이메일: %s입니다.\n", member.getEmail());
    System.out.printf("생일: %s입니다.\n", member.getBirthday());
    System.out.printf("사진: %s입니다.\n", member.getPhoto());
    System.out.printf("전화: %s입니다.\n", member.getPhoneNumber());
    System.out.printf("사업자번호: %s입니다.\n", member.getBusinessNumber());
    System.out.printf("사업장주소: %s입니다.\n", member.getBusinessAddress());
    System.out.printf("사업장번호: %s입니다.\n", member.getBusinessPlaceNumber());
    System.out.printf("등록일: %s입니다.\n", member.getRegisteredDate());
    System.out.printf("권한등급: %d입니다.", member.getAuthority());
  }

  public void update(int auth) {
    if (auth==0) {
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
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("판매자 변경을 취소하였습니다.");
      return;
    }

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

    System.out.println("판매자을 변경하였습니다.");
  }

  public void delete(int auth) {
    if (auth==0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("\n[판매자 삭제]");
    String id = Prompt.inputString("번호를 입력하세요: ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 번호의 판매자이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("판매자 삭제를 취소하였습니다.");
      return;
    }

    memberList.remove(member);
    managerList.remove(delMember(id));

    System.out.println("판매자를 삭제하였습니다.");
  }

  private SellerPrivacy findById(String id) {
    SellerPrivacy[] arr = memberList.toArray(new SellerPrivacy[0]);
    for (SellerPrivacy member : arr) {
      if (member.getId() == id) {
        return member;
      }
    }
    return null;
  }

  public Manager addMember(String label, int size) {
    int i;
    for (i=0; i<size; i++) {
      if (managerList.get(i).getId().equals(label)) {
        System.out.println("중복되는 아이디입니다.");
        return managerList.get(i);
      }
    }
    return null;
  }
  public Manager delMember(String label) {
    while (true) {
      for (int i=0; i<managerList.size(); i++) {
        if (managerList.get(i).getId().equals(label)) {
          return managerList.get(i);
        }
      } /*else if (label.length() == 0) {
          System.out.println("아이디를 입력해 주세요.");
          return null;
        }*/
      System.out.println("일치하는 아이디가 없습니다.");
      return null;
    }
  }
}







