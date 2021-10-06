package com.eomcs.pms.handler;
import static com.eomcs.pms.handler.CartValidation.checkNum;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartUpdateHandler implements Command {
  CartDao cartDao;
  public CartUpdateHandler(CartDao cartDao) {
    this.cartDao = cartDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    while(true) {
      System.out.println("[장바구니 변경]");
      String nowLoginId = ClientApp.getLoginUser().getId();
      int cartNo = (Integer) request.getAttribute("cartNo");
      Cart cart = cartDao.findByNo(cartNo, nowLoginId);

      int cartstocks = checkNum(String.format("수량(변경 전 : %d) : ", cart.getCartStocks()));
      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        cart.setCartStocks(cartstocks);
        cart.setCartPrice(cart.getStock().getPrice()*cartstocks);
        cartDao.update(cart);
        System.out.println("장바구니를 변경하였습니다.");
        return;
      } else {
        System.out.println("장바구니 변경을 취소하였습니다.");
        return;
      }
    }
  }

}
