package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;
import com.eomcs.request.RequestAgent;

public class NetSellerDao implements SellerDao {

  RequestAgent requestAgent;
  public NetSellerDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Seller seller) throws Exception {
    requestAgent.request("seller.insert", seller);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 데이터 저장 실패!");
    }
  }

  @Override
  public List<Seller> findAll() throws Exception {
    requestAgent.request("seller.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Seller.class));
  }

  @Override
  public Seller findById(String id) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("seller.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(requestAgent.getObject(String.class));
      return null;
    }

    return requestAgent.getObject(Seller.class);
  }

  @Override
  public void update(Seller seller) throws Exception {
    requestAgent.request("seller.update", seller);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("회원 변경 실패!");
    }

  }

  @Override
  public void delete(String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("seller.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 삭제 실패!");
    }

  }

  @Override
  public Seller login(String id, String password) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);
    params.put("password", password);

    requestAgent.request("seller.Login", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("회원 로그인 실패!");
    }
    return requestAgent.getObject(Seller.class);
  }
}