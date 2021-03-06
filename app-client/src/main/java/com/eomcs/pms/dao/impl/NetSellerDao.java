package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.request.RequestAgent;

public class NetSellerDao implements SellerDao {

  RequestAgent requestAgent;
  public NetSellerDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Member seller) throws Exception {
    requestAgent.request("addNumber.member", null);
    int no = requestAgent.getObject(Integer.class);
    seller.setNumber(no);

    requestAgent.request("seller.insert", seller);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 데이터 저장 실패!");
    }
    requestAgent.request("member.insert", seller);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 데이터 저장 실패!");
    }

    HashMap<String,String> params = new HashMap<>();
    params.put("id", seller.getId());
    requestAgent.request("stock.List.insert", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("재고리스트 데이터 저장 실패!");
    }
    requestAgent.request("booking.List.insert", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("예약리스트 데이터 저장 실패!");
    }

    requestAgent.request("message.List.insert", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("메세지리스트 데이터 저장 실패!");
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
    requestAgent.request("member.update", seller);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 변경 실패!");
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
    requestAgent.request("member.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 삭제 실패!");
    }
  }
}
