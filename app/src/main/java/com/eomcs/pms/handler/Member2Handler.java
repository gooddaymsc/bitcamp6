package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Member2;
import com.eomcs.util.Prompt;

public class Member2Handler {

  List<Member2> memberList;

  public Member2Handler(List<Member2> memberList) {
    this.memberList = memberList;
  }

  public void add() {
    System.out.println("[판매자 등록]");

    Member2 member = new Member2();

    member.setNo(Prompt.inputInt("번호? "));
    member.setName(Prompt.inputString("이름? "));
    member.setNickName(Prompt.inputString("닉네임? "));
    member.setEmail(Prompt.inputString("이메일? "));
    member.setBirthDay(Prompt.inputDate("생일? "));
    member.setPassword(Prompt.inputString("암호? "));
    member.setPhoto(Prompt.inputString("사진? "));
    member.setTel(Prompt.inputString("전화? "));
    member.setBusinessNo(Prompt.inputString("사업자번호? "));
    member.setBusinessAddress(Prompt.inputString("사업장주소? "));
    member.setBusinessTel(Prompt.inputString("사업장번호? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
  }

  public void list() {
    System.out.println("[판매자 목록]");

    Member2[] list = memberList.toArray(new Member2[0]);

    for (Member2 member : list) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s, %s\n", 
          member.getNo(), 
          member.getName(), 
          member.getEmail(), 
          member.getTel(), 
          member.getBusinessNo(),
          member.getBusinessAddress(),
          member.getBusinessTel(),
          member.getRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("[판매자 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Member2 member = findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 판매자가 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("닉네임: %s\n", member.getNickName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("생일: %s\n", member.getBirthDay());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("사업자번호: %s\n", member.getBusinessNo());
    System.out.printf("사업장주소: %s\n", member.getBusinessAddress());
    System.out.printf("사업장번호: %s\n", member.getBusinessTel());
    System.out.printf("등록일: %s\n", member.getRegisteredDate());
  }

  public void update() {
    System.out.println("[판매자 변경]");
    int no = Prompt.inputInt("번호? ");

    Member2 member = findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("이름(" + member.getName()  + ")? ");
    String nickName = Prompt.inputString("닉네임(" + member.getNickName()  + ")? ");
    String email = Prompt.inputString("이메일(" + member.getEmail() + ")? ");
    Date birthDay = Prompt.inputDate("생일(" + member.getBirthDay() + ")? ");
    String password = Prompt.inputString("암호? ");
    String photo = Prompt.inputString("사진(" + member.getPhoto() + ")? ");
    String tel = Prompt.inputString("전화(" + member.getTel() + ")? ");
    String bussinessNo = Prompt.inputString("사업자번호(" + member.getBusinessNo() + ")? ");
    String bussinessAddress = Prompt.inputString("사업장주소(" + member.getBusinessAddress() + ")? ");
    String bussinessTel = Prompt.inputString("사업장번호(" + member.getBusinessTel() + ")? ");
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("판매자 변경을 취소하였습니다.");
      return;
    }

    member.setName(name);
    member.setNickName(nickName);
    member.setEmail(email);
    member.setBirthDay(birthDay);
    member.setPassword(password);
    member.setPhoto(photo);
    member.setTel(tel);
    member.setBusinessNo(bussinessNo);
    member.setBusinessAddress(bussinessAddress);
    member.setBusinessTel(bussinessTel);

    System.out.println("판매자을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[판매자 삭제]");
    int no = Prompt.inputInt("번호? ");

    Member member = findByNo(no);

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

  private Member2 findByNo(int no) {
    Member2[] arr = memberList.toArray(new Member2[0]);
    for (Member2 member : arr) {
      if (member.getNo() == no) {
        return member;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Member2[] arr = memberList.toArray(new Member2[0]);
    for (Member2 member : arr) {
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







