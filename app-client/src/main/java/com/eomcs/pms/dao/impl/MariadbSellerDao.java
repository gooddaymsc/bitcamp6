package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
            + " photo, phoneNumber)" 
            + " values(?, ?, password(?), ?, ?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS)) {

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

      if(stmt.executeUpdate() == 0) {
        throw new Exception("회원 저장 실패");
      }

      int member_no = 0;
      try (ResultSet pkRS = stmt.getGeneratedKeys()) {
        if (pkRS.next()) {
          member_no = pkRS.getInt("member_no");
        }
      }

      try(PreparedStatement stmt2 = con.prepareStatement(
          "insert"
              + " into seller(member_no, business_name,  business_no, business_address, business_tel, openingTime, closingTime)"
              + " values(?, ?, ?, ?, ?, ?, ?)")){

        stmt2.setInt(1, member_no);
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
  }

  @Override
  public List<Seller> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " m.member_no, id, business_name, name,nickname,level,registeredDate"
            + " from member m join seller s on m.member_no = s.member_no"
            + " order by m.member_no desc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Seller> list = new ArrayList<>();

      while (rs.next()) {
        Seller seller = new Seller();
        seller.setNumber(rs.getInt("member_no"));
        seller.setId(rs.getString("id"));
        seller.setBusinessName(rs.getString("business_name"));
        seller.setName(rs.getString("name"));
        seller.setNickname(rs.getString("nickname"));
        seller.setLevel(rs.getInt("level"));
        seller.setRegisteredDate(rs.getDate("registeredDate"));

        list.add(seller);
      }

      return list;
    }
  }

  @Override
  public Seller findById(String id) throws Exception {
    return null;
  }

  @Override
  public void update(Seller seller) throws Exception {
  }

  @Override
  public void delete(String id) throws Exception {
  }

}
