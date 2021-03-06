package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.ProductType;

public interface ProductDao {
  void insert(Product product) throws Exception;
  List<Product> findAll() throws Exception;
  Product findByNo(int no) throws Exception;
  Product findByProduct(String name) throws Exception;
  Product ranking(String name) throws Exception;
  void update(Product product) throws Exception;
  void updateRate(Product product) throws Exception;
  void delete(Product product) throws Exception;
  List<ProductType> findAllProductType() throws Exception;
}
