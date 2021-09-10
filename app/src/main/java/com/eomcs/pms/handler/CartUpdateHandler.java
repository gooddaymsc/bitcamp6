package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.util.Prompt;

public class CartUpdateHandler extends AbstractCartHandler {

  public CartUpdateHandler(List<CartList> allCartList, CartPrompt cartPrompt) {
    super(allCartList, cartPrompt);
  }

  @Override
  public void execute() {
    while(true) {
      System.out.println("[장바구니 변경]");
      Cart cart = cartPrompt.findByCart(Prompt.inputString("상품명 : "));

      if (cart == null) {
        System.out.println("장바구니에 해당 상품이 없습니다.");
        return;
      }

      int cartstocks = Prompt.inputInt(String.format("수량(변경 전 : %d) : ", cart.getCartStocks()));
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
