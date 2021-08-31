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

  public void list(int auth, int mem) {  
    if (auth != 3) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }
    System.out.println("[회원 목록]");
    if (mem == 1) { 
      Privacy[] list = memberList.toArray(new Privacy[0]);
      for (Privacy member : list) {                        // 회원(구매자) 목록
        System.out.printf("회원번호-%d, %s, %s, %s, 등급[%d], %s\n", 
            member.getNumber(),
            member.getId(),
            member.getName(), 
            member.getNickname(), 
            member.getLevel(), 
            member.getRegisteredDate());
      }
    }

    if (mem == 2) { 
      SellerPrivacy[] list2 = sellerList.toArray(new SellerPrivacy[0]);
      for (SellerPrivacy member : list2) {                       // 판매자 목록
        System.out.printf("판매자번호-%d, %s, %s, %s, 등급[%d], %s\n", 
            member.getNumber(),
            member.getId(),
            member.getName(), 
            member.getNickname(), 
            member.getLevel(), 
            member.getRegisteredDate());
      }
    }
  }

  public void detail(int auth, int mem) { 
    if (auth != 3) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }

    System.out.println("[회원 상세보기]");

    if (mem == 1) {
      int no = Prompt.inputInt("회원번호를 입력해주세요: ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      System.out.printf("이름: %s입니다.\n", member.getName());
      System.out.printf("닉네임: %s입니다.\n", member.getNickname());
      System.out.printf("등급: %s입니다.\n", member.getLevel());
      System.out.printf("이메일: %s입니다.\n", member.getEmail());
      System.out.printf("사진: %s입니다.\n", member.getPhoto());
      System.out.printf("전화: %s입니다.\n", member.getPhoneNumber());
      //      System.out.printf("주소: %s입니다.\n", member.getAddress()); // 프라이버시입력?
      System.out.printf("등록일: %s입니다.\n", member.getRegisteredDate());

    }

    if (mem == 2) {
      int no = Prompt.inputInt("판매자번호를 입력해주세요: ");

      SellerPrivacy member = findByNo2(no); 

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      } 

      System.out.printf("이름: %s입니다.\n", member.getName());
      System.out.printf("닉네임: %s입니다.\n", member.getNickname());
      System.out.printf("등급: %s입니다.\n", member.getLevel());
      System.out.printf("이메일: %s입니다.\n", member.getEmail());
      System.out.printf("사진: %s입니다.\n", member.getPhoto());
      System.out.printf("전화: %s입니다.\n", member.getPhoneNumber());
      System.out.printf("사업자번호: %s입니다.\n", member.getBusinessNumber());
      System.out.printf("사업장주소: %s입니다.\n", member.getBusinessAddress());
      System.out.printf("사업장번호: %s입니다.\n", member.getBusinessPlaceNumber());
    } 

  }

  public void update(int auth, int mem) {
    if (auth != 3) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }
    System.out.println("[회원 변경]");

    if (mem == 1) {
      int no = Prompt.inputInt("회원번호를 입력해주세요: ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      String nickName = Prompt.inputString("변경 후의 닉네임(" + member.getNickname()  + ")을 입력해주세요: ");
      int level = Prompt.inputInt("변경 후의 등급(" + member.getLevel()  + ")을 입력해주세요: ");
      //      String buyerSeller = Prompt.inputString("구매자/판매자(" + member.getBuyerSeller()  + ")? ");
      // 관리자가 구매자를 판매자로 변경시키는 방법 구현 예정

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("회원 변경을 취소하였습니다.");
        return;
      }

      member.setNickname(nickName);
      member.setLevel(level);
      //      member.setBuyerSeller(buyerSeller);

    }

    if (mem == 2) {
      int no = Prompt.inputInt("판매자번호를 입력해주세요: ");

      Privacy member = findByNo2(no);

      if (member == null) {
        System.out.println("해당 번호의 판매자가 없습니다.");
        return;
      }

      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      String nickName = Prompt.inputString("변경 후의 닉네임(" + member.getNickname()  + ")을 입력해주세요: ");
      int level = Prompt.inputInt("변경 후의 등급(" + member.getLevel()  + ")을 입력해주세요: ");
      //      String buyerSeller = Prompt.inputString("구매자/판매자(" + member.getBuyerSeller()  + ")? ");
      // 관리자가 판매자를 구매자로 변경시키는 방법 구현 예정

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

  public void delete(int auth, int mem) {
    if (auth != 3) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }
    System.out.println("[회원 탈퇴]");
    if (mem == 1) {
      int no = Prompt.inputInt("회원번호를 입력해주세요: ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("회원 탈퇴를 취소하였습니다.");
        return;
      }

      memberList.remove(member);
      System.out.println("회원을 탈퇴시켰습니다.");

    }

    if (mem == 2) {
      int no = Prompt.inputInt("판매자번호를 입력해주세요: ");

      Privacy member = findByNo2(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("판매자 탈퇴를 취소하였습니다.");
        return;
      }

      sellerList.remove(member);
      System.out.println("판매자를 탈퇴시켰습니다.");
    }
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






