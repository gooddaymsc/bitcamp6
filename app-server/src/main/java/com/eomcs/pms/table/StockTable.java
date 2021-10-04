package com.eomcs.pms.table;

import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class StockTable extends JsonDataTable<StockList> implements DataProcessor {

  public StockTable() {
    super("allStockList.json", StockList.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      //로딩오류가 나면 새로 생성하기.
      case "stock.List.insert" : insertList(request, response); break;
      case "stock.insert" : insert(request, response); break;
      case "stock.selectList" : selectList(request, response); break;
      case "stock.selectAllList" : selectAllList(request, response); break;
      case "stock.selectOne" : selectOne(request, response); break;
      case "stock.selectOne2" : selectOne2(request, response); break;
      //      case "stock.update" : update(request, response); break;
      //      case "stock.delete" : delete(request, response); break;
      case "stock.List.delete" : deleteList(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }
  private void insertList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");

    StockList stockList = new StockList();
    stockList.setId(id);
    list.add(stockList);
    response.setStatus(Response.SUCCESS);
  }

  private void deleteList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    int index = indexOf(id);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 회원의 재고목록을 삭제할 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void insert(Request request, Response response) throws Exception {
    Stock stock = request.getObject(Stock.class);
    StockList stockList = findById(stock.getId());
    // stock numbering
    stock.setStockNumber(stockList.getStockListNumber());
    stockList.setStockListNumber(stock.getStockNumber()+1);
    stockList.getSellerStock().add(stock);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception{
    String id = request.getParameter("id");
    StockList stockList = findById(id);
    response.setStatus(Response.SUCCESS);
    response.setValue(stockList);
  }

  private void selectAllList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }


  private void selectOne(Request request, Response response) throws Exception {
    String productName = request.getParameter("productName");
    String id = request.getParameter("id");

    Stock stock = findByNameId(productName, id);
    if (stock != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(stock);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 상품이 없습니다.");
    }
  }

  private void selectOne2(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    String productName = request.getParameter("productName");

    Stock stock = findStockById(productName, id);
    if (stock != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(stock);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 상품이 없습니다.");
    }
  }

  private StockList findById(String id) {
    for (StockList stockList : list) {
      if (stockList.getId().equals(id)) {
        return stockList;
      }
    }
    return null;
  }

  private Stock findByNameId(String name, String id) {
    StockList stockList = findById(id);
    for (Stock stock : stockList.getSellerStock()) {
      if (stock.getProduct().getProductName().equals(name)) {
        return stock;
      }
    }
    return null;
  }

  private Stock findStockById(String id, String stockName) {
    StockList stockList = findById(id);
    for (Stock stock : stockList.getSellerStock()) {
      if (stock.getProduct().getProductName().equals(stockName)) {
        return stock;
      }
    }
    return null;
  }

  private int indexOf(String id) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

}
