package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Review;
import com.eomcs.request.RequestAgent;

public class NetReviewDao implements ReviewDao{

  RequestAgent requestAgent;

  public NetReviewDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Review review) throws Exception {
    //    requestAgent.request("addNumber.review", null);
    //    int no = requestAgent.getObject(Integer.class);
    //    review.setReviewNumber(no);

    requestAgent.request("review.insert", review);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("리뷰 데이터 저장 실패");
    }
  }

  @Override
  public List<Review> findAll(String product) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productName", product);
    requestAgent.request("review.selectList", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("리뷰 데이터 조회 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(Review.class));
  }

  @Override
  public void update(Review review) throws Exception {

    requestAgent.request("review.update", review);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("리뷰 데이터 변경 실패");
    }
  }

  @Override
  public void delete(Review review) throws Exception {
    requestAgent.request("review.delete", review);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("상품 데이터 삭제 실패");
    }
  }



}   
