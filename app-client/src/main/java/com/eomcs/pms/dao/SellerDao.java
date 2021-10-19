package com.eomcs.pms.dao;

import com.eomcs.pms.domain.Member;

public interface SellerDao {
  void insert(Member seller) throws Exception;
  //  List<Seller> findAll() throws Exception;
  //  Seller findById(String id) throws Exception;
  //  void update(Seller seller) throws Exception;
  //  void delete(String id) throws Exception;
}
