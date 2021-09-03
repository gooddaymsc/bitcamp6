package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class MemberDetailHandler extends AbstractMemberHandler {

  public MemberDetailHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    super(memberList, sellerList);
  }

  @Override
  public void execute(int mem) { 
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }

    System.out.println("[회원 상세보기]");

    if (mem == 1) {
      int no = Prompt.inputInt("회원번호 : ");

      Privacy member = findByNo(no);

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("닉네임: %s\n", member.getNickname());
      System.out.printf("등급: %s\n", member.getLevel());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("사진: %s\n", member.getPhoto());
      System.out.printf("전화: %s\n", member.getPhoneNumber());
      //      System.out.printf("주소: %s입니다.\n", member.getAddress()); // 프라이버시입력?
      System.out.printf("등록일: %s\n", member.getRegisteredDate());

    }

    if (mem == 2) {
      int no = Prompt.inputInt("판매자번호 : ");

      SellerPrivacy member = findByNo2(no); 

      if (member == null) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      } 

      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("닉네임: %s\n", member.getNickname());
      System.out.printf("등급: %s\n", member.getLevel());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("사진: %s\n", member.getPhoto());
      System.out.printf("전화: %s\n", member.getPhoneNumber());
      System.out.printf("사업자번호: %\n", member.getBusinessNumber());
      System.out.printf("사업장주소: %\n", member.getBusinessAddress());
      System.out.printf("사업장번호: %\n", member.getBusinessPlaceNumber());
    }
  }
}





