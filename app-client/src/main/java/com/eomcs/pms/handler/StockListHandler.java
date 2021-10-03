package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class StockListHandler implements Command {
  StockDao stockDao;
  public StockListHandler(StockDao stockDao) {
    this.stockDao = stockDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    Loop : while(true) {
      Member mine = ClientApp.getLoginUser();
      System.out.println("[재고 목록]");
      //      System.out.printf("<<\t%s\t>>\n", ((Seller) mine).getBusinessName());
      //      System.out.printf("> 주소\t:\t%s\n", ((Seller) mine).getBusinessAddress());
      //      System.out.printf("> 전화번호\t:\t%s\n", ((Seller) mine).getBusinessPlaceNumber());
      //      System.out.println();

      StockList stockList = stockDao.findAll(nowLoginId);

      if (stockList.getSellerStock().size() == 0) {
        System.out.println("아직 추가한 상품이 없습니다.\n");
        return;
      }
      System.out.printf("%-3s\t%-6s\t%-6s\t%-3s\n",
          "번호", "상품명", "가격", "재고");
      System.out.println("--------------------------------------------------------------------------");

      for (Stock stock : stockList.getSellerStock()) {
        System.out.printf("%-3d\t%-6s\t%-6d\t%-3d\n", 
            stock.getStockNumber(),
            stock.getProduct().getProductName(), 
            stock.getPrice(), 
            stock.getStocks());
      }
      System.out.println();
      while(true) {
        System.out.println("상세보기(R) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch(choose) {
          case "r":
          case "R" : 
            request.getRequestDispatcher("/stock/detail").forward(request); continue Loop;
          case "0" :
            return;
          default : 
            System.out.println("다시 선택해 주세요."); continue;
        }
      }
    }
  }
}

