package com.eomcs.pms.handler;

public abstract class AbstractStockHandler implements Command {

  // Stock findByStock(String ProductName) 과 StockList findById(String id) 
  // => StockPrompt 에 있음.
  StockPrompt stockPrompt;

  public AbstractStockHandler(StockPrompt stockPrompt) {
    this.stockPrompt = stockPrompt;
  }
}