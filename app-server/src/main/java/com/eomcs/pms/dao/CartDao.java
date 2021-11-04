package com.eomcs.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Cart;

public interface CartDao {
  void insert(Cart cart) throws Exception;
  List<Cart> findAll(String id) throws Exception;
  Cart findByNo(@Param("cartNumber")int cartNumber, @Param("id")String id) throws Exception;
  void update(Cart cart) throws Exception; 
  void delete(Cart car) throws Exception;
}
