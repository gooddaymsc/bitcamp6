package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartUpdateHandler extends AbstractCartHandler {

  public CartUpdateHandler(CartPrompt cartPrompt) {
    super(cartPrompt);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_BUYER) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    while(true) {
      System.out.println("[장바구니 변경]");
      Cart cart = cartPrompt.findByCart(Prompt.inputString("상품명 : "));

      if (cart == null) {
        System.out.println("장바구니에 해당 상품이 없습니다.");
        return;
      }

      int cartstocks = Prompt.inputInt(String.format("수량(변경 전 : %d) : ", cart.getCartStocks()));
      //      int cartPrice = cart.getCartPrice();
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
