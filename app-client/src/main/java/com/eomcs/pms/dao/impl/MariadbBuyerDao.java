package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;

public class MariadbBuyerDao implements BuyerDao {

  Connection con;

  public MariadbBuyerDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member buyer) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert"
            + " into member(authority,member_no,id,name,nickname,email,birthday,password,"
            + " photo,phoneNumber,zipcode,address,detail_address,registeredDate)"
            + " values(?,?,?,?,?,?,?,password(?),?,?,?,?,?,?)")) {

      stmt.setInt(1, buyer.getAuthority());
      stmt.setInt(2, buyer.getNumber());
      stmt.setString(3, buyer.getId());
      stmt.setString(4, buyer.getName());
      stmt.setString(5, buyer.getNickname());
      stmt.setString(6, buyer.getEmail());
      stmt.setDate(7, buyer.getBirthday());
      stmt.setString(8, buyer.getPassword());
      stmt.setString(9, buyer.getPhoto());
      stmt.setString(10, buyer.getPhoneNumber());
      stmt.setString(11, ((Buyer)buyer).getZipcode());
      stmt.setString(12, ((Buyer)buyer).getAddress());
      stmt.setString(13, ((Buyer)buyer).getDetailAddress());
      stmt.setDate(14, buyer.getRegisteredDate());


      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 저장 실패!");
      }
    }
  }

  @Override
  public List<Buyer> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,id,name,nickname,level,registeredDate"
            + " from member order by member_no asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Buyer> list = new ArrayList<>();

      while (rs.next()) {
        Buyer buyer = new Buyer();
        buyer.setNumber(rs.getInt("member_no"));
        buyer.setId(rs.getString("id"));
        buyer.setName(rs.getString("name"));
        buyer.setNickname(rs.getString("nickname"));
        buyer.setLevel(rs.getInt("level"));
        buyer.setRegisteredDate(rs.getDate("registeredDate"));

        list.add(buyer);
      }

      return list;
    }
  }

  @Override
  public Buyer findById(String id) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,nickname,email,birthday,photo,phoneNumber,zipcode,address,"
            + " detail_address,registeredDate,level,authority"
            + " from member where id = ?")) {
      stmt.setString(1, id);

      try(ResultSet rs = stmt.executeQuery()){
        if(!rs.next()) {
          return null;
        }
        Buyer buyer = new Buyer();
        buyer.setNumber(rs.getInt("member_no"));
        buyer.setName(rs.getString("name"));
        buyer.setNickname(rs.getString("nickname"));
        buyer.setEmail(rs.getString("email"));
        buyer.setBirthday(rs.getDate("birthday"));
        buyer.setPhoto(rs.getString("photo"));
        buyer.setPhoneNumber(rs.getString("phoneNumber"));
        buyer.setZipcode(rs.getString("zipcode"));

        buyer.setAddress(rs.getString("address"));
        buyer.setDetailAddress(rs.getString("detail_address"));
        buyer.setRegisteredDate(rs.getDate("registeredDate"));
        buyer.setLevel(rs.getInt("level"));
        buyer.setAuthority(rs.getInt("authority"));

        return buyer;
      }
    }
  }

  @Override
  public void update(Buyer buyer) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update member set"
            + " nickname=?,email=?,password=password(?),photo=?,address=?,phoneNumber=?"
            + " where member_no=?")) {

      stmt.setString(1, buyer.getNickname());
      stmt.setString(2, buyer.getEmail());
      stmt.setString(3, buyer.getPassword());
      stmt.setString(4, buyer.getPhoto());
      stmt.setString(5, buyer.getAddress());
      stmt.setString(6, buyer.getPhoneNumber());
      stmt.setInt(7, buyer.getNumber());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void delete(String id) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from member where id=?")) {

      stmt.setString(1, id);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 삭제 실패!");
      }
    }
  }
}
