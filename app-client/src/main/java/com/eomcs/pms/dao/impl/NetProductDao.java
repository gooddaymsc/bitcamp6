package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.request.RequestAgent;

public class NetProductDao implements ProductDao{

  RequestAgent requestAgent;

  public NetProductDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }

  @Override
  public void insert(Product product) throws Exception {
    requestAgent.request("product.add", product);
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
    params.put("no", String.valueOf(no));

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
}
