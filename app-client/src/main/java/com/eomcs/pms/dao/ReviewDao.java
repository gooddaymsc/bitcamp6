package com.eomcs.pms.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;

public interface ReviewDao {
  void insert(Product product) throws Exception;
  void insertReview(Review review) throws Exception;
  List<Review> findAll(int productNumber) throws Exception;
  void updateReview(Review review) throws Exception;    
  void deleteReview(Review review) throws Exception;
  HashMap<String,Seller> findByAdress(String address) throws Exception;
  Stock findStockById(String id, int productNumber) throws Exception;
  Review reviewIs(@Param("productNo")int productNumber, @Param("id")String id) throws Exception;
  boolean findPurchased(String productName) throws Exception;
}
