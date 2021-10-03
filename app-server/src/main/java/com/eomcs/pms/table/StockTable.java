package com.eomcs.pms.table;

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
      //      case "stock.insert" : insert(request, response); break;
      //      case "stock.selectList" : selectList(request, response); break;
      //      case "stock.selectOne" : selectOne(request, response); break;
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
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private StockList findById(String id) {
    for (StockList stockList : list) {
      if (stockList.getId().equals(id)) {
        return stockList;
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
