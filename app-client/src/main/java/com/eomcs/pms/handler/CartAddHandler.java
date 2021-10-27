package com.eomcs.pms.handler;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;
// 가게명이 유효하지 않을때 에러메세지 구현해야함
public class CartAddHandler implements Command {
  CartDao cartDao;
  CartHandlerHelper cartHelper;
  SqlSession sqlSession;
  public CartAddHandler(CartDao cartDao, CartHandlerHelper cartHelper, SqlSession sqlSession) {
    this.cartDao = cartDao;
    this.cartHelper = cartHelper;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[장바구니 등록]");

    // search에서 장바구니로 넘어가는 과정 확인.
    HashMap<String, Stock> hashStock = null;

    Collection<Cart> carts = cartDao.findAll(nowLoginId);

    CartList cartList = new CartList();
    cartList.setId(nowLoginId);
    cartList.setPrivacyCart((List<Cart>) carts);

    hashStock = cartHelper.findBySellerId((Integer) request.getAttribute("productNumber"));

    while(true) {
      String input = Prompt.inputString("가게명을 선택하세요(0.취소) > ");
      if(input.equals("0")) { return; }

      String storeName = cartHelper.findStoreName(hashStock.keySet(), input);

      if (storeName==null) {
        System.out.println("가게명을 다시 입력해주세요.\n");
        continue;
      }

      int stocks = CartValidation.checkNum("수량 : ");
      if (stocks <= hashStock.get(storeName).getStocks()) {

        for(Cart privacyCart : cartList.getPrivacyCart()) {
          if(privacyCart.getStock().getProduct().getProductName().equals(request.getAttribute("productName"))
              && privacyCart.getStock().getSeller().getBusinessName().equals(storeName)) {
            String input2 = Prompt.inputString("이미 등록된 상품입니다. 정말 등록하시겠습니까(y/N)? ");
            if(input2.equalsIgnoreCase("y")) {
              if((privacyCart.getCartStocks()+stocks)<= hashStock.get(storeName).getStocks()) {
                privacyCart.setCartStocks(privacyCart.getCartStocks() + stocks);
                privacyCart.setCartPrice(privacyCart.getCartPrice()+(privacyCart.getStock().getPrice()*stocks));
                cartDao.update(privacyCart); 
                sqlSession.commit();
                System.out.println("장바구니가 등록되었습니다.\n");         
                return;
              } else {
                System.out.println("주문수량이 재고를 초과하였습니다.\n");
                return;
              }
            } else {
              System.out.println("장바구니 등록을 취소하였습니다.");
              return;
            } 
          }
        }

        Cart cart = new Cart();
        cart.setCartStocks(stocks);
        cart.setStock(hashStock.get(storeName));
        cart.setCartPrice(hashStock.get(storeName).getPrice()*stocks);
        // 체크!!!
        cart.setId(nowLoginId);
        cartDao.insert(cart);
        sqlSession.commit();
        System.out.println("장바구니가 등록되었습니다.\n");         
        break;
      } else {
        System.out.println("주문수량이 재고를 초과하였습니다.\n");
        return;
      }
    }
  }
}