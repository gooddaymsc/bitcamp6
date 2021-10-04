package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public interface StockDao {
  void insert(Stock stock) throws Exception;
  StockList findAll(String id) throws Exception;
  List<StockList> findAll() throws Exception;
  Product checkProduct(String name) throws Exception; 
  Stock findByNameId(String name, String id) throws Exception;
  void update(Stock Stock) throws Exception;
  void delete(Stock stock) throws Exception;
  int checkPrice(String string);
  int checkNum(String string);
  int checkNum2(String string);
}
