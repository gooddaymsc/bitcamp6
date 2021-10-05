package com.eomcs.pms.table;

import com.eomcs.pms.domain.Seller;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class SellerTable extends JsonDataTable<Seller> implements DataProcessor{

  public SellerTable() {
    super("seller.json", Seller.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      case "seller.insert" : insert(request, response); break;
      case "seller.selectList" : selectList(request, response); break;
      case "seller.selectOne" : selectOne(request, response); break;
      case "seller.update" : update(request, response); break;
      case "seller.delete" : delete(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Seller seller = request.getObject(Seller.class);
    list.add(seller);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    Seller seller = findByNo(id);
    if (seller != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(seller);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
    }
  }
  //
  private void update(Request request, Response response) throws Exception {
    Seller seller = request.getObject(Seller.class);
    int index = indexOf(seller.getId());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
      return;
    } 
    list.set(index, seller);
    response.setStatus(Response.SUCCESS);
  }
  //
  private void delete(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    int index = indexOf(id);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Seller findByNo(String id) {
    for (Seller m : list) {
      if (m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }
  //
  private int indexOf(String id) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
}