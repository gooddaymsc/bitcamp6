package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class StockDetailHandler extends AbstractStockHandler {

  public StockDetailHandler(List<StockList> allStockList, StockPrompt stockPrompt ) {
    super(stockPrompt, allStockList);
  }
  @Override
  public void execute() {
    String nowLoginId = App.getLoginUser().getId();

    System.out.println("[재고 상세보기]");
    Stock stock = stockPrompt.findStockById(nowLoginId, Prompt.inputString("상품명 : "));

    if (stock == null) {
      System.out.println("해당 상품의 재고가 없습니다.");
      return;
    }
    System.out.printf("재고번호: %s\n", stock.getStockNumber());
    System.out.printf("상품명 : %s\n",stock.getProduct().getProductName());
    System.out.printf("가격: %d\n", stock.getPrice ());
    System.out.printf("재고수량: %d\n", stock.getStocks ());   

  }

}






