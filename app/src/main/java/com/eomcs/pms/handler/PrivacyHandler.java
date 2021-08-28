package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.util.Prompt;

public class PrivacyHandler {

  List<Privacy> memberList;
<<<<<<< HEAD
  int size = 1;
  public PrivacyHandler(List<Privacy> memberList) {
=======
  List<String> uniqueIdList;

  int size = 1;
  public PrivacyHandler(List<Privacy> memberList,  List<String> uniqueIdList) {
>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb
    this.memberList = memberList;
    this.uniqueIdList = uniqueIdList;

  }

<<<<<<< HEAD
  //  public void chooseAdd(int i) {
  //    if (i==1) {
  //      memberAdd(i);
  //    } else if (i==2) {
  //      sellerAdd(i);
  //    }
  //  }
  //
  //  public void chooseList(int i) {
  //    if (i==1) {
  //      memberList();
  //    } else if (i==2) {
  //      sellerList();
  //    }
  //  }
  //
  //  public void chooseDetail(int i) {
  //    if (i==1) {
  //      memberDetail();
  //    } else if (i==2) {
  //      sellerDetail();
  //    }
  //  }
  //
  //  public void chooseUpdate(int i) {
  //    if (i==1) {
  //      memberUpdate();
  //    } else if (i==2) {
  //      sellerUpdate();
  //    }
  //  }


=======
>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb
  public void memberAdd(int i) {
    System.out.println("\n[회원 등록]");

    Privacy privacy = new Privacy();
    privacy.setAuthority(i);
    privacy.setNumber(size++);
    //privacy.setNumber(Prompt.inputInt("번호? "));
<<<<<<< HEAD
    privacy.setId(Prompt.inputString("아이디? "));
=======

    // 아이디가 중복되면 다시 아이디 재설정.
    String checkId = promptMember(Prompt.inputString("아이디? "));
    if (checkId == null) {
      return;
    }
    privacy.setId(checkId);
>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb
    privacy.setName(Prompt.inputString("이름? "));
    privacy.setNickname(Prompt.inputString("닉네임? "));
    privacy.setEmail(Prompt.inputString("이메일? "));
    privacy.setBirthday(Prompt.inputDate("생일? "));
    privacy.setPassword(Prompt.inputString("암호? "));
    privacy.setPhoto(Prompt.inputString("사진? "));
    privacy.setPhoneNumber(Prompt.inputString("전화? "));
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

<<<<<<< HEAD

  public void memberDetail() {
    System.out.println("\n[회원 상세보기]");
    int no = Prompt.inputInt("번호? ");
=======
  public void memberDetail() {
    System.out.println("\n[회원 상세보기]");
    String id = Prompt.inputString("아이디 정보보기 : ");
>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("닉네임: %s\n", member.getNickname());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("생일: %s\n", member.getBirthday());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getPhoneNumber());
    System.out.printf("등록일: %s\n", member.getRegisteredDate());
    System.out.printf("권한등급: %d", member.getAuthority());
  }

<<<<<<< HEAD

  public void memberUpdate() {
    System.out.println("\n[회원 변경]");
    int no = Prompt.inputInt("번호? ");
=======
>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb

  public void memberUpdate() {
    System.out.println("\n[회원 변경]");
    String id = Prompt.inputString("변경할 아이디 : ");

    Privacy member = findById(id);

    if (member == null) {
      System.out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    String name = Prompt.inputString("이름(" + member.getName()  + ")? ");
    String nickName = Prompt.inputString("닉네임(" + member.getNickname()  + ")? ");
    String email = Prompt.inputString("이메일(" + member.getEmail() + ")? ");
    Date birthDay = Prompt.inputDate("생일(" + member.getBirthday() + ")? ");
    String password = Prompt.inputString("암호? ");
    String photo = Prompt.inputString("사진(" + member.getPhoto() + ")? ");
    String tel = Prompt.inputString("전화(" + member.getPhoneNumber() + ")? ");

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
<<<<<<< HEAD
    System.out.println("\n[회원 삭제]");
    int no = Prompt.inputInt("번호? ");
=======
>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb

    System.out.println("\n[회원 삭제]");
    String id = Prompt.inputString("삭제할 아이디 : ");

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
<<<<<<< HEAD

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


=======
>>>>>>> d4ba18d87e2e3b9c596960774d541c0f48e315cb
}






