package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;

public class StockAddHandler implements Command {
  StockDao stockDao;
  public StockAddHandler(StockDao stockDao) {
    this.stockDao = stockDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {   

    String nowLoginId = ClientApp.getLoginUser().getId();

    System.out.println("[재고등록]");
    Stock stock = new Stock();
    String productName = (String) request.getAttribute("productName");
    Product product = stockDao.checkProduct(productName);
    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.\n");
      return;
    }
    Stock stockcheck = stockDao.findByNameId(productName, nowLoginId);
    if (stockcheck != null) {
      System.out.println("이미 추가된 상품입니다.\n");
      return;
    }
    stock.setProduct(product);
    stock.setPrice(stockDao.checkPrice("판매 가격 : "));
    stock.setStocks(stockDao.checkNum("재고 수량 : "));
    stock.setId(nowLoginId);
    stockDao.insert(stock);
    System.out.println("재고 등록을 완료하였습니다.\n");
  }

}