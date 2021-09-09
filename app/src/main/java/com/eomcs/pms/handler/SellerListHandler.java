package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;

public class SellerListHandler extends AbstractSellerHandler {

  public SellerListHandler(List<Seller> sellerList, List<Member> memberList) {
    super(sellerList, memberList);
  }

  @Override
  public void execute(){
    System.out.println("\n[판매자 목록]");
    for (Seller seller : sellerList) {
      System.out.printf("판매자번호-%d, %s, %s, %s, %s, 등급[%d], %s\n", 
          seller.getNumber(), 
          seller.getId(),
          seller.getBusinessName(),
          seller.getName(), 
          seller.getNickname(),
          seller.getLevel(),
          seller.getRegisteredDate());
    }
  }
}







