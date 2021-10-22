package com.eomcs.pms.dao.impl;

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
  }

  @Override
  public StockList findAll(String id) throws Exception {
    //    return null;
    return sqlSession.selectOne("StockMapper.findAll", id);


    //    ArrayList<Stock> list = new ArrayList<>();
    //    list = sqlSession.selectOne("StockMapper.findAll", id);
    //    StockList stocklist = new StockList();
    //    stocklist.setId(id);
    //    stocklist.setSellerStock(list);
    //    return stocklist;

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
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "select"
    //            + " p.product_no, p.type_no, p.name, p.origin, p.volume, p.alcoholLevel, p.sugarLevel,"
    //            + " p.acidity, p.weight, p.rate, p.variety,"
    //            + " t.type, t.subType"
    //            + " from product p join product_type t on p.type_no=t.type_no"
    //            + " where p.name=?")) {
    //      stmt.setString(1, name);
    //      ResultSet rs = stmt.executeQuery(); 
    //
    //      if (rs.next()) {
    //        Product p = new Product();
    //        p.setProductNumber(rs.getInt("product_no"));
    //        p.setProductName(rs.getString("name"));
    //        p.setCountryOrigin(rs.getNString("origin"));
    //        p.setVolume(rs.getInt("volume"));
    //        p.setAlcoholLevel(rs.getFloat("alcoholLevel"));
    //        p.setSugerLevel(rs.getInt("sugarLevel"));
    //        p.setAcidity(rs.getInt("acidity"));
    //        p.setWeight(rs.getInt("weight"));
    //        p.setProductType(rs.getString("type"));
    //        p.setProductSubType(rs.getString("subType"));
    //        p.setVariety(rs.getString("variety"));
    //        return p;
    //      }
    //      return null;
    //    }
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
    //    sqlSession.commit();
  }

  @Override
  public void delete(Stock stock) throws Exception {
    sqlSession.delete("StockMapper.delete", stock);
    //    sqlSession.commit();
  }
}
