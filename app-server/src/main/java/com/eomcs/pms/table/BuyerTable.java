package com.eomcs.pms.table;

import com.eomcs.pms.domain.Buyer;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class BuyerTable extends JsonDataTable<Buyer> implements DataProcessor{

  public BuyerTable() {
    super("buyer.json", Buyer.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      case "buyer.insert" : insert(request, response); break;
      case "buyer.selectList" : selectList(request, response); break;
      case "buyer.selectOne" : selectOne(request, response); break;
      case "buyer.update" : update(request, response); break;
      case "buyer.delete" : delete(request, response); break;
      case "buyer.Login" : selectOneByLogin(request, response); break;
      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Buyer buyer = request.getObject(Buyer.class);
    list.add(buyer);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    Buyer buyer = findByNo(id);
    if (buyer != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(buyer);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Buyer buyer = request.getObject(Buyer.class);

    int index = indexOf(buyer.getId());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
      return;
    } 
    list.set(index, buyer);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    Buyer buyer = request.getObject(Buyer.class);
    int index = indexOf(buyer.getId());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Buyer findByNo(String id) {
    for (Buyer m : list) {
      if (m.getId().equals(id)) {
        return m;
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

  private void selectOneByLogin(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    String password = request.getParameter("password");

    Buyer buyer = null;
    for (Buyer b : list) {
      if (b.getId().equals(id) && b.getPassword().equals(password)) {
        buyer = b;
        response.setStatus(Response.SUCCESS);
        response.setValue(buyer);
        return;
      } else if (b.getId().equals(id) && !b.getPassword().equals(password)) {
        response.setStatus(Response.FAIL);
        response.setValue("암호가 틀립니다.");
        return;
      } else {
        response.setStatus(Response.FAIL);
        response.setValue("가입되어 있지 않은 아이디입니다.");
        return;
      }
    } 
  }
}


