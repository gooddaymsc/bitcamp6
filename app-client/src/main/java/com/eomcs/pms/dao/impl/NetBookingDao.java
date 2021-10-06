
package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


  // 먼저 buyer 기준으로만.
  @Override
  public List<Booking> findBookingBuyer(int no, String firstId, String secondId, boolean delete) throws Exception {
    List<Booking> twoBookingList = new ArrayList<>();
    BookingList bookingList = findAll(firstId);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == no) {
        twoBookingList.add(booking);
        if (delete) {
          bookingList.getBooking().remove(booking);
        }
        bookingList = findAll(secondId);
        for (Booking booking2 : bookingList.getBooking()) {
          if (booking2.getMineId().equals(firstId)
              && booking2.getCart().getStock().getProduct().getProductName().equals(
                  booking.getCart().getStock().getProduct().getProductName())) {
            twoBookingList.add(booking2);
            if (delete) {
              bookingList.getBooking().remove(booking2);
            }
            return twoBookingList;
          }
        }
      }
    }
    return null;
  }
  //  @Override
  //  public List<BookingList> findAll() throws Exception {
  //    requestAgent.request("booking.selectAllList", null);
  //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
  //      throw new Exception("재고목록 불러오기 실패");
  //    }
  //    return new ArrayList<>(requestAgent.getObjects(BookingList.class));
  //  } 

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
  public void delete(Booking booking) throws Exception {
    requestAgent.request("booking.delete", booking);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("예약 삭제 실패");
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
  public void deleteCart(String id, Cart cart) throws Exception {
    requestAgent.request("cart.delete", cart);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("장바구니 삭제 실패!");
    }
  }
  // 판매자의 아이디를 이용해 영업시간 알아내서 분 비교하기
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

  @Override
  public String bookingStatue(Booking booking) {
    String statue ;
    if(booking.isConfirm() == true) {
      statue = "픽업완료";
    }
    else {
      statue = "픽업예정";
    }
    return statue;
  }
  //  
  //  public void changeBookingUpdate(String id, Boolean bool) throws Exception {
  //    requestAgent.request("member.selectList", );
  //    for (Member member : memberList) {
  //      if (member.getId().equals(id)) {
  //        member.setBookingUpdate(bool);
  //      }
  //    }
  //  }



  // 먼저 seller 기준으로만.
  @Override
  public List<Booking> findBookingSeller(int no, String firstId, String secondId, boolean delete) throws Exception {
    List<Booking> twoBookingList = new ArrayList<>();
    BookingList bookingList = findAll(firstId);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == no) {
        twoBookingList.add(booking);
        if (delete) {
          bookingList.getBooking().remove(booking);
        }
        bookingList = findAll(secondId);
        for (Booking booking2 : bookingList.getBooking()) {
          if (booking2.getCart().getSellerId().equals(firstId)
              && booking2.getCart().getStock().getProduct().getProductName().equals(
                  booking.getCart().getStock().getProduct().getProductName())) {
            twoBookingList.add(booking2);
            if (delete) {
              bookingList.getBooking().remove(booking2);
            }
            return twoBookingList;
          }
        }
      }
    }
    return null;
  }

}
