package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class MemberDetailHandler extends AbstractMemberHandler {

  public MemberDetailHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    super(memberList, sellerList);
  }

  public void execute(int mem) { 
    if (App.getLoginUser().getAuthority() != 3) {
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
}





