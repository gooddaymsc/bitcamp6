package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public abstract class AbstractStockHandler implements Command {

  List<Stock> stockList;

  protected Stock findByStock (String ProductName) {
    StockList stockList = findById(App.getLoginUser().getId());
    for (Stock stock : stockList.getSellerStock()) {
      if (stock.getProduct().getProductName().equals(ProductName)) {
        return stock;
      }
    }
    return null;
  } 


  protected StockList findById(String id) {
    for (StockList stockList : App.allStockList) {
      if (stockList.getId().equals(id)) {
        return stockList;
      }
    }
    return null;
  }
}