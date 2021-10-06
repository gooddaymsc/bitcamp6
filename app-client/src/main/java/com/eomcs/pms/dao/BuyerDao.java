package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;

public interface BuyerDao {
  void insert(Member buyer) throws Exception;
  List<Buyer> findAll() throws Exception;
  Buyer findById(String id) throws Exception;
  void update(Buyer buyer) throws Exception;
  void delete(String id) throws Exception;
}
