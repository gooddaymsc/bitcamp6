package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;

public class CartListHandler extends AbstractCartHandler {
  StockPrompt stockPrompt;
  public CartListHandler(StockPrompt stockPrompt) {
    this.stockPrompt = stockPrompt;
  }
  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_PRIVACY) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }

    if (App.getLoginUser().getAuthority()==Menu.ACCESS_PRIVACY) {
      System.out.println("[장바구니 목록]");
      CartList cartList = findCartListById(App.getLoginUser().getId());

      if (cartList.getPrivacyCart().size() == 0) {
        System.out.println("아직 추가한 장바구니가 없습니다.");
        return;
      }
      for (Cart cart : cartList.getPrivacyCart()) {
        System.out.printf("%d, %s, %d, %d, %s\n", // 장바구니 번호, 상품명, 수량, 총액
            cart.getCartNumber(), 
            cart.getStock().getProduct().getProductName(), 
            cart.getCartStocks(), 
            cart.getCartPrice(),
            cart.getRegistrationDate());
      }
    }
  }

}
