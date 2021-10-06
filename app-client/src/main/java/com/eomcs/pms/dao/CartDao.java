package com.eomcs.pms.dao;

import java.util.HashMap;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Stock;

public interface CartDao {
  void insert(Cart cart) throws Exception;
  CartList findAll(String id) throws Exception;
  HashMap<String, Stock> findBySellerId(String stockName) throws Exception;
  void update(Cart cart) throws Exception;
  void delete(int cartNo, String id) throws Exception;
  Cart findByNo(int cartNo, String id) throws Exception;
}
