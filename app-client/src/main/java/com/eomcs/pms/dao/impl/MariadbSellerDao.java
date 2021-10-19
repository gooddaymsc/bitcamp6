package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;

public class MariadbSellerDao implements SellerDao {
  Connection con;

  public MariadbSellerDao(Connection con) {
    this.con =  con;
  }

  @Override
  public void insert(Member seller) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert"
            + " into member(member_no, id, password, authority, name, nickname, email, birthday,"
            + " photo, phoneNumber, active, level)" 
            + " values(?, ?, password(?), ?, ?, ?, ?, ?, ?, ?, ?, ?)")){

      stmt.setInt(1, seller.getNumber());
      stmt.setString(2, seller.getId());
      stmt.setString(3, seller.getPassword());
      stmt.setInt(4, seller.getAuthority());
      stmt.setString(5, seller.getName());
      stmt.setString(6, seller.getNickname());
      stmt.setString(7, seller.getEmail());
      stmt.setDate(8, seller.getBirthday());
      stmt.setString(9, seller.getPhoto());
      stmt.setString(10, seller.getPhoneNumber());
      stmt.setInt(11, seller.getActive());
      stmt.setInt(12, seller.getLevel());


      if(stmt.executeUpdate() == 0) {
        throw new Exception("회원 저장 실패");
      }
    }

    try(PreparedStatement stmt2 = con.prepareStatement(
        "insert"
            + " into seller(member_no, business_name,  business_no, business_address, business_tel, openingTime, closingTime)"
            + "values(?, ?, ?, ?, ?, ?, ?)")){   

      stmt2.setInt(1, seller.getNumber());
      stmt2.setString(2, ((Seller) seller).getBusinessName());
      stmt2.setString(3, ((Seller) seller).getBusinessNumber());
      stmt2.setString(4, ((Seller) seller).getBusinessAddress());
      stmt2.setString(5, ((Seller) seller).getBusinessPlaceNumber());
      stmt2.setString(6, ((Seller) seller).getBusinessOpeningTime());
      stmt2.setString(7, ((Seller) seller).getBusinessClosingTime());



      if(stmt2.executeUpdate() == 0) {
        throw new Exception("판매자 저장 실패");
      }
    }

  }

  //@Override
  //public List<Seller> findAll() throws Exception {
  //  requestAgent.request("seller.selectList", null);
  //  if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //    throw new Exception("회원 목록 조회 실패!");
  //  }
  //
  //  return new ArrayList<>(requestAgent.getObjects(Seller.class));
  //}
  //
  //@Override
  //public Seller findById(String id) throws Exception {
  //  HashMap<String,String> params = new HashMap<>();
  //  params.put("id", id);
  //  requestAgent.request("seller.selectOne", params);
  //
  //  if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //    return null;
  //  }
  //  return requestAgent.getObject(Seller.class);
  //}
  //
  //@Override
  //public void update(Seller seller) throws Exception {
  //  requestAgent.request("seller.update", seller);
  //  if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //    System.out.println("회원 변경 실패!");
  //  }
  //  requestAgent.request("member.update", seller);
  //  if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //    throw new Exception("회원 변경 실패!");
  //  }
  //}
  //
  //@Override
  //public void delete(String id) throws Exception {
  //  HashMap<String, String> params = new HashMap<>();
  //  params.put("id", id);
  //
  //  requestAgent.request("seller.delete", params);
  //  if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //    throw new Exception("회원 삭제 실패!");
  //  }
  //  requestAgent.request("member.delete", params);
  //  if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //    throw new Exception("회원 삭제 실패!");
  //  }
  //}
}
