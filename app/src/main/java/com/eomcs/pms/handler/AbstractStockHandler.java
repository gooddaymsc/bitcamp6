package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Stock;

public abstract class AbstractStockHandler implements Command {

  List<Stock> stockList;

  public AbstractStockHandler(List<Stock> stockList) {
    this.stockList = stockList;
  }

  protected Stock findByStock (String ProductName) {
    for (Stock stock : stockList) {
      if (stock.product.getProductName().equals(ProductName)) {
        return stock;
      }
    }
    return null;
  } 

}






