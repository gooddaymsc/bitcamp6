package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyHandler {

  List<SellerPrivacy> memberList;
  List<String> uniqueIdList;
  Manager loginPrivacy;
  int size=1;

  public SellerPrivacyHandler(List<SellerPrivacy> memberList, List<String> uniqueIdList, Manager loginPrivacy) {
    this.memberList = memberList;
    this.uniqueIdList = uniqueIdList;
    this.loginPrivacy = loginPrivacy;
  }


  public void add(int i) {
    System.out.println("\n[판매자 등록]");

    SellerPrivacy sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setAuthority(i);
    sellerPrivacy.setNumber(size++);
    //sellerPrivacy.setNumber(Prompt.inputInt("번호? "));

    String checkId = promptMember(Prompt.inputString("아이디? "));
    if (checkId == null) {
      return;
    }
    sellerPrivacy.setId(checkId);
    sellerPrivacy.setName(Prompt.inputString("이름? "));
    sellerPrivacy.setNickname(Prompt.inputString("닉네임? "));
    sellerPrivacy.setEmail(Prompt.inputString("이메일? "));
    sellerPrivacy.setBirthday(Prompt.inputDate("생일? "));
    sellerPrivacy.setPassword(Prompt.inputString("암호? "));
    sellerPrivacy.setPhoto(Prompt.inputString("사진? "));
    sellerPrivacy.setPhoneNumber(Prompt.inputString("전화? "));
    sellerPrivacy.setBusinessNumber(Prompt.inputString("사업자번호? "));
    sellerPrivacy.setBusinessAddress(Prompt.inputString("사업장주소? "));
    sellerPrivacy.setBusinessPlaceNumber(Prompt.inputString("사업장번호? "));
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(sellerPrivacy);
    uniqueIdList.add(sellerPrivacy.getId());

  }

  public void list() {
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

  public void detail() {
    System.out.println("\n[판매자 상세보기]");

    String id = Prompt.inputString("번호? ");

    SellerPrivacy member = findById(id);

    if (member == null) {
      System.out.println("해당 번호의 판매자가 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("닉네임: %s\n", member.getNickname());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("생일: %s\n", member.getBirthday());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getPhoneNumber());
    System.out.printf("사업자번호: %s\n", member.getBusinessNumber());
    System.out.printf("사업장주소: %s\n", member.getBusinessAddress());
    System.out.printf("사업장번호: %s\n", member.getBusinessPlaceNumber());
    System.out.printf("등록일: %s\n", member.getRegisteredDate());
    System.out.printf("권한등급: %d", member.getAuthority());
  }

  public void update() {
    System.out.println("\n[판매자 변경]");

    String id = Prompt.inputString("번호? ");

    SellerPrivacy member = findById(id);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("이름(" + member.getName()  + ")? ");
    String nickName = Prompt.inputString("닉네임(" + member.getNickname()  + ")? ");
    String email = Prompt.inputString("이메일(" + member.getEmail() + ")? ");
    Date birthDay = Prompt.inputDate("생일(" + member.getBirthday() + ")? ");
    String password = Prompt.inputString("암호? ");
    String photo = Prompt.inputString("사진(" + member.getPhoto() + ")? ");
    String tel = Prompt.inputString("전화(" + member.getPhoneNumber() + ")? ");
    String bussinessNo = Prompt.inputString("사업자번호(" + member.getBusinessNumber() + ")? ");
    String bussinessAddress = Prompt.inputString("사업장주소(" + member.getBusinessAddress() + ")? ");
    String bussinessTel = Prompt.inputString("사업장번호(" + member.getBusinessPlaceNumber() + ")? ");
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

  public void delete() {
    System.out.println("\n[판매자 삭제]");

    String id = Prompt.inputString("번호? ");

    SellerPrivacy member = findById(id);

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
    uniqueIdList.remove(member.getId());

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

  public String promptMember(String label) {
    while (true) {
      if (!uniqueIdList.contains(label)) {
        return label;
      } /*else if (label.length() == 0) {
        System.out.println("아이디를 입력해 주세요.");
        return null;
      }*/
      System.out.println("중복되는 아이디입니다.");
      return null;
    }
  }
}







