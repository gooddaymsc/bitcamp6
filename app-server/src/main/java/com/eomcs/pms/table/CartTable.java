package com.eomcs.pms.table;

import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class CartTable extends JsonDataTable<CartList> implements DataProcessor {

  public CartTable() {
    super("allCartList.json", CartList.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      //로딩오류가 나면 새로 생성하기.
      case "cart.List.insert" : insertList(request, response); break;
      case "cart.insert" : insert(request, response); break;
      case "cart.selectList" : selectList(request, response); break;
      case "cart.selectAllList" : selectAllList(request, response); break;
      case "cart.selectOne" : selectOne(request, response); break;
      case "cart.update" : update(request, response); break;
      case "cart.delete" : delete(request, response); break;
      case "cart.List.delete" : deleteList(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }
  private void insertList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");

    CartList cartList = new CartList();
    cartList.setId(id);
    list.add(cartList);
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

  private void insert(Request request, Response response) throws Exception {
    Cart cart = request.getObject(Cart.class);
    CartList cartList = findById(cart.getId());
    // stock numbering
    cart.setCartNumber(cartList.getCartListNumber());
    cartList.setCartListNumber(cart.getCartNumber()+1);
    cartList.getPrivacyCart().add(cart);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception{
    String id = request.getParameter("id");
    CartList cartList = findById(id);
    response.setStatus(Response.SUCCESS);
    response.setValue(cartList);
  }

  private void selectAllList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception{
    int cartNo = Integer.parseInt(request.getParameter("cartNo"));
    String id = request.getParameter("id");
    Cart cart = findById(id, cartNo);

    if (cart != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(cart);

    } else {
      response.setStatus(Response.FAIL);
      response.setValue("장바구니에 해당상품이 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception{
    Cart cart = request.getObject(Cart.class);
    CartList cartList = findById(cart.getId());
    int index = indexOf(cart.getId(), cart.getCartNumber());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글이 없습니다.");
      return;
    } 
    cartList.getPrivacyCart().set(index, cart);
    response.setStatus(Response.SUCCESS);

  }

  private void delete(Request request, Response response) throws Exception{
    Cart cart = request.getObject(Cart.class);
    CartList cartList = findById(cart.getId());
    int index = indexOf(cart.getId(), cart.getCartNumber());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 장바구니를 찾을 수 없습니다.");
      return;
    }

    cartList.getPrivacyCart().remove(index);
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }


  private CartList findById(String id) {
    for (CartList cartList : list) {
      if (cartList.getId().equals(id)) {
        return cartList;
      }
    }
    return null;
  }

  private Cart findById(String id, int cartNo) {
    CartList cartList = findById(id);
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getCartNumber() == cartNo) {
        return cart;
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

  private int indexOf(String id, int cartNo) {
    CartList cartList = findById(id);

    for (int i = 0; i < cartList.getPrivacyCart().size(); i++) {
      if (cartList.getPrivacyCart().get(i).getCartNumber() == cartNo) {
        return i;
      }
    }
    return -1;
  }
}
