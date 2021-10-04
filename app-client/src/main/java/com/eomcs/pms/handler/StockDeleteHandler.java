package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.util.Prompt;

public class StockDeleteHandler implements Command {

  StockDao stockDao;

  public StockDeleteHandler(StockDao stockDao) {
    this.stockDao = stockDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    while(true) {
      System.out.println("[재고 삭제]");

      String stockName = (String)request.getAttribute("stock");
      if (stockDao.findStockById(stockName, nowLoginId) == null) {
        System. out.println("해당 이름의 재고가 없습니다.\n");
        return;
      }

      String input = Prompt.inputString("상품을 판매내역에서 삭제하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        if (!stockDao.removeStockById(stockName, nowLoginId)) {
          System.out.println("삭제가 제대로 이뤄지지 않음.\n");
          return;
        }
        System.out.println("상품을 삭제하였습니다.\n");
        return;
      } else {
        System.out.println("삭제를 취소하였습니다.\n");
        return;
      }
    }
  }

}






