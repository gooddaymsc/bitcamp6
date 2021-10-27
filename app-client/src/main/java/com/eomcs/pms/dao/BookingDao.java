package com.eomcs.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Booking;

public interface BookingDao {
  void insert(Booking booking) throws Exception;
  void insertList(Booking booking) throws Exception;
  List<Booking> findAll1(String id) throws Exception; //구매자
  List<Booking> findAll2(String id) throws Exception; //판매자
  Booking findByNo1(@Param("bookingNo")int bookingNo, @Param("id")String id) throws Exception; // 구매
  Booking findByNo2(@Param("bookingNo")int bookingNo, @Param("id")String id) throws Exception; // 판매자

  void delete(int bookingNo) throws Exception;
  //  HashMap<Cart, Seller> findByCartList(String productName, String id) throws Exception;
  //  Cart findByCart(String productName, String nowLoginId) throws Exception;
  //  void deleteCart(String nowLoginId, Cart bookingProduct) throws Exception;
  //  void update(Booking booking) throws Exception;
  //  //  void changeBookingUpdate(String sellerId, boolean b) throws Exception;
  //  String checkTime(String label, String sellerId) throws Exception;
}
