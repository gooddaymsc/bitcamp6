package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.request.RequestAgent;

public class NetProductDao implements ProductDao{

  RequestAgent requestAgent;
  SellerDao sellerDao;
  StockDao stockDao;

  public NetProductDao(RequestAgent requestAgent, SellerDao sellerDao,  StockDao stockDao ) {
    this.requestAgent =  requestAgent;
    this.sellerDao = sellerDao;
    this.stockDao = stockDao;
  }

  @Override
  public void insert(Product product) throws Exception {
    requestAgent.request("addNumber.product", null);
    int no = requestAgent.getObject(Integer.class);
    product.setProductNumber(no);

    requestAgent.request("product.insert", product);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("상품 데이터 저장 실패");
    }
  }

  @Override
  public List<Product> findAll() throws Exception {
    requestAgent.request("product.selectList", null);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("상품 데이터 조회 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(Product.class));
  }

  @Override
  public Product findByNo(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productNumber", String.valueOf(no));

    requestAgent.request("product.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Product.class);
  }

  @Override
  public Product findByNo2(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("reviewNumber", String.valueOf(no));

    requestAgent.request("product.review.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Product.class);
  }

  @Override
  public Product findByProduct(String name) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productName", name);

    requestAgent.request("product.selectOne2", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Product.class);
  }

  // ProductValidation 으로 migration
  //  @Override
  //  public Review findReviewById(Product product, String id) {
  //    for (Review review : product.getReviewList()) {
  //      if (review.getId().equals(id)) {
  //        return review;
  //      }
  //    }
  //    return null;
  //  }

  @Override
  public void update(Product product) throws Exception {
    requestAgent.request("product.update", product);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("상품 데이터 변경 실패");
    }
  }

  @Override
  public void delete(Product product) throws Exception {    
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("name", product.getProductName());

    requestAgent.request("product.delete", product);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("상품 데이터 삭제 실패");
    }
  }

  @Override
  public void insertReview(Review review) throws Exception {
    requestAgent.request("addNumber.review", null);
    requestAgent.request("product.review.insert", review);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("리뷰 데이터 저장 실패");
    }
  }
  @Override
  public List<Review> findAll(int productNumber) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productNumber", String.valueOf(productNumber));

    requestAgent.request("product.review.selectList", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("리뷰 데이터 조회 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(Review.class));
  }

  @Override
  public void updateReview(Review review) throws Exception {

    requestAgent.request("product.review.update", review);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("리뷰 데이터 변경 실패");
    }
  }

  @Override
  public void deleteReview(Review review) throws Exception {
    requestAgent.request("product.review.delete", review);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("상품 데이터 삭제 실패");
    }
  }

  @Override
  public HashMap<String, Seller> findByAdress (String address) throws Exception {
    HashMap<String, Seller> hashMap = new HashMap<>();
    for (Seller seller : sellerDao.findAll()) {
      String[] arr = address.split(" ");
      if((seller.getBusinessAddress().contains(arr[2])) && 
          (seller.getBusinessAddress().contains(arr[1]))) {
        hashMap.put(seller.getId(), seller);
        return hashMap;
      } 
    }
    return null;
  }

  @Override
  public Stock findStockById(String id, int productNumber) throws Exception{
    StockList stockList = stockDao.findAll(id);
    for (Stock stock : stockList.getSellerStock()) {
      if (stock.getProduct().getProductNumber() == productNumber) {
        return stock;
      }
    }
    return null;
  }

  @Override
  public boolean reviewIs(int productNumber, String id) throws Exception {
    Product product = findByNo(productNumber);

    for (Review review : product.getReviewList()) {
      if (review.getId().equals(id)) {
        return false;
      }
    }
    return true;
  }
}


