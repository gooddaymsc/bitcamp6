package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockDetailHandler extends AbstractStockHandler {

  public StockDetailHandler(List<Stock> stockList) {
    super(stockList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("[재고 상세보기]");
    Stock stock = findByStock(Prompt.inputString("상품명 : "));

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






