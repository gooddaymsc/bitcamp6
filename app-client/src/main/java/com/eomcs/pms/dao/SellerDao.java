package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Seller;

public interface SellerDao {
  void insert(Seller seller) throws Exception;
  List<Seller> findAll() throws Exception;
  Seller findById(String id) throws Exception;
  void update(Seller seller) throws Exception;
  void delete(String id) throws Exception;
}
