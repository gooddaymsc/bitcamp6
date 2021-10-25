package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Cart;

public interface CartDao {
  //  void insert(Cart cart) throws Exception;
  List<Cart> findAll(String id) throws Exception;
  //  HashMap<String, Stock> findBySellerId(String stockName) throws Exception;
  //  HashMap<String, Stock> findBySeller(String stockName) throws Exception;
  //  String findStoreName(Set<String> set, String storeName) throws Exception;
  //  Seller findBySellerInfo(String id) throws Exception;
  //  //  Product findByNo(int no) throws Exception;
  //  //  Product findByProduct(String name) throws Exception;
  //  void update(Cart cart) throws Exception; 
  //  void delete(int cartNo, String id) throws Exception;
  //  Cart findByNo(int cartNo, String id) throws Exception;
}
