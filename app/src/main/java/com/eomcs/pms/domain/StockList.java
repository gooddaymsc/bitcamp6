package com.eomcs.pms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class StockList implements Serializable{
  private String id;
  private List<Stock> sellerStock = new ArrayList<>();

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
