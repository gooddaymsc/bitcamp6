package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.util.Prompt;

public class StockDeleteHandler extends AbstractStockHandler {

  public StockDeleteHandler(StockPrompt stockPrompt) {
    super(stockPrompt);
  }
  @Override
  public void execute(CommandRequest request) {
    String nowLoginId = App.getLoginUser().getId();
    while(true) {
      System.out.println("[재고 삭제]");

      String stockName = Prompt.inputString("삭제할 상품명 : ");
      if (!stockPrompt.findByStock(stockName, nowLoginId)) {
        System. out.println("해당 이름의 재고가 없습니다.");
        return;
      }

      String input = Prompt.inputString("상품을 판매내역에서 삭제하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        if (!stockPrompt.removeStockById(stockName, nowLoginId)) {
          System.out.println("삭제가 제대로 이뤄지지 않음");
          return;
        }
        System.out.println("상품을 삭제하였습니다.");
        return;
      } else {
        System.out.println("삭제를 취소하였습니다.");
        return;
      }
    }
  }

}






