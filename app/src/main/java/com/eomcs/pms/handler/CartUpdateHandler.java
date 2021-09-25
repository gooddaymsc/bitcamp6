package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartUpdateHandler extends AbstractCartHandler {

  public CartUpdateHandler(CartPrompt cartPrompt) {
    super(cartPrompt);
  }

  @Override
  public void execute(CommandRequest request) {
    while(true) {
      System.out.println("[장바구니 변경]");

      String productName = (String)request.getAttribute("productName");
      Cart cart = cartPrompt.findByCart(productName);

      if (cart == null) {
        System.out.println("장바구니에 해당 상품이 없습니다.");  
        return;
      }

      int cartstocks = checkNum(String.format("수량(변경 전 : %d) : ", cart.getCartStocks()));
      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        cart.setCartStocks(cartstocks);
        cart.setCartPrice(cart.getStock().getPrice()*cartstocks);
        System.out.println("장바구니를 변경하였습니다.");
        return;
      } else {
        System.out.println("장바구니 변경을 취소하였습니다.");
        return;
      }
    }
  }

}
