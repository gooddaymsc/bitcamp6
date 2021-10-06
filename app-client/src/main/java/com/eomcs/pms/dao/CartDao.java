package com.eomcs.pms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;

public interface CartDao {
  void insert(Cart cart) throws Exception;
  CartList findAll(String id) throws Exception;
  List<CartList> findAll() throws Exception;
  HashMap<String, Stock> findBySellerId(String stockName) throws Exception;
  String findStoreName(Set<String> set, String storeName) throws Exception;
  int checkNum(String label) throws Exception;
  Seller findBySellerInfo(String id) throws Exception;
  //  Product findByNo(int no) throws Exception;
  //  Product findByProduct(String name) throws Exception;
  void update(Cart cart) throws Exception;
  void delete(int cartNo, String id) throws Exception;
  Cart findByNo(int cartNo, String id) throws Exception;
}
