package com.eomcs.pms.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Stock;

public class CartHandlerHelper {

  StockDao stockDao;
  public CartHandlerHelper (StockDao stockDao) {
    this.stockDao = stockDao;
  }

  public HashMap<String, Stock> findBySellerId(int productNo) throws Exception {
    HashMap<String, Stock> hashStock= new HashMap<>();
    Collection<Stock> stocks = stockDao.findByProductNo(productNo);

    for (Stock stock : stocks) {
      System.out.printf("가게명 : %s, 판매자 : %s, 재고 : %s, 금액 : %d, 주소 : %s, 판매자연락처 : %s\n", 
          stock.getSeller().getBusinessName(),
          stock.getSeller().getMember().getId(),
          stock.getStocks(),
          stock.getPrice(),
          stock.getSeller().getBusinessAddress(),
          stock.getSeller().getBusinessPlaceNumber());
      hashStock.put(stock.getSeller().getBusinessName(), stock);
    }
    return hashStock;
  }

  public String findStoreName(Set<String> keySet, String storeName) {
    for (String str : keySet) {
      if (str.equals(storeName)) {
        return storeName;
      }
    }
    return null;
  }
}
