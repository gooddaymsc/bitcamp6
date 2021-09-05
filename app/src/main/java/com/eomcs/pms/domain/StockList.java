package com.eomcs.pms.domain;

import java.util.ArrayList;
import java.util.List;

public class StockList {
  private String id;
  private List<Stock> sellerStock = new ArrayList<>();

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public List<Stock> getSellerStock() {
    return sellerStock;
  }
  public void setSellerStock(List<Stock> sellerStock) {
    this.sellerStock = sellerStock;
  }


}
