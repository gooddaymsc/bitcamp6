package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockDeleteHandler implements Command {

  StockDao stockDao;
  SqlSession sqlSession;

  public StockDeleteHandler(StockDao stockDao, SqlSession sqlSession) {
    this.stockDao = stockDao;
    this.sqlSession = sqlSession;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    while(true) {
      System.out.println("[재고 삭제]");

      String stockName = (String)request.getAttribute("stock");
      if (stockDao.findByNameId(stockName, nowLoginId) == null) {
        System. out.println("해당 이름의 재고가 없습니다.\n");
        return;
      }

      String input = Prompt.inputString("상품을 판매내역에서 삭제하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        Stock stock = stockDao.findByNameId(stockName, nowLoginId);
        System.out.println("상품을 삭제하였습니다.\n");
        stockDao.delete(stock);
        sqlSession.commit();
        return;
      } else {
        System.out.println("삭제를 취소하였습니다.\n");
        return;
      }
    }
  }

}






