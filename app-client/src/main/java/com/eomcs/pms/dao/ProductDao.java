package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;

public interface ProductDao {
  void insert(Product product) throws Exception;
  List<Product> findAll() throws Exception;
  Product findByNo(int no) throws Exception;
  Product findByProduct(String name) throws Exception;
  Review findReviewById(Product productName, String id) throws Exception;
  void update(Product product) throws Exception;
  void delete(Product product) throws Exception;
  void insertReview(Review review) throws Exception;
  List<Review> findAll(int productNumber) throws Exception;
  void updateReview(Review review) throws Exception;
  void deleteReview(Review review) throws Exception;
  boolean reviewIs(int productNumber, String id) throws Exception;
}
