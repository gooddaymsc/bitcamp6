package com.eomcs.pms.dao;

import java.util.HashMap;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Seller;

public interface BookingDao {
  void insert(String id, Booking booking) throws Exception;
  BookingList findAll(String id) throws Exception;
  Booking findByNoId(int no, String id) throws Exception;
  void delete(Booking booking) throws Exception;
  HashMap<Cart, Seller> findByCartList(String productName, String id) throws Exception;
  Cart findByCart(String productName, String nowLoginId) throws Exception;
  int checkHours(String string, String sellerId) throws Exception;
  int checkMinutes(String string, int bookingHour, String sellerId) throws Exception;
  void deleteCart(String nowLoginId, Cart bookingProduct) throws Exception;
  //  void changeBookingUpdate(String sellerId, boolean b) throws Exception;
  Object bookingStatue(Booking booking) throws Exception;
}
