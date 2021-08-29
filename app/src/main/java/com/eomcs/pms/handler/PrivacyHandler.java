package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyHandler {

  List<Privacy> memberList;
  List<String> uniqueIdList;

  int size = 1;
  public PrivacyHandler(List<Privacy> memberList,  List<String> uniqueIdList) {
    this.memberList = memberList;
    this.uniqueIdList = uniqueIdList;

  }

  public void memberAdd(int i) {
    System.out.println("\n[회원 등록]");

    Privacy privacy = new Privacy();
    privacy.setAuthority(i);
    privacy.setNumber(size++);
    //privacy.setNumber(Prompt.inputInt("번호? "));

    // 아이디가 중복되면 다시 아이디 재설정.
    String checkId = promptMember(Prompt.inputString("아이디를 입력해주세요: "));
    if (checkId == null) {
      return;
    }
    privacy.setId(checkId);
    privacy.setName(Prompt.inputString("이름을 입력해주세요: "));
    privacy.setNickname(Prompt.inputString("닉네임을 입력해주세요: "));
    privacy.setEmail(Prompt.inputString("이메일을 입력해주세요: "));
    privacy.setBirthday(Prompt.inputDate("생일을 입력해주세요: "));
    privacy.setPassword(Prompt.inputString("암호를 입력해주세요: "));
    privacy.setPhoto(Prompt.inputString("사진을 등록해주세요: "));
    privacy.setPhoneNumber(Prompt.inputString("전화를 입력해주세요: "));
    privacy.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(privacy);
    uniqueIdList.add(privacy.getId());
  }


  public void memberList() {
    System.out.println("\n[회원 목록]");

    Privacy[] list = memberList.toArray(new Privacy[0]);

    for (Privacy member : list) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          member.getId(), 
          member.getName(), 
          member.getEmail(), 
          member.getPhoneNumber(), 
          member.getRegisteredDate());
    }
  }

  public void memberDetail() {
    System.out.println("\n[회원 상세보기]");
    String id = Prompt.inputString("아이디 정보보기 : ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    System.out.printf("이름: %s입니다.\n", member.getName());
    System.out.printf("닉네임: %s입니다.\n", member.getNickname());
    System.out.printf("이메일: %s입니다.\n", member.getEmail());
    System.out.printf("생일: %s입니다.\n", member.getBirthday());
    System.out.printf("사진: %s입니다.\n", member.getPhoto());
    System.out.printf("전화: %s입니다.\n", member.getPhoneNumber());
    System.out.printf("등록일: %s입니다.\n", member.getRegisteredDate());
    System.out.printf("권한등급: %d입니다.", member.getAuthority());
  }


  public void memberUpdate() {
    System.out.println("\n[회원 변경]");
    String id = Prompt.inputString("변경할 아이디를 입력해주세요: ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("변경 후의 이름(" + member.getName()  + ")을 입력해주세요. ");
    String nickName = Prompt.inputString("변경 후의 닉네임(" + member.getNickname()  + ")을 입력해주세요. ");
    String email = Prompt.inputString("변경 후의 이메일(" + member.getEmail() + ")을 입력해주세요. ");
    Date birthDay = Prompt.inputDate("변경 후의 생일(" + member.getBirthday() + ")을 입력해주세요. ");
    String password = Prompt.inputString("변경 후의 암호을 입력해주세요. ");
    String photo = Prompt.inputString("변경 후의 사진(" + member.getPhoto() + ")을 등록해주세요. ");
    String tel = Prompt.inputString("변경 후의 전화(" + member.getPhoneNumber() + ")을 입력해주세요. ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    member.setName(name);
    member.setNickname(nickName);
    member.setEmail(email);
    member.setBirthday(birthDay);
    member.setPassword(password);
    member.setPhoto(photo);
    member.setPhoneNumber(tel);

    System.out.println("회원을 변경하였습니다.");
  }


  public void delete() {

    System.out.println("\n[회원 삭제]");
    String id = Prompt.inputString("삭제할 아이디를 입력해주세요: ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제를 취소하였습니다.");
      return;
    }

    memberList.remove(member);
    uniqueIdList.remove(member.getId());

    System.out.println("회원을 삭제하였습니다.");
  }

  private Privacy findById(String id) {
    Privacy[] arr = memberList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
      if (member.getId() == id) {
        return member;
      }
    }
    return null;
  }

  public String promptMember(String label) {
    while (true) {
      //String owner = Prompt.inputString(label);
      if (!uniqueIdList.contains(label)) {
        return label;
      } /*else if (label.length() == 0) {
        System.out.println("아이디를 입력해 주세요.");
        return null;
      } */
      System.out.println("중복되는 아이디입니다.");
      return null;
    }
  }
}






