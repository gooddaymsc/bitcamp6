package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Review;

public interface ReviewDao {
  void insert(Review review) throws Exception;
  List<Review> findAll(String productName) throws Exception;
  //  Product findByNo(int no) throws Exception;
  //  Product findByProduct(String name) throws Exception;
  void update(Review review) throws Exception;
  void delete(Review review) throws Exception;

}
