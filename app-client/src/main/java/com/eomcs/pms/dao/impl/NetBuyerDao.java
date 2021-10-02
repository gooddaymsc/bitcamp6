package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;

public class NetBuyerDao implements BuyerDao {

  RequestAgent requestAgent;

  public NetBuyerDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Member buyer) throws Exception {
    requestAgent.request("addNumber.member", null);
    int no = requestAgent.getObject(Integer.class);
    buyer.setNumber(no);

    requestAgent.request("buyer.insert", buyer);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 데이터 저장 실패!");
    }

    requestAgent.request("member.insert", buyer);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 데이터 저장 실패!");
    }
  }

  @Override
  public List<Buyer> findAll() throws Exception {
    requestAgent.request("buyer.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Buyer.class));
  }

  @Override
  public Buyer findById(String id) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("buyer.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Buyer.class);
  }

  @Override
  public void update(Buyer buyer) throws Exception {
    requestAgent.request("buyer.update", buyer);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 변경 실패!");
    }

    requestAgent.request("member.update", buyer);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 변경 실패!");
    }
  }

  @Override
  public void delete(String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);

    requestAgent.request("buyer.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 삭제 실패!");
    }

    requestAgent.request("member.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("회원 삭제 실패!");
    }

  }
}
