package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

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
    requestAgent.request("product.selectOne", params);

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


  //수량 처음 입력시 무조건 1이상이어야함.
  @Override
  public int checkNum (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      } else if (num >= 100) {
        System.out.println("입력하신 수는 100을 넘습니다.\n잘못 입력하셨다면 My Store에서 수량울 변경해 주세요.\n");
      }
      return num;       
    }
  }
  // 수량 변경시 0이상 가능.
  public int checkNum2 (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 0) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      } else if (num >= 100) {
        System.out.println("입력하신 수는 100을 넘습니다.\n잘못 입력하셨다면 My Store에서 수량울 변경해 주세요.\n");
      }
      return num;       
    }
  }

  @Override
  public int checkPrice(String label) {
    while(true) {
      int price = Prompt.inputInt(label);
      if(price < 1) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      } 
      return price;       
    }
  }
}
