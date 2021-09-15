package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public class StockPrompt {
  List<StockList> allStockList;
  MemberPrompt memberPrompt;

  public StockPrompt(List<StockList> allStockList, MemberPrompt memberPrompt) {
    this.allStockList = allStockList;
    this.memberPrompt = memberPrompt;
  }


  protected boolean removeStockById(String stockName, String id) {
    StockList stockList = allStockList.get(findStockListById(id));
    for (int i=0; i<stockList.getSellerStock().size(); i++) {
      if (stockList.getSellerStock().get(i).getProduct().getProductName().equals(stockName)) {
        allStockList.get(findStockListById(id)).getSellerStock().remove(i);
        return true;
      }
    }
    return false;
  }

  protected void putStockListById(String nowLoginId, Stock stock) {
    for (StockList stockList : allStockList) {
      if (stockList.getId().equals(nowLoginId)) {
        int stockListNumber = stockList.getStockListNumber();
        stock.setStockNumber(stockListNumber);
        stockList.getSellerStock().add(stock);
        stockList.setStockListNumber(++stockListNumber);
        ;
      }
    }
  }

  public int findStockListById(String id) {
    for (int i=0; i< allStockList.size(); i++) {
      if (allStockList. get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  public Stock findStockById(String id, String stockName) {
    StockList stockList = allStockList.get(findStockListById(id));
    for (Stock stock : stockList.getSellerStock()) {
      if (stock.getProduct().getProductName().equals(stockName)) {
        return stock;
      }
    }
    return null;
  }

  public boolean findByStock (String ProductName, String nowLoginId) {

    for (int i=0; i< allStockList.size(); i++) {
      if (allStockList.get(i).getId().equals(nowLoginId)) {
        for (Stock stock : allStockList.get(i).getSellerStock()) {
          if (stock.getProduct().getProductName().equals(ProductName)) {
            return true;
          }
        }
      }
    }
    return false;
  }


  //--------상품 검색시 판매자정보 반환
  protected HashMap<String, Stock> findBySellerId (String StockName) {
    //    Stock stockName = findByStock(StockName);
    //    if (stockName==null) {
    //      System.out.println("존재하지 않는 상품입니다.");
    //      return null;
    //    }

    HashMap<String, Stock> hashStock= new HashMap<>();
    for (StockList stockList : allStockList) {
      for (Stock stock : stockList.getSellerStock()) {
        if (stock.getProduct().getProductName().equals(StockName)) {
          //          isStock = true;
          Seller sellerInfo = memberPrompt.findBySellerInfo(stockList.getId());
          System.out.printf("가게명 : %s, 판매자 : %s, 재고 : %s, 금액 : %d, 주소 : %s, 판매자연락처 : %s\n", 
              sellerInfo.getBusinessName(),
              stockList.getId(),
              stock.getStocks(),
              stock.getPrice(),
              sellerInfo.getBusinessAddress(),
              sellerInfo.getBusinessPlaceNumber());
          hashStock.put(sellerInfo.getBusinessName(), stock);
        }
      }
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

  public void addStockListById(String id) {
    StockList StockList = new StockList();
    StockList.setId(id);
    allStockList.add(StockList);
  }

  protected int getStockIndexById(String id) {
    for (int i=0; i< allStockList.size(); i++) {
      if (allStockList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
  //  protected StockList  getStockListById(String id) {
  //    for (int i=0; i< allStockList.size(); i++) {
  //      if (allStockList.get(i).getId().equals(id)) {
  //        return sellerStock;
  //      }
  //    }
  //    return null;
  //  }


  public void removeStockListById(String nowLoginid) {
    allStockList.remove(getStockIndexById(nowLoginid));
  }
}



