
package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import java.util.Set;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.request.RequestAgent;

public class NetCartDao implements CartDao{
  RequestAgent requestAgent;
  SellerDao sellerDao;
  StockDao stockDao;
  public NetCartDao(RequestAgent requestAgent, SellerDao sellerDao, StockDao stockDao) {
    this.requestAgent = requestAgent;
    this.sellerDao = sellerDao;
    this.stockDao = stockDao;
  }

  @Override
  public void insert(Cart cart) throws Exception {
    requestAgent.request("cart.insert", cart);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("장바구니 데이터 저장 실패");
    }
  }

  @Override
  public CartList findAll(String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);
    requestAgent.request("cart.selectList", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("재고목록 불러오기 실패");
    }
    return requestAgent.getObject(CartList.class);
  }

  @Override
  public Cart findByNo(int cartNo, String id) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("cartNo", String.valueOf(cartNo));
    params.put("id", id);

    requestAgent.request("cart.selectOne", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(requestAgent.getObject(String.class));
      return null;
    }

    return requestAgent.getObject(Cart.class);
  }

  @Override
  public void update(Cart cart) throws Exception {
    requestAgent.request("cart.update", cart);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("장바구니 변경 실패");
    }
  }

  @Override
  public void delete(int cartNo, String id) throws Exception {
    Cart cart = new Cart();
    cart.setId(id);
    cart.setCartNumber(cartNo);
    requestAgent.request("cart.delete", cart);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("장바구니 삭제 실패");
    }  
  }

  @Override
  public HashMap<String, Stock> findBySellerId(String stockName) throws Exception {
    HashMap<String, Stock> hashStock= new HashMap<>();

    for (StockList stockList : stockDao.findAll()) {
      for (Stock stock : stockList.getSellerStock()) {
        if (stock.getProduct().getProductName().equals(stockName)) {
          //          isStock = true;

          Seller sellerInfo = sellerDao.findById(stockList.getId());;
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

  @Override
  public HashMap<String, Stock> findBySeller(String stockName) throws Exception {
    HashMap<String, Stock> hashStock= new HashMap<>();
    for (StockList stockList : stockDao.findAll()) {
      for (Stock stock : stockList.getSellerStock()) {
        if (stock.getProduct().getProductName().equals(stockName)) {

          Seller sellerInfo = sellerDao.findById(stockList.getId());;
          hashStock.put(sellerInfo.getBusinessName(), stock);
        }
      }
    }
    return hashStock;
  }


  @Override
  public String findStoreName(Set<String> keySet, String storeName) {
    for (String str : keySet) {
      if (str.equals(storeName)) {
        return storeName;
      }
    }
    return null;
  }

  @Override
  public Seller findBySellerInfo(String id) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("seller.selectOne", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return requestAgent.getObject(Seller.class);
  }
}
