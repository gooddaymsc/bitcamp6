package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockDeleteHandler extends AbstractStockHandler {


  public StockDeleteHandler(List<Stock> stockList) {
    super(stockList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 3 || App.getLoginUser().getAuthority() == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    while(true) {
      System.out.println("[재고 삭제]");
      String name = Prompt.inputString("삭제할 상품이름 : ");
      Stock stock = findByStock(name);

      if (stock == null) {
        System. out.println("해당 이름의 재고가 없습니다.");
        return;
      }

      String input = Prompt.inputString("상품을 판매내역에서 삭제하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        System.out.println("상품을 삭제하였습니다.");
        return;
      } else {
        System.out.println("삭제를 취소하였습니다.");
        return;
      }
    }
  }

}






