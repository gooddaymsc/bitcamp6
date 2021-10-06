package com.eomcs.pms.handler;
import static com.eomcs.pms.handler.CartValidation.checkNum;
import static com.eomcs.pms.handler.CartValidation.findStoreName;
import java.sql.Date;
import java.util.HashMap;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class CartAddHandler implements Command {
  CartDao cartDao;
  public CartAddHandler(CartDao cartDao) {
    this.cartDao = cartDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[장바구니 등록]");
    Cart cart = new Cart();
    String name = (String) request.getAttribute("productName");
    HashMap<String, Stock> hashStock = cartDao.findBySellerId(name);

    if (hashStock.size() == 0) {
      System.out.println("해당 상품을 갖는 판매자가 없습니다.\n");
      return;
    }
    String storeName = "";
    int stocks;
    //----------장바구니 추가
    while(true) {

      storeName = findStoreName(hashStock.keySet(), Prompt.inputString("가게명을 선택하세요 > "));

      // 가게명이 유효하지 않을때 에러메세지 구현해야함
      if (storeName==null) {
        System.out.println("가게명을 다시 입력해주세요.\n");
        continue;
      }

      stocks = checkNum("수량 : ");
      if (stocks <= hashStock.get(storeName).getStocks()) {
        cart.setCartStocks(stocks);
        break;
      } else {
        System.out.println("주문수량이 재고를 초과하였습니다.\n");
        return;
      }

    }
    cart.setStock(hashStock.get(storeName));
    cart.setCartPrice(hashStock.get(storeName).getPrice()*stocks);
    // 체크!!!
    cart.setSellerId(hashStock.get(storeName).getId());
    cart.setRegistrationDate(new Date(System.currentTimeMillis()));
    cart.setId(nowLoginId);
    cartDao.insert(cart);
    System.out.println("장바구니가 등록되었습니다.\n");
    // 나가기.
  }
}