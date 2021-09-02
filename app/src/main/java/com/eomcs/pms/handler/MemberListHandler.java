package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.SellerPrivacy;

public class MemberListHandler extends AbstractMemberHandler {

  public MemberListHandler(List<Privacy> memberList, List<SellerPrivacy> sellerList) {
    super(memberList, sellerList);
  }

  @Override
  public void execute(int mem) {  
    if (App.getLoginUser().getAuthority() != 3) {
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
}






