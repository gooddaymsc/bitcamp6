package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public class StockListHandler extends AbstractStockHandler {

  public StockListHandler(StockPrompt stockPrompt) {
    super(stockPrompt);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }

    if (App.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {
      System.out.println("[재고 목록]");
      StockList stockList = stockPrompt.findStockListById(App.getLoginUser().getId());

      if (stockList.getSellerStock().size() == 0) {
        System.out.println("아직 추가한 상품이 없습니다.");
        return;
      }
      for (Stock stock : stockList.getSellerStock()) {
        System.out.printf("%s, %d, %d\n", 
            stock.getProduct().getProductName(), 
            stock.getPrice(), 
            stock.getStocks());
      }
    }
  }

}