package com.eomcs.pms.table;

import java.util.List;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class MemberTable extends JsonDataTable<Member> implements DataProcessor{
  BuyerTable buyerTable;
  SellerTable sellerTable;

  public MemberTable(BuyerTable buyerTable, SellerTable sellerTable) {
    this.buyerTable = buyerTable;
    this.sellerTable = sellerTable;

    merge(buyerTable.list, sellerTable.list);
  }

  private void merge(List<Buyer> buyerList, List<Seller> sellerList) {
    for (Buyer buyer : buyerList) {
      list.add(buyer);
    }
    for (Seller seller : sellerList) {
      list.add(seller);
    }
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      case "member.insert" : insert(request, response); break;
      case "member.selectList" : selectList(request, response); break;
      case "member.selectOne" : selectOne(request, response); break;
      case "member.update" : Update(request, response); break;
      case "member.delete" : delete(request, response); break;
      case "member.Login" : selectOneByLogin(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);
    list.add(member);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    Member member = findByNo(id);
    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
    }
  }

  private void Update(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);
    int index = indexOf(member.getId());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 회원이 없습니다.");
      return;
    } 
    list.set(index, member);
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

  private Member findByNo(String id) {
    for (Member m : list) {
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

  private void selectOneByLogin(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    String password = request.getParameter("password");

    Member member = null;
    for (Member s : list) {
      if (s.getId().equals(id) && s.getPassword().equals(password)) {
        member = s;
        response.setStatus(Response.SUCCESS);
        response.setValue(member);
        break;
      }
    }
    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이메일과 암호를 가진 회원을 찾을 수 없습니다.");
    }
  }
}