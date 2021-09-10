package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.util.Prompt;

public class CartDetailHandler extends AbstractCartHandler {

  public CartDetailHandler(List<CartList> allCartList, CartPrompt cartPrompt) {
    super(allCartList, cartPrompt);
  }

  @Override
  public void execute() {
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
