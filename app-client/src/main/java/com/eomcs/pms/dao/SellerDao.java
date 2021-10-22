package com.eomcs.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;

public interface SellerDao {
  void insert(Member seller) throws Exception;
  List<Seller> findAll() throws Exception;
  Seller findById(String id) throws Exception;
  void update(Seller seller) throws Exception;
  void delete(Seller seller) throws Exception;
  void insertSeller(@Param("memberNo") int memberNo, @Param("businessName") String businessName, 
      @Param("businessNumber") String businessNumber, @Param("businessClosingTime") String businessAddress, 
      @Param("businessPlaceNumber") String businessPlaceNumber, 
      @Param("businessOpeningTime") String businessOpeningTime, 
      @Param("businessClosingTime") String businessClosingTime) throws Exception;
}
