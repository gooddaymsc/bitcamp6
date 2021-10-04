package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.request.RequestAgent;

public class NetProductDao implements ProductDao{

  RequestAgent requestAgent;

  public NetProductDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
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
  public Product findByProduct(String name) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productName", name);

    requestAgent.request("product.selectOne", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Product.class);
  }

  @Override
  public Review findReviewById(Product product, String id) {
    for (Review review : product.getReviewList()) {
      if (review.getId().equals(id)) {
        return review;
      }
    }
    return null;
  }

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
  ///////////////////////review///////////////////
  @Override
  public void insertReview(Review review) throws Exception {
    //    requestAgent.request("addNumber.review", null);
    //    int no = requestAgent.getObject(Integer.class);
    //    review.setNo(no);

    requestAgent.request("review.insert", review);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("리뷰 데이터 저장 실패");
    }
  }
  @Override
  public List<Review> findAll(int productNumber) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productNumber", String.valueOf(productNumber));
    requestAgent.request("review.selectList", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("리뷰 데이터 조회 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(Review.class));
  }

  @Override
  public void updateReview(Review review) throws Exception {

    requestAgent.request("review.update", review);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("리뷰 데이터 변경 실패");
    }
  }

  @Override
  public void deleteReview(Review review) throws Exception {
    requestAgent.request("review.delete", review);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("상품 데이터 삭제 실패");
    }
  }


  @Override
  public boolean reviewIs(int productNumber, String id) {
    HashMap<String, String> params = new HashMap<>();
    params.put("productNumber", String.valueOf(productNumber));
    params.put("id", id);
    requestAgent.request("product.selectOne", params);
    for (Review review : product.getReviewList()) {
      if (review.getId().equals(ClientApp.getLoginUser().getId())) {
        return true;
      }
    }
    return false;
  }
}


