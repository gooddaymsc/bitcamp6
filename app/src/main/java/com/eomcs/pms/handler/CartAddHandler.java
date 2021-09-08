package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class CartAddHandler extends AbstractCartHandler {
  StockPrompt stockPrompt;
  public CartAddHandler(StockPrompt stockPrompt, CartPrompt cartPrompt) {
    super(cartPrompt);
    this.stockPrompt = stockPrompt;

  }

  @Override
  public void execute() {
    System.out.println("\n[장바구니 등록]");
    Cart cart = new Cart();
    HashMap<String, Stock> hashStock = stockPrompt.findBySellerId(Prompt.inputString("상품명 : "));

    if (hashStock.size() == 0) {
      System.out.println("해당 상품을 갖는 판매자가 없습니다.");
      return;
    }
    String storeName = "";
    int stocks;
    //----------장바구니 추가
    while(true) {

      storeName = stockPrompt.findStoreName(hashStock.keySet(), Prompt.inputString("가게명을 선택하세요 > "));

      // 가게명이 유효하지 않을때 에러메세지 구현해야함
      if (storeName==null) {
        System.out.println("가게명을 다시 입력해주세요.");
        continue;
      }
      stocks = Prompt.inputInt("수량 : ");
      if (stocks <= hashStock.get(storeName).getStocks()) {
        cart.setCartStocks(stocks);
        break;
      } else {
        System.out.println("주문수량이 재고를 초과하였습니다.");
        return;
      }
    }

    cart.setCartPrice(hashStock.get(storeName).getPrice()*stocks);
    // 체크!!!
    System.out.println(App.getLoginUser().getId());
    System.out.println(stockPrompt.findCartListById(App.getLoginUser().getId()).size()+1);
    cart.setCartNumber(stockPrompt.findCartListById(App.getLoginUser().getId()).size()+1);
    cart.setSellerId(stockPrompt.findByPlaceName(storeName).getId());
    cart.setRegistrationDate(new Date(System.currentTimeMillis()));
    System.out.println("장바구니가 등록되었습니다.");
    CartList cartList = cartPrompt.findCartListById(App.getLoginUser().getId());
    cartList.getPrivacyCart().add(cart);
  }
}