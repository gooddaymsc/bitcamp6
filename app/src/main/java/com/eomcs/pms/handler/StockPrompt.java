package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public class StockPrompt {
  List<SellerPrivacy> sellerPrivacyList;

  public StockPrompt(List<SellerPrivacy> sellerPrivacyList) {
    this.sellerPrivacyList = sellerPrivacyList;
  }

  public StockList findStockListById(String id) {
    for (StockList stockList : App.allStockList) {
      if (stockList.getId().equals(id)) {
        return stockList;
      }
    }
    return null;
  }

  public Stock findStockById(String id, String stockName) {
    StockList stockList = findStockListById(id);
    for (Stock stock : stockList.getSellerStock()) {
      if (stock.getProduct().getProductName().equals(stockName)) {
        return stock;
      }
    }
    return null;
  }

  public Stock findByStock (String ProductName) {
    StockList stockList = findStockListById(App.getLoginUser().getId());
    for (Stock stock : stockList.getSellerStock()) {
      if (stock.getProduct().getProductName().equals(ProductName)) {
        return stock;
      }
    }
    return null;
  }

  //--------상품 검색시 판매자정보 반환
  protected HashMap<String, Stock> findBySellerId (String StockName) {
    //    Stock stockName = findByStock(StockName);
    //    if (stockName==null) {
    //      System.out.println("존재하지 않는 상품입니다.");
    //      return null;
    //    }

    HashMap<String, Stock> hashStock= new HashMap<>();
    for (StockList stockList : App.allStockList) {
      for (Stock stock : stockList.getSellerStock()) {
        if (stock.getProduct().getProductName().equals(StockName)) {
          //          isStock = true;
          SellerPrivacy sellerInfo = findBySellerInfo(stockList.getId());
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

  public SellerPrivacy findBySellerInfo (String SellerId) {
    for (SellerPrivacy member : sellerPrivacyList) {
      if (member.getName().equals(SellerId)){
        return member;
      }
    }
    return null;
  }

  public List<Cart> findCartListById(String id) {
    for (CartList cartList : App.allCartList) {
      if (cartList.getId().equals(id)) {
        return cartList.getPrivacyCart();
      }
    }
    return null;
  }

  public SellerPrivacy findByPlaceName (String storeName) {
    for (SellerPrivacy member : sellerPrivacyList) {
      if (member.getBusinessName().equals(storeName)){
        return member;
      }
    }
    return null;
  }


  //입력한 문자열을 포함하면 adress 리턴.
  public HashMap<String, SellerPrivacy> findByAdress (String adress) {
    HashMap<String, SellerPrivacy> hashMap = new HashMap<>();
    for (SellerPrivacy member : sellerPrivacyList) {
      if((member.getBusinessAddress()).contains(adress)) {
        hashMap.put(member.getId(), member);
      }
    }
    return hashMap;
  }


  //  HashMap<String, Stock> hashStock= new HashMap<>();
  //  for (StockList stockList : App.allStockList) {
  //    for (Stock stock : stockList.getSellerStock()) {
  //      if (stock.getProduct().getProductName().equals(StockName)) {
  //        //          isStock = true;
  //        SellerPrivacy sellerInfo = findBySellerInfo(stockList.getId());
  //        System.out.printf("가게명 : %s, 판매자 : %s, 재고 : %s, 금액 : %d, 주소 : %s, 판매자연락처 : %s\n", 
  //            sellerInfo.getBusinessName(),
  //            stockList.getId(),
  //            stock.getStocks(),
  //            stock.getPrice(),
  //            sellerInfo.getBusinessAddress(),
  //            sellerInfo.getBusinessPlaceNumber());
  //        hashStock.put(sellerInfo.getBusinessName(), stock);
  //      }
  //    }
  //  }
  //  return hashStock;
  //}


}
