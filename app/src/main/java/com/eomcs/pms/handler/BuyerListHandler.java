package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Buyer;

public class BuyerListHandler extends AbstractBuyerHandler {
  public BuyerListHandler(List<Buyer> buyerList) {
    super(buyerList);
  }

  @Override
  public void execute() {
    System.out.println("\n[회원 목록]");

    for (Buyer buyer : buyerList) {
      System.out.printf("회원번호-%d, %s, %s, %s, 등급[%d], %s\n", 
          buyer.getNumber(),
          buyer.getId(), 
          buyer.getName(),
          buyer.getNickname(),
          buyer.getLevel(),
          buyer.getRegisteredDate());
    }
  }
}







