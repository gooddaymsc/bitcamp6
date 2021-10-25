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
    sqlSession.insert("SellerMapper.insert",seller.getMember());

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
    return sqlSession.selectOne("SellerMapper.findById",id);
  }

  @Override
  public void update(Seller seller) throws Exception {
    sqlSession.update("SellerMapper.update",seller.getMember());
    sqlSession.update("SellerMapper.updateSeller",seller);
    sqlSession.commit();
  }

  @Override
  public void delete(Seller seller) throws Exception {
    sqlSession.delete("SellerMapper.deleteSeller",seller.getMember().getNumber());
    sqlSession.delete("SellerMapper.delete",seller.getMember().getNumber());
    sqlSession.commit();

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
