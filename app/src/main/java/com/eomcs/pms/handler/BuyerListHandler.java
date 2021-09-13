package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;

public class BuyerListHandler extends AbstractBuyerHandler {
  public BuyerListHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute() {
    System.out.println("\n[회원 목록]");
    System.out.printf("%-3s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
        "회원번호", "아이디", "이름", "닉네임", "등급","등록일");
    System.out.println("--------------------------------------------------------------------------");

    for (Member buyer : memberList) {
      if (buyer instanceof Buyer) {
        System.out.printf("%-6d\t%-6s\t%-6s\t%-6s\t%-6d\t%-6s\n", 
            buyer.getNumber(),
            buyer.getId(), 
            buyer.getName(),
            buyer.getNickname(),
            buyer.getLevel(),
            buyer.getRegisteredDate());
      }
    }
  }
}







