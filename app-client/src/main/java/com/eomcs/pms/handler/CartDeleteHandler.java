package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartDeleteHandler implements Command {
  CartDao cartDao;
  SqlSession sqlSession;
  public CartDeleteHandler(CartDao cartDao, SqlSession sqlSession) {
    this.cartDao = cartDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    while(true) {
      System.out.println("[장바구니 삭제]");
      String nowLoginId = ClientApp.getLoginUser().getId();
      int cartNo = (Integer) request.getAttribute("cartNo");
      Cart cart = cartDao.findByNo(cartNo, nowLoginId);

      String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        cartDao.delete(cart);
        sqlSession.commit();
        System.out.println("장바구니를 삭제하였습니다.");
        return;
      } else {
        System.out.println("장바구니 삭제를 취소하였습니다.");
        return;
      }
    }
  }

}
