package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;

public class MybatisSellerDao implements SellerDao {
  SqlSession sqlSession;
  public MybatisSellerDao(SqlSession sqlSession) {
    this.sqlSession =  sqlSession;
  }

  @Override
  public void insert(Seller seller) throws Exception {
    sqlSession.insert("SellerMapper.insert",seller);

    HashMap<String,Object> params = new HashMap<>();
    params.put("memberNo", seller.getMember().getNumber());
    params.put("businessName", seller.getBusinessName());
    params.put("businessNumber", seller.getBusinessNumber());
    params.put("businessAddress", seller.getBusinessAddress());
    params.put("businessPlaceNumber", seller.getBusinessPlaceNumber() );
    params.put("businessOpeningTime", seller.getBusinessOpeningTime());
    params.put("businessClosingTime", seller.getBusinessClosingTime());

    sqlSession.insert("SellerMapper.insertSeller", params);
    sqlSession.commit();
  }

  @Override
  public List<Seller> findAll() throws Exception {
    return sqlSession.selectList("SellerMapper.findAll");
  }

  @Override
  public Seller findById(String id) throws Exception {
    return null;
    //    try(PreparedStatement stmt = con.prepareStatement(
    //        "select"
    //            + " s.member_no, m.name, m.nickname, m.level, m.email, m.birthday, m.photo, m.phoneNumber,"
    //            + " s.business_name, s.business_no, s.business_address, s.business_tel,"
    //            + " s.openingTime, s.closingTime, m.registeredDate, m.authority"
    //            + " from member m join seller s on m.member_no=s.member_no"
    //            + " where m.id=?")) {
    //      stmt.setString(1, id);
    //
    //      try(ResultSet rs = stmt.executeQuery()){
    //        if(!rs.next()) {
    //          return null;
    //        }
    //        Seller seller = new Seller();
    //        seller.setNumber(rs.getInt("member_no"));
    //        seller.setName(rs.getString("name"));
    //        seller.setNickname(rs.getString("nickname"));
    //        seller.setLevel(rs.getInt("level"));
    //        seller.setEmail(rs.getString("email"));
    //        seller.setBirthday(rs.getDate("birthday"));
    //        seller.setPhoto(rs.getString("photo"));
    //        seller.setPhoneNumber(rs.getString("phoneNumber"));
    //        seller.setBusinessName(rs.getString("business_name"));
    //        seller.setBusinessNumber(rs.getString("business_no"));
    //        seller.setBusinessAddress(rs.getString("business_address"));
    //        seller.setBusinessPlaceNumber(rs.getString("business_tel"));
    //        seller.setBusinessOpeningTime(rs.getString("openingTime"));
    //        seller.setBusinessClosingTime(rs.getString("closingTime"));
    //        seller.setRegisteredDate(rs.getDate("registeredDate"));
    //        seller.setAuthority(rs.getInt("authority"));
    //
    //        return seller;
    //      }
    //    }
  }

  @Override
  public void update(Seller seller) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "update"
    //            + " member m join seller s on m.member_no = s.member_no"
    //            + " set m.nickname=?, m.email=?, m.password=password(?), m.photo=?, m.phoneNumber=?,"
    //            + " s.business_name=?, s.business_no=?, s.business_address=?, s.business_tel=?,"
    //            + " s.openingTime=?, s.closingTime=?  where m.member_no=?")) {
    //
    //      stmt.setString(1, seller.getNickname());
    //      stmt.setString(2, seller.getEmail());
    //      stmt.setString(3, seller.getPassword());
    //      stmt.setString(4, seller.getPhoto());
    //      stmt.setString(5, seller.getPhoneNumber());
    //      stmt.setString(6, seller.getBusinessName());
    //      stmt.setString(7, seller.getBusinessNumber());
    //      stmt.setString(8, seller.getBusinessAddress());
    //      stmt.setString(9, seller.getBusinessPlaceNumber());
    //      stmt.setString(10, seller.getBusinessOpeningTime());
    //      stmt.setString(11, seller.getBusinessClosingTime());
    //      stmt.setInt(12, seller.getNumber());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("회원 데이터 변경 실패!");
    //      }
    //    }

  }

  @Override
  public void delete(String id) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "delete from member where member_no=?");
    //        PreparedStatement stmt2 = con.prepareStatement(
    //            "delete from seller where member_no=?")) {
    //
    //      stmt2.setString(1, id);
    //      stmt2.executeUpdate();
    //
    //      stmt.setString(1, id);
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("회원 데이터 삭제 실패!");
    //      }
    //    }
  }
}
