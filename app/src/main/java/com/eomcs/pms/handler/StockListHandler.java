package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public class StockListHandler extends AbstractStockHandler {
  List<StockList> allStockList;
  public StockListHandler(List<StockList> allStockList, StockPrompt stockPrompt) {
    super(stockPrompt);
    this.allStockList = allStockList;
  }

  @Override
  public void execute() {
    String nowLoginId = App.getLoginUser().getId();
    System.out.println("\n[재고 목록]");
    StockList stockList = allStockList.get(stockPrompt.findStockListById(nowLoginId));

    if (stockList.getSellerStock().size() == 0) {
      System.out.println("아직 추가한 상품이 없습니다.");
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
  }
}


