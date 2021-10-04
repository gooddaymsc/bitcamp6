package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockUpdateHandler implements Command {

  StockDao stockDao;

  public StockUpdateHandler(StockDao stockDao) {
    this.stockDao = stockDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    while(true) {
      System.out.println("[재고 변경]");

      String stockName = (String)request.getAttribute("stock");
      Stock stock = stockDao.findByNameId(stockName, nowLoginId);

      if (stock == null) {
        System. out.println("해당 상품의 재고가 없습니다.\n");
        return;
      }

      int stocks = stockDao.checkNum2(String.format("수량(변경 전 : %d) : ", stock.getStocks()));
      int price = stockDao.checkPrice(String.format("가격(변경 전 : %d) : ", stock.getPrice()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        stock.setStocks(stocks);
        stock.setPrice(price);
        System.out.println("재고정보를 변경하였습니다.\n");
        stockDao.update(stock);
        return;
      } else {
        System.out.println("재고 변경을 취소하였습니다.\n");
        return;
      } 
    }
  }
}






