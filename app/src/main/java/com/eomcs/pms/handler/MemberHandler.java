package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class MemberHandler {

  List<Privacy> memberList;
  List<SellerPrivacy> sellerList; //회원(구매자)과 판매자 리스트 변수

  public MemberHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    this.memberList = memberList;
    this.sellerList = sellerList;
  }

  //  public void add() {
  //    System.out.println("[회원 등록]");
  //
  //    Privacy member = new Privacy();
  //
  //    member.setNumber(Prompt.inputInt("번호? "));
  //    member.setName(Prompt.inputString("이름? "));
  //    member.setNickname(Prompt.inputString("닉네임? "));
  //    member.setEmail(Prompt.inputString("이메일? "));
  //    member.setPassword(Prompt.inputString("암호? "));
  //    member.setPhoto(Prompt.inputString("사진? "));
  //    member.setPhoneNumber(Prompt.inputString("전화? "));
  //    member.setAddress(Prompt.inputString("주소? "));
  //    member.setRegisteredDate(new Date(System.currentTimeMillis()));
  //    member.setBuyerSeller(Prompt.inputString("구매자/판매자? "));
  //
  //    memberList.add(member);
  //  }

  public void list() {
    System.out.println("[회원 목록]");

    Privacy[] list = memberList.toArray(new Privacy[0]);
    Privacy[] list2 = sellerList.toArray(new Privacy[0]);

    for (Privacy member : list) {                        // 회원(구매자) 목록
      System.out.printf("%d, %s, %d, %s, %s\n", 
          member.getNumber(),
          member.getName(), 
          member.getLevel(), 
          member.getBuyerSeller(), 
          member.getRegisteredDate());
    }

    for (Privacy member : list2) {                       // 판매자 목록
      System.out.printf("%d, %s, %d, %s, %s\n", 
          member.getNumber(),
          member.getName(), 
          member.getLevel(), 
          member.getBuyerSeller(), 
          member.getRegisteredDate());

    }
  }

  public void detail() {
    System.out.println("[회원 상세보기]");
    try {
      int buyerSeller = Prompt.inputInt("1.구매자/2.판매자? ");

      if (buyerSeller == 1) {
        int no = Prompt.inputInt("번호? ");

        Privacy member = findByNo(no);

        if (member == null) {
          System.out.println("해당 번호의 회원이 없습니다.");
          return;
        }

        System.out.printf("이름: %s\n", member.getName());
        System.out.printf("닉네임: %s\n", member.getNickname());
        System.out.printf("이메일: %s\n", member.getEmail());
        System.out.printf("사진: %s\n", member.getPhoto());
        System.out.printf("전화: %s\n", member.getPhoneNumber());
        System.out.printf("주소: %s\n", member.getAddress());
        System.out.printf("등록일: %s\n", member.getRegisteredDate());

      } else if (buyerSeller == 2) {

        int no = Prompt.inputInt("번호? ");

        SellerPrivacy member = findByNo2(no); 

        if (member == null) {
          System.out.println("해당 번호의 회원이 없습니다.");
          return;
        } 

        System.out.printf("이름: %s\n", member.getName());
        System.out.printf("닉네임: %s\n", member.getNickname());
        System.out.printf("이메일: %s\n", member.getEmail());
        System.out.printf("사진: %s\n", member.getPhoto());
        System.out.printf("전화: %s\n", member.getPhoneNumber());
        System.out.printf("주소: %s\n", member.getAddress());
        System.out.printf("사업자번호: %s\n", member.getBusinessNo());
        System.out.printf("사업장주소: %s\n", member.getBusinessAddress());
        System.out.printf("사업장번호: %s\n", member.getBusinessTel());
      } else {
        System.out.println("번호를 다시 입력하시오.");
        return; // int buyerSeller = Prompt.inputInt("1.구매자/2.판매자? ")로 가고싶다.
      }
    } catch (Throwable e) {
      System.out.println("---------------------------------------------");
      System.out.printf("오류 발생: %s\n", e.getClass().getName());
      System.out.println("---------------------------------------------");
    }
  }

  public void update() {
    System.out.println("[회원 변경]");
    try {
      int no = Prompt.inputInt("번호? ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      String nickName = Prompt.inputString("닉네임(" + member.getNickname()  + ")? ");
      int level = Prompt.inputInt("등급(" + member.getLevel()  + ")? ");
      String buyerSeller = Prompt.inputString("구매자/판매자(" + member.getBuyerSeller()  + ")? ");

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("회원 변경을 취소하였습니다.");
        return;
      }

      member.setNickname(nickName);
      member.setLevel(level);
      member.setBuyerSeller(buyerSeller);

    } catch (Throwable e) {
      System.out.println("---------------------------------------------");
      System.out.printf("오류 발생: %s\n", e.getClass().getName());
      System.out.println("---------------------------------------------");
    }

    System.out.println("회원을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[회원 탈퇴]");
    try {
      int no = Prompt.inputInt("번호? ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("회원 삭제를 취소하였습니다.");
        return;
      }

      memberList.remove(member);

    } catch (Throwable e) {
      System.out.println("---------------------------------------------");
      System.out.printf("오류 발생: %s\n", e.getClass().getName());
      System.out.println("---------------------------------------------");
    }

    System.out.println("회원을 탈퇴시켰습니다.");
  }

  private Privacy findByNo(int no) {                          // 구매자 번호찾기
    Privacy[] arr = memberList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
      if (member.getNumber() == no) {
        return member;
      }
    }
    return null;
  }

  private SellerPrivacy findByNo2(int no) {                         // 판매자 번호찾기
    SellerPrivacy[] arr = sellerList.toArray(new SellerPrivacy[0]);
    for (SellerPrivacy member : arr) {
      if (member.getNumber() == no) {
        return member;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Privacy[] arr = memberList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
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






