package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartUpdateHandler extends AbstractCartHandler {

  public CartUpdateHandler(List<Cart> cartList) {
    super(cartList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_PRIVACY) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 변경]");
    Cart cart = findByCart(Prompt.inputString("상품명 : "));

    if (cart == null) {
      System.out.println("장바구니에 해당 상품이 없습니다.");
      return;
    }

    int cartstocks = Prompt.inputInt(String.format("수량(변경 전 : %d) : ", cart.getCartStocks()));
    int cartPrice = cart.getCartPrice();
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      cart.setCartStocks(cartstocks);
      cart.setCartPrice(cartPrice);
      System.out.println("장바구니를 변경하였습니다.");
      return;
    } else {
      System.out.println("장바구니 변경을 취소하였습니다.");
      return;
    }
  }

}
