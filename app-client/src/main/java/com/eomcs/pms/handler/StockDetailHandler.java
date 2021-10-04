package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockDetailHandler implements Command {

  StockDao stockDao;

  public StockDetailHandler(StockDao stockDao) {
    this.stockDao = stockDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();

    System.out.println("[재고 상세보기]");
    String stockName = Prompt.inputString("상품명 :");
    Stock stock = stockDao.findStockById(nowLoginId, stockName);

    if (stock == null) {
      System.out.println("해당 상품의 재고가 없습니다.");
      return;
    }
    System.out.printf("재고번호: %s\n", stock.getStockNumber());
    System.out.printf("상품명 : %s\n",stock.getProduct().getProductName());
    System.out.printf("가격: %d\n", stock.getPrice ());
    System.out.printf("재고수량: %d\n", stock.getStocks ());   
    System.out.println();

    request.setAttribute("stock", stockName);

    while(true) {
      String choose = Prompt.inputString("재고변경(U) / 재고삭제(D) / 이전(0)>");
      System.out.println();
      switch(choose) {
        case "U":
        case "u":request.getRequestDispatcher("/stock/update").forward(request); return;
        case "D":
        case "d":request.getRequestDispatcher("/stock/delete").forward(request); return;
        case "0":return;
        default : System.out.println("잘못입력하셨습니다."); continue;

      }
    }
  }

}






