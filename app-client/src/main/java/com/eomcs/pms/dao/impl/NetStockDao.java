package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.request.RequestAgent;

public class NetStockDao implements StockDao {

  RequestAgent requestAgent;

  public NetStockDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }
  @Override
  public void insert(Stock stock) throws Exception {
    requestAgent.request("stock.insert", stock);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("재고 데이터 저장 실패");
    }
  }
  @Override
  public StockList findAll(String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);
    requestAgent.request("stock.selectList", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("재고목록 불러오기 실패");
    }
    return requestAgent.getObject(StockList.class);
  }

  @Override
  public List<StockList> findAll() throws Exception {
    requestAgent.request("stock.selectAllList", null);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("재고목록 불러오기 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(StockList.class));
  }

  @Override
  public Product checkProduct(String name) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productName", name);
    requestAgent.request("product.selectOne2", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Product.class);
  }

  @Override
  public Stock findByNameId(String name, String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("productName", name);
    params.put("id", id);

    requestAgent.request("stock.selectOne", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Stock.class);
  }

  @Override
  public void delete(Stock stock) throws Exception {

    requestAgent.request("stock.delete", stock);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("재고삭제 실패");
    }
  }

  @Override
  public void update(Stock stock) throws Exception {
    requestAgent.request("stock.update", stock);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("재고 데이터 변경 실패");
    }
  }
}
