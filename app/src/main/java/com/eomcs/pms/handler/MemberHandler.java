package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;
// 수정중
public class MemberHandler {

  List<Privacy> memberList;
  List<SellerPrivacy> sellerList; //회원(구매자)과 판매자 리스트 변수

  public MemberHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    this.memberList = memberList;
    this.sellerList = sellerList;
  }

  public void list(int auth) {  
    System.out.println("[회원 목록]");

    if (auth == 1) { // 왜 PrivacyHandler의 list가 출력될까요
      Privacy[] list = memberList.toArray(new Privacy[0]);
      for (Privacy member : list) {                        // 회원(구매자) 목록
        System.out.printf("%d, %s, %s, %d, %s, %s\n", 
            member.getNumber(),
            member.getId(),
            member.getName(), 
            member.getLevel(), //레벨은?
            member.getBuyerSeller(), //회원/판매자 출력방법..?
            member.getRegisteredDate());
      }
    }

    if (auth == 2) { // 권한2(판매자) 출력이 안데네욤..
      Privacy[] list2 = sellerList.toArray(new Privacy[0]);
      for (Privacy member : list2) {                       // 판매자 목록
        System.out.printf("%d, %s, %s, %d, %s, %s\n", 
            member.getNumber(),
            member.getId(),
            member.getName(), 
            member.getLevel(), //레벨은?
            member.getBuyerSeller(), //회원/판매자 출력방법..?
            member.getRegisteredDate());
      }
    }
  }

  public void detail(int auth) { // 아래 마찬가지

    System.out.println("[회원 상세보기]");

    if (auth == 1) {
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

    }

    if (auth == 2) {
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
      System.out.printf("사업자번호: %s\n", member.getBusinessNumber());
      System.out.printf("사업장주소: %s\n", member.getBusinessAddress());
      System.out.printf("사업장번호: %s\n", member.getBusinessPlaceNumber());
    } 

  }

  public void update(int auth) {
    System.out.println("[회원 변경]");

    if (auth == 1) {
      int no = Prompt.inputInt("회원번호? ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      String nickName = Prompt.inputString("닉네임(" + member.getNickname()  + ")? ");
      int level = Prompt.inputInt("등급(" + member.getLevel()  + ")? ");
      //      String buyerSeller = Prompt.inputString("구매자/판매자(" + member.getBuyerSeller()  + ")? ");

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("회원 변경을 취소하였습니다.");
        return;
      }

      member.setNickname(nickName);
      member.setLevel(level);
      //      member.setBuyerSeller(buyerSeller);

    }

    if (auth == 2) {
      int no = Prompt.inputInt("판매자번호? ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      String nickName = Prompt.inputString("닉네임(" + member.getNickname()  + ")? ");
      int level = Prompt.inputInt("등급(" + member.getLevel()  + ")? ");
      //      String buyerSeller = Prompt.inputString("구매자/판매자(" + member.getBuyerSeller()  + ")? ");

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("회원 변경을 취소하였습니다.");
        return;
      }

      member.setNickname(nickName);
      member.setLevel(level);
      //      member.setBuyerSeller(buyerSeller);

    }



    System.out.println("회원을 변경하였습니다.");
  }

  public void delete(int auth) {
    System.out.println("[회원 탈퇴]");
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






