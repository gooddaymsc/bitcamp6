package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartDetailHandler extends AbstractCartHandler {

  public CartDetailHandler(CartPrompt cartPrompt) {
    super(cartPrompt);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_BUYER) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 상세보기]");
    Cart cart = cartPrompt.findByCart(Prompt.inputString("상품명 : "));

    if (cart == null) {
      System.out.println("장바구니에 해당 상품이 없습니다.");
      return;
    }

    System.out.printf("상품명: %s\n", cart.getStock().getProduct().getProductName());
    System.out.printf("수량: %s\n", cart.getCartStocks());
    System.out.printf("총액: %s\n", cart.getCartPrice());
    System.out.printf("등록일: %s\n", cart.getRegistrationDate());
  }
}
