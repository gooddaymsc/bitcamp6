package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class SellerPrivacyHandler {

  List<SellerPrivacy> memberList;

  public SellerPrivacyHandler(List<SellerPrivacy> memberList) {
    this.memberList = memberList;
  }

  public void add() {
    System.out.println("[판매자 등록]");

    SellerPrivacy sellerPrivacy = new SellerPrivacy();

    sellerPrivacy.setNumber(Prompt.inputInt("번호? "));
    sellerPrivacy.setName(Prompt.inputString("이름? "));
    sellerPrivacy.setNickname(Prompt.inputString("닉네임? "));
    sellerPrivacy.setEmail(Prompt.inputString("이메일? "));
    sellerPrivacy.setBirthday(Prompt.inputDate("생일? "));
    sellerPrivacy.setPassword(Prompt.inputString("암호? "));
    sellerPrivacy.setPhoto(Prompt.inputString("사진? "));
    sellerPrivacy.setPhoneNumber(Prompt.inputString("전화? "));
    sellerPrivacy.setBusinessNo(Prompt.inputString("사업자번호? "));
    sellerPrivacy.setBusinessAddress(Prompt.inputString("사업장주소? "));
    sellerPrivacy.setBusinessTel(Prompt.inputString("사업장번호? "));
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(sellerPrivacy);
  }

  public void list() {
    System.out.println("[판매자 목록]");

    SellerPrivacy[] list = memberList.toArray(new SellerPrivacy[0]);

    for (SellerPrivacy member : list) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s\n", 
          member.getNumber(), 
          member.getName(), 
          member.getEmail(), 
          member.getPhoneNumber(), 
          member.getBusinessNo(),
          member.getBusinessAddress(),
          member.getBusinessTel(),
          member.getRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("[판매자 상세보기]");
    int no = Prompt.inputInt("번호? ");

    SellerPrivacy member = findByNo(no);

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
    System.out.printf("사업자번호: %s\n", member.getBusinessNo());
    System.out.printf("사업장주소: %s\n", member.getBusinessAddress());
    System.out.printf("사업장번호: %s\n", member.getBusinessTel());
    System.out.printf("등록일: %s\n", member.getRegisteredDate());
  }

  public void update() {
    System.out.println("[판매자 변경]");
    int no = Prompt.inputInt("번호? ");

    SellerPrivacy member = findByNo(no);

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
    String bussinessNo = Prompt.inputString("사업자번호(" + member.getBusinessNo() + ")? ");
    String bussinessAddress = Prompt.inputString("사업장주소(" + member.getBusinessAddress() + ")? ");
    String bussinessTel = Prompt.inputString("사업장번호(" + member.getBusinessTel() + ")? ");
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
    member.setBusinessNo(bussinessNo);
    member.setBusinessAddress(bussinessAddress);
    member.setBusinessTel(bussinessTel);

    System.out.println("판매자을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[판매자 삭제]");
    int no = Prompt.inputInt("번호? ");

    SellerPrivacy member = findByNo(no);

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

    System.out.println("판매자를 삭제하였습니다.");
  }

  private SellerPrivacy findByNo(int no) {
    SellerPrivacy[] arr = memberList.toArray(new SellerPrivacy[0]);
    for (SellerPrivacy member : arr) {
      if (member.getNumber() == no) {
        return member;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    SellerPrivacy[] arr = memberList.toArray(new SellerPrivacy[0]);
    for (SellerPrivacy member : arr) {
      if (member.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public String promptMember(String label) {
    while (true) {
      String owner = Prompt.inputString(label);
      if (this.exist(owner)) {
        return owner;
      } else if (owner.length() == 0) {
        return null;
      }
      System.out.println("등록된 판매자가 아닙니다.");
    }
  }

  public String promptMembers(String label) {
    String members = "";
    while (true) {
      String member = Prompt.inputString(label);
      if (this.exist(member)) {
        if (members.length() > 0) {
          members += ",";
        }
        members += member;
        continue;
      } else if (member.length() == 0) {
        break;
      } 
      System.out.println("등록된 판매자가 아닙니다.");
    }
    return members;
  }
}







