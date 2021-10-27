package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;

public class StockAddHandler implements Command {
  StockDao stockDao;
  SellerDao sellerDao;
  SqlSession sqlSession;
  public StockAddHandler(StockDao stockDao, SellerDao sellerDao, SqlSession sqlSession) {
    this.stockDao = stockDao;
    this.sellerDao = sellerDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {   

    String nowLoginId = ClientApp.getLoginUser().getId();

    System.out.println("[재고등록]");
    Stock stock = new Stock();
    String productName = (String) request.getAttribute("productName");

    stock = stockDao.checkProduct(productName);

    if (stock.getProduct() == null) {
      System.out.println("입력하신 상품이 없습니다.\n");
      return;
    }
    Stock stockcheck = stockDao.findByNameId(productName, nowLoginId);
    if (stockcheck != null) {
      System.out.println("이미 재고에 추가된 상품입니다.\n");
      return;
    }
    Stock stock2 = new Stock();
    stock2.setProduct(stock.getProduct());
    stock2.setPrice(StockValidation.checkPrice("판매 가격 : "));
    stock2.setStocks(StockValidation.checkNum("재고 수량 : "));
    Seller seller = sellerDao.findById(nowLoginId);
    stock2.setSeller(seller);
    stockDao.insert(stock2);
    sqlSession.commit();

    System.out.println("재고 등록을 완료하였습니다.\n");
  }

}