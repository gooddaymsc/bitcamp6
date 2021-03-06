package com.eomcs.pms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class StockList implements Serializable{
  private String id; // 판매자
  private List<Stock> sellerStock = new ArrayList<>();
  private int stockListNumber = 1;

  public int getStockListNumber() {
    return stockListNumber;
  }
  public void setStockListNumber(int stockListNumber) {
    this.stockListNumber = stockListNumber;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id; //판매자
  }
  public List<Stock> getSellerStock() {
    return sellerStock;
  }
  public void setSellerStock(List<Stock> sellerStock) {
    this.sellerStock = sellerStock;
  }


}
