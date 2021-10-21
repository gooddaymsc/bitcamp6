package com.eomcs.pms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;

public class MybatisBuyerDao implements BuyerDao {

  SqlSession sqlSession;
  public MybatisBuyerDao(SqlSession sqlSession) {
    this.sqlSession =  sqlSession;
  }

  @Override
  public void insert(Buyer buyer) throws Exception {
    sqlSession.insert("BuyerMapper.insert",buyer);
    sqlSession.commit();
  }

  @Override
  public List<Buyer> findAll() throws Exception {
    return sqlSession.selectList("BuyerMapper.findAll");
  }

  @Override
  public Buyer findById(String id) throws Exception {
    return sqlSession.selectOne("BuyerMapper.findById",id);
  }

  @Override
  public void update(Buyer buyer) throws Exception {
    sqlSession.update("BuyerMapper.update",buyer);
    sqlSession.commit();
  }

  @Override
  public void delete(String id) throws Exception {
    sqlSession.delete("BuyerMapper.delete",id);
    sqlSession.commit();
  }
}
