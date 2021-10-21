package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;

public class MariadbProductDao implements ProductDao{

  Connection con;
  SellerDao sellerDao;
  StockDao stockDao;
  BookingDao bookingDao;

  public MariadbProductDao(Connection con, SellerDao sellerDao,  StockDao stockDao, BookingDao bookingDao) {
    this.con = con;
    this.sellerDao = sellerDao;
    this.stockDao = stockDao;
    this.bookingDao = bookingDao;
  }

  @Override
  public void insert(Product product) throws Exception {
    try (PreparedStatement stmt2 = con.prepareStatement(
        "select type_no"
            + " from product_type"
            + " where type=? and subType=?")) {
      stmt2.setString(1, product.getProductType());
      stmt2.setString(2, product.getProductSubType());
      ResultSet rs = stmt2.executeQuery();

      int type_no = 0;
      while(rs.next()) {
        type_no = rs.getInt("type_no");
      }

      try(PreparedStatement stmt = con.prepareStatement(
          "insert into product(type_no, name, variety, origin, volume, alcoholLevel, sugarLevel, acidity, weight)"
              + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)" )) {
        stmt.setInt(1, type_no);
        stmt.setString(2, product.getProductName());
        stmt.setString(3, product.getVariety());
        stmt.setString(4, product.getCountryOrigin());
        stmt.setInt(5, product.getVolume());
        stmt.setFloat(6, product.getAlcoholLevel());
        stmt.setInt(7, product.getSugerLevel());
        stmt.setInt(8, product.getAcidity());
        stmt.setInt(9, product.getWeight()); 

        if(stmt.executeUpdate() == 0 ) {
          throw new Exception("상품 데이터 입력 실패");
        }
      }
    }
  }

  @Override
  public List<Product> findAll() throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "Select"
            + " p.product_no, t.type, t.subType,"
            + " p.name, p.origin, p.volume, p.alcoholLevel, p.sugarLevel, p.acidity, p.weight, p.rate, p.variety"
            + " From product p"
            + " left outer join product_type t on t.type_no = p.type_no"
            + " order by p.product_no asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Product> list = new ArrayList<>();

      while(rs.next()) {
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

        list.add(p);
      }
      return list;
    }
  }


  @Override
  public Product findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " p.product_no, p.type_no, p.name, p.origin, p.volume, p.alcoholLevel, p.sugarLevel, p.acidity, p.weight, p.rate, p.variety,"
            + " t.type, t.subType"
            + " from product p join product_type t on p.type_no=t.type_no"
            + " where p.product_no="+no);
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
    }
    return null;
  }

  @Override
  public Product findByProduct(String name) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productName", name);

    try(PreparedStatement stmt = con.prepareStatement(
        "select"
            + " p.product_no, p.type_no, p.name, p.origin, p.volume, p.alcoholLevel, p.sugarLevel, p.acidity, p.weight, p.rate, p.variety,"
            + " t.type, t.subType"
            + " from product p join product_type t on p.type_no=t.type_no"
            + " where p.name = " + name);
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
    }
    return null;
  }

  //  @Override
  //  public Product findByNo2(int no) throws Exception {
  //    //    HashMap<String, String> params = new HashMap<>();
  //    //    params.put("reviewNumber", String.valueOf(no));
  //    //
  //    //    requestAgent.request("product.review.selectOne", params);
  //    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
  //    //      return null;
  //    //    }
  //    //    return requestAgent.getObject(Product.class);
  //    return null;
  //  }


  @Override
  public void update(Product product) throws Exception {
    try (PreparedStatement stmt2 = con.prepareStatement(
        "select type_no"
            + " from product_type"
            + " where type=? and subType=?")) {
      stmt2.setString(1, product.getProductType());
      stmt2.setString(2, product.getProductSubType());
      ResultSet rs = stmt2.executeQuery();

      int type_no = 0;
      while(rs.next()) {
        type_no = rs.getInt("type_no");
      }

      try(PreparedStatement stmt = con.prepareStatement(
          "update product set"
              + " type_no = ?, name = ?, origin=?, volume=?, alcoholLevel=?, sugarLevel=?, acidity=?, weight=?, variety=?"
              + " where product_no=?")) {

        stmt.setInt(1, type_no);
        stmt.setString(2, product.getProductName());
        stmt.setString(3, product.getCountryOrigin());
        stmt.setInt(4, product.getVolume());
        stmt.setFloat(5, product.getAlcoholLevel());
        stmt.setInt(6, product.getSugerLevel());
        stmt.setInt(7, product.getAcidity());
        stmt.setInt(8, product.getWeight());
        stmt.setString(9, product.getVariety());
        stmt.setInt(10, product.getProductNumber());

        if (stmt.executeUpdate() == 0) {
          throw new Exception("프로젝트 데이터 변경 실패!");
        }
      }
    }             
  }


  @Override
  public void delete(Product product) throws Exception {    
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from product where product_no="+product.getProductNumber())) {

      if (stmt.executeUpdate() == 0) {
        throw new Exception("상품 데이터 삭제 실패!");
      }
    }
  }

  @Override
  public HashMap<String, Seller> findByAdress (String address) throws Exception {
    return null;
    //    HashMap<String, Seller> hashMap = new HashMap<>();
    //    for (Seller seller : sellerDao.findAll()) {
    //      String[] arr = address.split(" ");
    //      if((seller.getBusinessAddress().contains(arr[2])) && 
    //          (seller.getBusinessAddress().contains(arr[1]))) {
    //        hashMap.put(seller.getId(), seller);
    //        return hashMap;
    //      } 
    //    }
    //    return null;
  }

  @Override
  public void insertReview(Review review) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into review(product_no, member_no, purchase_no, score, comment)"
            + " values(?, ?, ?, ?, ?)" )){

      stmt.setInt(1, review.getProductNo());
      stmt.setString(2, review.getId());
      stmt.setString(3, null);
      stmt.setFloat(4, review.getScore());
      stmt.setString(5, review.getComment());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("리뷰 데이터 등록 실패!");
      } 
    }
  }
  @Override
  public List<Review> findAll(int productNumber) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " r.review_no, pro.product_no, m.id, r.score, r.comment, r.registeredDate"
            + " from review r"
            + " join product pro on r.product_no = pro.product_no"
            + " join member m on r.member_no = m.member_no"
            + " where pro.product_no =" + productNumber)) {

      try (ResultSet rs = stmt.executeQuery()) {
        ArrayList<Review> list = new ArrayList<>();
        while(rs.next()) {
          Review review = new Review();
          review.setNo(rs.getInt("review_no"));
          review.setProductNo(productNumber);
          review.setId(rs.getString("id"));
          review.setScore(rs.getFloat("score"));
          review.setComment(rs.getString("comment"));
          review.setRegisteredDate(rs.getDate("registeredDate"));

          Product product = new Product();
          product.setProductNumber(rs.getInt("product_no"));
          list.add(review);
        }
        return list;
      }
    }
  }

  @Override
  public void updateReview(Review review) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update review set"
            + " score=?, comment=?"
            + " where review_no=?")){

      stmt.setFloat(1, review.getScore());
      stmt.setString(2, review.getComment());
      stmt.setInt(3, review.getNo());

      if(stmt.executeUpdate() == 0) {
        throw new Exception("리뷰 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void deleteReview(Review review) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "delete from review where review_id ="+ ClientApp.getLoginUser().getId())){
    //
    //      if(stmt.executeUpdate() == 0) {
    //        throw new Exception("리뷰 데이터 삭제 실패!");
    //      }
    //    }
  }

  @Override
  public Stock findStockById(String id, int productNumber) throws Exception{
    //    StockList stockList = stockDao.findAll(id);
    //    for (Stock stock : stockList.getSellerStock()) {
    //      if (stock.getProduct().getProductNumber() == productNumber) {
    //        return stock;
    //      }
    //    }
    return null;
  }

  @Override
  public boolean reviewIs(int productNumber, String id) throws Exception {
    //    Product product = findByNo(productNumber);
    //
    //    for (Review review : product.getReviewList()) {
    //      if (review.getId().equals(id)) {
    //        return false;
    //      }
    //    }
    return true;
  }

  @Override
  public boolean findPurchased(String productName) throws Exception {
    //    BookingList bookingList = bookingDao.findAll(ClientApp.getLoginUser().getId());
    //    for(Booking booking : bookingList.getBooking()) {
    //      if(booking.getCart().getStock().getProduct().getProductName().equals(productName) &&
    //          booking.isConfirm() == true) { 
    //        return true;
    //      }    
    //    }
    return false;
  }
}


