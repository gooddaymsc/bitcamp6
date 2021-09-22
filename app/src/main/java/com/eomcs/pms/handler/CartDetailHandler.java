package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartDetailHandler extends AbstractCartHandler {

  public CartDetailHandler(CartPrompt cartPrompt) {
    super(cartPrompt);
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[장바구니 상세보기]");
    String productName = Prompt.inputString("상품명 :");
    Cart cart = cartPrompt.findByCart(productName);

    if (cart == null) {
      System.out.println("장바구니에 해당 상품이 없습니다.");
      return;
    }

    System.out.printf("상품명: %s\n", cart.getStock().getProduct().getProductName());
    System.out.printf("수량: %s\n", cart.getCartStocks());
    System.out.printf("총액: %s\n", cart.getCartPrice());
    System.out.printf("등록일: %s\n", cart.getRegistrationDate());
    System.out.println();

    request.setAttribute("productName",  productName);

    while (true) {

      String choose = Prompt.inputString("변경(U), 삭제(D), 예약(B), 이전(0)>");
      System.out.println();
      switch(choose) {
        case "U" :
        case "u" :request.getRequestDispatcher("/cart/update").forward(request); return;
        case "D" :
        case "d" :request.getRequestDispatcher("/cart/delete").forward(request); return;
        case "B" :
        case "b" :request.getRequestDispatcher("/booking/add").forward(request); return;
        case "0" : return;
        default : System.out.println("잘못입력하셨습니다.\n"); continue;
      }

    }
  }
}
