package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public class MariadbStockDao implements StockDao {

  Connection con;

  public MariadbStockDao(Connection con) {
    this.con =  con;
  }
  @Override
  public void insert(Stock stock) throws Exception {

    try (PreparedStatement stmt2 = con.prepareStatement(
        "select member_no from member where member_no=?")) {
      stmt2.setString(1, stock.getId());
      stmt2.executeQuery();

      try (PreparedStatement stmt3 = con.prepareStatement(
          "select product_no from product where product_no=?")) {
        stmt3.setString(1, stock.getProduct());
        stmt3.executeQuery();

        try (PreparedStatement stmt = con.prepareStatement(
            "insert into stock(stock_no,amount,price) + values(?,?,?)")) {
          stmt.setInt(1, stock.getStockNumber());
          stmt.setInt(2, stock.getStocks());
          stmt.setInt(3, stock.getPrice());

          if(stmt.executeUpdate() == 0 ) {
            throw new Exception("재고 데이터 입력 실패");
          }
        }    
      }
    }

  }
  @Override
  public StockList findAll(String id) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("id", id);
    //    requestAgent.request("stock.selectList", params);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      throw new Exception("재고목록 불러오기 실패");
    //    }
    //    return requestAgent.getObject(StockList.class);
    return null;
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
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " p.product_no, p.type_no, p.name, p.origin, p.volume, p.alcoholLevel, p.sugarLevel,"
            + " p.acidity, p.weight, p.rate, p.variety,"
            + " t.type, t.subType"
            + " from product p join product_type t on p.type_no=t.type_no"
            + " where p.name="+name);

        ResultSet rs = stmt.executeQuery()) { 

      if (rs.next()) {
        Product p = new Product();
        p.setProductNumber(rs.getInt("product_no"));
        p.setProductName(rs.getString("name"));
        p.setCountryOrigin(rs.getNString("origin"));
        p.setVolume(rs.getInt("volume"));
        p.setAlcoholLevel(rs.getFloat("alcoholLevel"));
        p.setSugerLevel(rs.getInt("sugarLevel"));
        p.setAcidity(rs.getInt("acidity"));
        p.setWeight(rs.getInt("weight"));
        p.setProductType(rs.getString("type"));
        p.setProductSubType(rs.getString("subType"));
        p.setVariety(rs.getString("variety"));
        return p;
      }
      return null;
    }
  }

  @Override
  public Stock findByNameId(String name, String id) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("productName", name);
    //    params.put("id", id);
    //
    //    requestAgent.request("stock.selectOne", params);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      return null;
    //    }
    //    return requestAgent.getObject(Stock.class);
    return null;
  }

  @Override
  public void delete(Stock stock) throws Exception {

    //    requestAgent.request("stock.delete", stock);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      throw new Exception("재고삭제 실패");
    //    }
  }

  @Override
  public void update(Stock stock) throws Exception {
    //    requestAgent.request("stock.update", stock);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception("재고 데이터 변경 실패");
    //    }
  }
}
