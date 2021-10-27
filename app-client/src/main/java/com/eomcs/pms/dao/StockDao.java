package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public interface StockDao {
  void insert(Stock stock2) throws Exception;
  List<Stock> findAll(String id) throws Exception;
  List<Stock> findByProductNo(int ProductNo) throws Exception;
  List<StockList> findAll() throws Exception;
  Stock checkProduct(String name) throws Exception; 
  Stock findBySeller(int no) throws Exception;
  void update(Stock stock) throws Exception;
  void delete(Stock stock) throws Exception;
}
