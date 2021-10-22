package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ProductType;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;

public class MybatisProductDao implements ProductDao{

  SqlSession sqlSession;
  SellerDao sellerDao;

  public MybatisProductDao(SqlSession sqlSession, SellerDao sellerDao) {
    this.sqlSession = sqlSession;
    this.sellerDao = sellerDao;
  }

  @Override
  public void insert(Product product) throws Exception {
  }

  @Override
  public Product findByNo(int no) throws Exception {
    return null;
  }

  @Override
  public List<ProductType> findAllProductType() throws Exception {
    return sqlSession.selectList("ProductMapper.findAllProductType");
  }


  @Override
  public List<Product> findAll() throws Exception {
    return sqlSession.selectList("ProductMapper.findAll");
  }



  @Override
  public Product findByProduct(String name) throws Exception {
    return null;
  }

  @Override
  public void update(Product product) throws Exception {
  }


  @Override
  public void delete(Product product) throws Exception {    
  }

  @Override
  public HashMap<String, Seller> findByAdress (String address) throws Exception {
    return null;
  }

  @Override
  public void insertReview(Review review) throws Exception {
    sqlSession.selectOne("ReviewMapper.insert",review);
    sqlSession.commit();
  }

  @Override
  public List<Review> findAll(int productNumber) throws Exception {
    return sqlSession.selectList("ReviewMapper.findAll",productNumber);
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "insert into review(product_no, member_no, purchase_no, score, comment)"
    //            + " values(?, ?, ?, ?, ?)" )){
    //
    //      stmt.setInt(1, review.getProductNo());
    //      stmt.setString(2, review.getId());
    //      stmt.setString(3, null);
    //      stmt.setFloat(4, review.getScore());
    //      stmt.setString(5, review.getComment());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("리뷰 데이터 등록 실패!");
    //      } 
    //    }
  }

  @Override
  public void updateReview(Review review) throws Exception {
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


