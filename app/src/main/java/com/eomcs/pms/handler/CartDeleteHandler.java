package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.util.Prompt;

public class CartDeleteHandler extends AbstractCartHandler {

  public CartDeleteHandler(List<CartList> allCartList, CartPrompt cartPrompt) {
    super(allCartList, cartPrompt);
  }

  @Override
  public void execute() {
    while(true) {
      System.out.println("[장바구니 삭제]");

      String ProductName = Prompt.inputString("상품명 : ");

      Cart cart = cartPrompt.findByCart(ProductName);

      if (cart == null) {
        System.out.println("장바구니에 해당 상품이 없습니다.");
        return;
      }

      String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        CartList cartList = cartPrompt.findCartListById(App.getLoginUser().getId());

        cartList.getPrivacyCart().remove(cart);
        System.out.println("장바구니를 삭제하였습니다.");
        return;
      } else {
        System.out.println("장바구니 삭제를 취소하였습니다.");
        return;
      }
    }
  }

}
