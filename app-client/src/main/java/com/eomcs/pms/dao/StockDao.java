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
  //  List<Product> findByKeyword() throws Exception;
  Stock findByNameId(String name, String id) throws Exception;
  //  Product findByProduct(String name) throws Exception;
  //  void update(Product product) throws Exception;
  //  void delete(Product product) throws Exception;
  int checkPrice(String string);
  int checkNum(String string);
}
