package com.eomcs.pms.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public class MybatisStockDao implements StockDao {

  SqlSession sqlSession;

  public MybatisStockDao(SqlSession sqlSession) {
    this.sqlSession =  sqlSession;
  }
  @Override
  public void insert(Stock stock) throws Exception {
    sqlSession.insert("StockMapper.insert", stock);
    sqlSession.commit();
  }

  @Override
  public StockList findAll(String id) throws Exception {
    Collection<Stock> list = sqlSession.selectList("StockMapper.findAll", id);
    StockList stocklist = new StockList();
    stocklist.setId(id);
    stocklist.setSellerStock((List<Stock>) list);
    return stocklist;
    //    return null;
  }

  @Override
  public List<StockList> findAll() throws Exception {
    //    requestAgent.request("stock.selectAllList", null);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      throw new Exception("재고목록 불러오기 실패");
    //    }
    //    return new ArrayList<>(requestAgent.getObjects(StockList.class));
    return null;
  }

  @Override
  public Product checkProduct(String name) throws Exception {
    return sqlSession.selectOne("StockMapper.checkProduct", name);
  }

  @Override
  public Stock findByNameId(String name, String id) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("productName", name);
    params.put("id", id);

    return sqlSession.selectOne("StockMapper.findByNameId", params);
  }

  @Override
  public void update(Stock stock) throws Exception {
    sqlSession.update("StockMapper.update", stock);
    sqlSession.commit();
  }

  @Override
  public void delete(Stock stock) throws Exception {
    sqlSession.delete("StockMapper.delete", stock);
    sqlSession.commit();
  }
}
