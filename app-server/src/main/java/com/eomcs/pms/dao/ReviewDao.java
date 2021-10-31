package com.eomcs.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Review;

public interface ReviewDao {
  void insert(Review review) throws Exception;
  List<Review> findAll(int productNumber) throws Exception;
  Review findByNo(int no) throws Exception;
  void update(Review review) throws Exception;    
  void delete(int no) throws Exception;
  Review reviewIs(@Param("productNo")int productNumber, @Param("id")String id) throws Exception;
  List<Review> myReview(String id) throws Exception;
  boolean findPurchased(String productName) throws Exception;
  float avg(Review review) throws Exception;

}
