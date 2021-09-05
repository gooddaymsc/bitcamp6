package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;

public class CartListHandler extends AbstractCartHandler {
  public CartListHandler(List<Cart> cartList) {
    this.cartList = cartList;
  }
  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_PRIVACY) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }

    if (App.getLoginUser().getAuthority()==Menu.ACCESS_PRIVACY) {
      System.out.println("[장바구니 목록]");
      CartList cartList = findById(App.getLoginUser().getId());

      if (cartList.getPrivacyCart().size() == 0) {
        System.out.println("아직 추가한 장바구니가 없습니다.");
        return;
      }
      int i = 1;
      for (Cart cart : cartList.getPrivacyCart()) {
        System.out.printf("%d, %s, %d, %d, %s\n", // 장바구니 번호, 상품명, 수량, 총액
            i++,
            cart.getCartNumber(), 
            cart.getStock().getProduct().getProductName(), 
            cart.getCartStocks(), 
            cart.getCartPrice(),
            cart.getRegistrationDate());
      }
    }
  }

}
