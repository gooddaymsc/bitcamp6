
package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Seller;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class NetBookingDao implements BookingDao{
  RequestAgent requestAgent;
  CartDao cartDao;
  SellerDao sellerDao;
  public NetBookingDao(RequestAgent requestAgent, CartDao cartDao, SellerDao sellerDao) {
    this.cartDao = cartDao;
    this.requestAgent = requestAgent;
    this.sellerDao = sellerDao;
  }

  @Override
  public void insert(String id, Booking booking) throws Exception {
    requestAgent.request("booking.insert", booking);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("예약 데이터 저장 실패");
    }
  }

  @Override
  public BookingList findAll(String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);
    requestAgent.request("booking.selectList", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("예약목록 불러오기 실패");
    }
    return requestAgent.getObject(BookingList.class);
  }

  @Override
  public Booking findByNoId(int no, String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));
    params.put("id", id);

    requestAgent.request("booking.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Booking.class);
  }

  @Override
  public void update(Booking booking) throws Exception {
    requestAgent.request("booking.update", booking);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("예약 변경 실패!");
    }
  }

  @Override
  public void delete(Booking booking) throws Exception {
    requestAgent.request("booking.delete", booking);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("예약 삭제 실패!");
    }
  }

  @Override
  public HashMap<Cart, Seller> findByCartList (String ProductName, String id) throws Exception {
    HashMap<Cart, Seller> hashStock= new HashMap<>();
    CartList cartList = cartDao.findAll(id);
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        Seller sellerInfo = sellerDao.findById(cart.getSellerId());
        System.out.printf(">> 가게명 : %s, 담은 갯수 : %s\n" ,
            sellerInfo.getBusinessName(),
            cart.getCartStocks());
        hashStock.put(cart, sellerInfo);
      }
    }
    return hashStock;
  }

  @Override
  public Cart findByCart(String ProductName, String nowLoginId) throws Exception {
    CartList cartList = cartDao.findAll(nowLoginId);
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        return cart;
      }
    }
    return null;
  }

  @Override
  public void deleteCart(String id, Cart cart) throws Exception {
    requestAgent.request("cart.delete", cart);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("장바구니 삭제 실패!");
    }
  }

  @Override
  public int checkHours (String label, String sellerId) throws Exception {
    Seller seller = sellerDao.findById(sellerId);
    while(true) {
      int hours = Prompt.inputInt(label);
      if(hours < seller.getBusinessOpeningHours() || hours > seller.getBusinessClosingHours()) {  
        System.out.println("영업시간이 아닙니다.\n"); 
        System.out.printf("오픈시간: %s시 %s분\n", 
            seller.getBusinessOpeningHours(), seller.getBusinessOpeningMinutes());
        System.out.printf("마감시간: %s시 %s분\n", 
            seller.getBusinessClosingHours() ,seller.getBusinessClosingMinutes());
        continue;
      }           
      return hours;       
    }
  }

  @Override
  public int checkMinutes (String label, int hours, String sellerId) throws Exception {
    Seller seller = sellerDao.findById(sellerId);
    while(true) {
      int minutes = Prompt.inputInt(label);
      if (hours == seller.getBusinessOpeningHours()) {
        if((minutes < seller.getBusinessOpeningMinutes() || minutes > 59)) {
          System.out.println("영업시간이 아닙니다.\n"); 
          System.out.printf("오픈시간: %s시 %s분\n", 
              seller.getBusinessOpeningHours(), seller.getBusinessOpeningMinutes());
          System.out.printf("마감시간: %s시 %s분\n", 
              seller.getBusinessClosingHours() ,seller.getBusinessClosingMinutes());
          continue;
        } 
      }
      if (hours == seller.getBusinessClosingHours()) {
        if (minutes > seller.getBusinessClosingMinutes() || minutes <0) {
          System.out.println("영업시간이 아닙니다.\n"); 
          System.out.printf("오픈시간: %s시 %s분\n", 
              seller.getBusinessOpeningHours(), seller.getBusinessOpeningMinutes());
          System.out.printf("마감시간: %s시 %s분\n", 
              seller.getBusinessClosingHours() ,seller.getBusinessClosingMinutes());
          continue;
        }
      }
      return minutes;
    }
  }
  //  public void changeBookingUpdate(String id, Boolean bool) throws Exception {
  //    requestAgent.request("member.selectList", );
  //    for (Member member : memberList) {
  //      if (member.getId().equals(id)) {
  //        member.setBookingUpdate(bool);
  //      }
  //    }
  //  }
}
