package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.ProductType;

public interface ProductDao {
  void insert(Product product) throws Exception;
  List<Product> findAll() throws Exception;
  Product findByNo(int no) throws Exception;
  //  // Product findByNo2(int reviewNo) throws Exception;
  Product findByProduct(String name) throws Exception;
  Product ranking(String name) throws Exception;
  void update(Product product) throws Exception;
  void updateRate(Product product) throws Exception;
  void delete(Product product) throws Exception;
  //  void insertReview(Review review) throws Exception;
  //  List<Review> findAll(int productNumber) throws Exception;
  //  void updateReview(Review review) throws Exception;    
  //  void deleteReview(Review review) throws Exception;
  //  HashMap<String,Seller> findByAdress(String address) throws Exception;
  //  Stock findStockById(String id, int productNumber) throws Exception;
  //  boolean reviewIs(int productNumber, String id) throws Exception;
  //  boolean findPurchased(String productName) throws Exception;
  List<ProductType> findAllProductType() throws Exception;
}
