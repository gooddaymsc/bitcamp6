package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Person;
import com.eomcs.util.Prompt;

public class PersonHandler {

  List<Person> memberList;

  public PersonHandler(List<Person> memberList) {
    this.memberList = memberList;
  }

  public void add() {
    System.out.println("[회원 등록]");

    Person member = new Person();

    member.setNo(Prompt.inputInt("번호? "));
    member.setName(Prompt.inputString("이름? "));
    member.setNickName(Prompt.inputString("닉네임? "));
    member.setEmail(Prompt.inputString("이메일? "));
    member.setPassword(Prompt.inputString("암호? "));
    member.setPhoto(Prompt.inputString("사진? "));
    member.setTel(Prompt.inputString("전화? "));
    member.setAddress(Prompt.inputString("주소? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    member.setBuyerSeller(Prompt.inputString("구매자/판매자? "));

    memberList.add(member);
  }

  public void list() {
    System.out.println("[회원 목록]");

    Person[] list = memberList.toArray(new Person[0]);

    for (Person member : list) {
      System.out.printf("%d, %s, %d, %s, %s\n", 
          member.getNo(),
          member.getName(), 
          member.getLevel(), 
          member.getBuyerSeller(), 
          member.getRegisteredDate());

    }
  }

  public void detail() {
    System.out.println("[회원 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Person member = findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("닉네임: %s\n", member.getNickName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("주소: %s\n", member.getAddress());
    System.out.printf("등록일: %s\n", member.getRegisteredDate());
    System.out.printf("구매자/판매자: %s\n", member.getBuyerSeller());
  }

  public void update() {
    System.out.println("[회원 변경]");
    int no = Prompt.inputInt("번호? ");

    Person member = findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("이름(" + member.getName()  + ")? ");
    String email = Prompt.inputString("이메일(" + member.getEmail() + ")? ");
    String password = Prompt.inputString("암호? ");
    String photo = Prompt.inputString("사진(" + member.getPhoto() + ")? ");
    String tel = Prompt.inputString("전화(" + member.getTel() + ")? ");
    String address = Prompt.inputString("주소(" + member.getAddress() + ")? ");


    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    member.setName(name);
    member.setEmail(email);
    member.setPassword(password);
    member.setPhoto(photo);
    member.setTel(tel);
    member.setAddress(address);

    System.out.println("회원을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[회원 삭제]");
    int no = Prompt.inputInt("번호? ");

    Person member = findByNo(no);

    if (member == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제를 취소하였습니다.");
      return;
    }

    memberList.remove(member);

    System.out.println("회원을 삭제하였습니다.");
  }

  private Person findByNo(int no) {
    Person[] arr = memberList.toArray(new Person[0]);
    for (Person member : arr) {
      if (member.getNo() == no) {
        return member;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Person[] arr = memberList.toArray(new Person[0]);
    for (Person member : arr) {
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
      System.out.println("등록된 회원이 아닙니다.");
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
      System.out.println("등록된 회원이 아닙니다.");
    }
    return members;
  }
}






