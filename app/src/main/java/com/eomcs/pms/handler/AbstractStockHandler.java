package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.StockList;

public abstract class AbstractStockHandler implements Command {

  // Stock findByStock(String ProductName) 과 StockList findById(String id) 
  // => StockPrompt 에 있음.
  StockPrompt stockPrompt;
  List<StockList> allStockList;
  public AbstractStockHandler(StockPrompt stockPrompt, List<StockList> allStockList) {
    this.stockPrompt = stockPrompt;
    this.allStockList = allStockList;
  }
}