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
    try(PreparedStatement stmt = con.prepareStatement(
        "select"
            + " s.member_no, m.name, m.nickname, m.level, m.email, m.birthday, m.photo, m.phoneNumber,"
            + " s.business_name, s.business_no, s.business_address, s.business_tel,"
            + " s.openingTime, s.closingTime, m.registeredDate, m.authority"
            + " from member m join seller s on m.member_no=s.member_no"
            + " where m.id=?")) {
      stmt.setString(1, id);

      try(ResultSet rs = stmt.executeQuery()){
        if(!rs.next()) {
          return null;
        }
        Seller seller = new Seller();
        seller.setNumber(rs.getInt("member_no"));
        seller.setName(rs.getString("name"));
        seller.setNickname(rs.getString("nickname"));
        seller.setLevel(rs.getInt("level"));
        seller.setEmail(rs.getString("email"));
        seller.setBirthday(rs.getDate("birthday"));
        seller.setPhoto(rs.getString("photo"));
        seller.setPhoneNumber(rs.getString("phoneNumber"));
        seller.setBusinessName(rs.getString("business_name"));
        seller.setBusinessNumber(rs.getString("business_no"));
        seller.setBusinessAddress(rs.getString("business_address"));
        seller.setBusinessPlaceNumber(rs.getString("business_tel"));
        seller.setBusinessOpeningTime(rs.getString("openingTime"));
        seller.setBusinessClosingTime(rs.getString("closingTime"));
        seller.setRegisteredDate(rs.getDate("registeredDate"));
        seller.setAuthority(rs.getInt("authority"));

        return seller;
      }
    }
  }

  @Override
  public void update(Seller seller) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update"
            + " member m join seller s on m.member_no = s.member_no"
            + " set m.nickname=?, m.email=?, m.password=password(?), m.photo=?, m.phoneNumber=?,"
            + " s.business_name=?, s.business_no=?, s.business_address=?, s.business_tel=?,"
            + " s.openingTime=?, s.closingTime=?  where m.member_no=?")) {

      stmt.setString(1, seller.getNickname());
      stmt.setString(2, seller.getEmail());
      stmt.setString(3, seller.getPassword());
      stmt.setString(4, seller.getPhoto());
      stmt.setString(5, seller.getPhoneNumber());
      stmt.setString(6, seller.getBusinessName());
      stmt.setString(7, seller.getBusinessNumber());
      stmt.setString(8, seller.getBusinessAddress());
      stmt.setString(9, seller.getBusinessPlaceNumber());
      stmt.setString(10, seller.getBusinessOpeningTime());
      stmt.setString(11, seller.getBusinessClosingTime());
      stmt.setInt(12, seller.getNumber());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 변경 실패!");
      }
    }

  }

  @Override
  public void delete(String id) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from member where member_no=?");
        PreparedStatement stmt2 = con.prepareStatement(
            "delete from seller where member_no=?")) {

      stmt2.setString(1, id);
      stmt2.executeUpdate();

      stmt.setString(1, id);
      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 삭제 실패!");
      }
    }

  }

}
