package com.eomcs.pms.table;

import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class BuyerTable extends JsonDataTable2 implements DataProcessor{

  public BuyerTable() {
    super("buyer.json", "seller.json", Buyer.class, Seller.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      //      case "member.selectList" : selectList(request, response); break;
      //      case "member.selectOneByLogin" : selectOneByLogin(request, response); break;
      //      case "member.insert" : memberinsert(request, response); break;
      //      case "member.delete" : delete(request, response); break;
      //      case "member.checkDuplicate" : checkDuplicate(request, response); break;
      //      case "member.update" : update(request, response); break;
      //
      //      case "member.buyer.selectOne" : buyerselectOne(request, response); break;
      //      case "member.seller.selectOne" : sellerselectOne(request, response); break;
      //      case "member.buyer.insert" : buyerinsert(request, response); break;
      //      case "member.seller.insert" : sellerinsert(request, response); break;
      case "buyer.insert" : insert(request, response); break;
      case "buyer.checkDuplicate" : checkDuplicate(request, response); break;
      case "buyer.selectList" : selectList(request, response); break;
      case "buyer.selectOne" : selectOne(request, response); break;
      case "buyer.selectOneByLogin" : selectOneByLogin(request, response); break;
      case "buyer.update" : update(request, response); break;
      case "buyer.delete" : delete(request, response); break;
      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void buyerinsert(Request request, Response response) throws Exception {
    Buyer buyer = request.getObject(Buyer.class);
    memberList.add(buyer);
    response.setStatus(Response.SUCCESS);
  }

  private void sellerinsert(Request request, Response response) throws Exception {
    Seller seller = request.getObject(Seller.class);
    memberList.add(seller);
    response.setStatus(Response.SUCCESS);
  }

  private void memberinsert(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);
    memberList.add(member);
    response.setStatus(Response.SUCCESS);
  }

  private void checkDuplicate(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    if (findById(id) != null) {
      response.setStatus(Response.FAIL);
      response.setValue("중복되는 아이디입니다.");
    }
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(memberList);
  }

  private void buyerselectOne(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    Buyer buyer = findBuyerById(id);
    if (buyer != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(buyer);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
    }
  }
  private void sellerselectOne(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    Seller seller = findSellerById(id);
    if (seller != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(seller);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
    }
  }
  private void selectOneByLogin(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    String password = request.getParameter("password");

    Member member = null;
    for (Member m : memberList) {
      if (m.getId().equals(id) && m.getPassword().equals(password)) {
        member = m;
        response.setStatus(Response.SUCCESS);
        response.setValue(member);
        return;
      } else if (m.getId().equals(id) && !m.getPassword().equals(password)) {
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

  private void update(Request request, Response response) throws Exception {
    Member buyer = request.getObject(Member.class);

    int index = indexOf(buyer.getId());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
      return;
    } 
    memberList.set(index, buyer);
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

    memberList.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Buyer findBuyerById(String id) {
    for (Member m : memberList) {
      if (m.getId().equals(id)) {
        return (Buyer)m;
      }
    }
    return null;
  }

  private Member findById(String id) {
    for (Member m : memberList) {
      if (m.getId().equals(id)) {
        return m;
      }
    }
    return null;
  }

  private Seller findSellerById(String id) {
    for (Member m : memberList) {
      if (m.getId().equals(id)) {
        return (Seller)m;
      }
    }
    return null;
  }

  private int indexOf(String id) {
    for (int i = 0; i < memberList.size(); i++) {
      if (memberList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
}
