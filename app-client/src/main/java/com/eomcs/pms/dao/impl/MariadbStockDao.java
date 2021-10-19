package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
        "select member_no from member where id=?")) {
      stmt2.setString(1, stock.getId());
      ResultSet rs = stmt2.executeQuery();

      int member_no = 0;
      while(rs.next()) {
        member_no = rs.getInt("member_no");
      }

      try (PreparedStatement stmt = con.prepareStatement(
          "insert into stock(stock_no,member_no,product_no,amount,price) values(?,?,?,?,?)")) {
        stmt.setInt(1, stock.getStockNumber());
        stmt.setInt(2, member_no);
        stmt.setInt(3, stock.getProduct().getProductNumber());
        stmt.setInt(4, stock.getStocks());
        stmt.setInt(5, stock.getPrice());

        if(stmt.executeUpdate() == 0 ) {
          throw new Exception("재고 데이터 입력 실패");
        }
      }    
    }
  }

  @Override
  public StockList findAll(String id) throws Exception {

    try (PreparedStatement stmt2 = con.prepareStatement(
        "select member_no from member where id=?")) {
      stmt2.setString(1, id);
      ResultSet rs2 = stmt2.executeQuery();

      int member_no = 0;
      while(rs2.next()) {
        member_no = rs2.getInt("member_no");
      }

      try(PreparedStatement stmt = con.prepareStatement(
          "Select s.stock_no, p.name, s.price, s.amount"
              + " From stock s left outer join product p"
              + " on s.product_no = p.product_no where member_no=" + member_no
              + " order by s.product_no asc");
          ResultSet rs = stmt.executeQuery()) {

        ArrayList<Stock> list = new ArrayList<>();

        while(rs.next()) {
          Stock s = new Stock();
          s.setStockNumber(rs.getInt("stock_no"));
          s.getProduct().setProductName(rs.getString("name"));
          s.setPrice(rs.getInt("price"));
          s.setStocks(rs.getInt("amount"));


          list.add(s);
        }
        return list; // 질문.....
      }
    }
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
            + " where p.name=?")) {
      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery(); 

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
  public void update(Stock stock) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update stock set amount=?,price=? where stock_no=?")) {

      stmt.setInt(1, stock.getStocks());
      stmt.setInt(2, stock.getPrice());
      stmt.setInt(3, stock.getStockNumber());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("재고 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void delete(Stock stock) throws Exception {

    try (PreparedStatement stmt = con.prepareStatement(
        "delete from stock where stock_no=" + stock.getStockNumber())) {

      if (stmt.executeUpdate() == 0) {
        throw new Exception("재고 데이터 삭제 실패!");
      }
    }
  }
}
