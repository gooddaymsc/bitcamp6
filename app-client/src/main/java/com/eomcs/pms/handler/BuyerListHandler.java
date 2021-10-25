package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.util.Prompt;

public class BuyerListHandler implements Command {
  BuyerDao buyerDao;
  public BuyerListHandler (BuyerDao buyerDao) {
    this.buyerDao = buyerDao;
  } 

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[회원 목록] || 이전(0)\n");
      System.out.printf("%-3s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
          "회원번호", "아이디", "이름", "닉네임", "등급","등록일");
      System.out.println("--------------------------------------------------------------------------");

      Collection<Buyer> buyerList =  buyerDao.findAll();

      for (Buyer buyer : buyerList) {
        System.out.printf("%-6d\t%-6s\t%-6s\t%-6s\t%-6d\t%-6s\n", 
            buyer.getMember().getNumber(),
            buyer.getMember().getId(), 
            buyer.getMember().getName(),
            buyer.getMember().getNickname(),
            buyer.getMember().getLevel(),
            buyer.getMember().getRegisteredDate());
      }
      System.out.println();
      while (true) {
        String id = Prompt.inputString("선택할 아이디 : ");
        System.out.println();
        request.setAttribute("id", id);
        if (id.equals("0")) {return;}
        request.getRequestDispatcher("/buyer/detail").forward(request);
        continue Loop;
      }
    }
  }
}







