package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;

public class SellerListHandler extends AbstractSellerHandler {

  public SellerListHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute(CommandRequest request){
    System.out.println("[판매자 목록]");
    System.out.printf("%-3s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
        "판매자번호", "아이디", "가게명", "이름", "닉네임", "등급","등록일");
    System.out.println("--------------------------------------------------------------------------");

    for (Member seller : memberList) {
      if (seller instanceof Seller) {
        System.out.printf("%-3d\t%-6s\t%-6s\t%-6s\t%-6s\t%-6d\t%-6s\n", 
            seller.getNumber(), 
            seller.getId(),
            ((Seller) seller).getBusinessName(),
            seller.getName(), 
            seller.getNickname(),
            seller.getLevel(),
            seller.getRegisteredDate());
      }
    }
  }
}







