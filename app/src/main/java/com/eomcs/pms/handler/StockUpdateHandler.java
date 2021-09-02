package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockUpdateHandler extends AbstractStockHandler {

  @Override
  public void execute(int i) {}

  public StockUpdateHandler(List<Stock> stockList) {
    super(stockList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0 || App.getLoginUser().getAuthority() == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    while(true) {
      System.out.println("[재고 변경]");

      Stock stock = findByStock(Prompt.inputString("상품명 : "));

      if (stock == null) {
        System. out.println("해당 상품의 재고가 없습니다.");
        return;
      }

      int stocks = Prompt.inputInt("재고수량(" + stock.getStocks() + ") :");
      int price = Prompt.inputInt("가격(" + stock.getPrice() + ") : ");

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        stock.setPrice(stocks);
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






