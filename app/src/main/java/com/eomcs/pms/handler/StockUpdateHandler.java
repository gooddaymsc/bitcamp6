package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockUpdateHandler extends AbstractStockHandler {
  public StockUpdateHandler(StockPrompt stockPrompt) {
    super(stockPrompt);
  }
  @Override
  public void execute() {

    if (App.getLoginUser().getAuthority() != Menu.ACCESS_SELLER ) {

      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    while(true) {
      System.out.println("[재고 변경]");

      Stock stock = stockPrompt.findByStock(Prompt.inputString("변경할 상품명 : "));

      if (stock == null) {
        System. out.println("해당 상품의 재고가 없습니다.");
        return;
      }

      int stocks = Prompt.inputInt(String.format("수량(변경 전 : %d) : ", stock.getStocks()));
      int price = Prompt.inputInt(String.format("가격(변경 전 : %d) : ", stock.getPrice()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        stock.setStocks(stocks);
        stock.setPrice(price);
        System.out.println("재고정보를 변경하였습니다.");
        return;
      } else {
        System.out.println("재고 변경을 취소하였습니다.");
        return;
      } 
    }
  }
}





