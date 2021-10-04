package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartDetailHandler implements Command {
  CartDao cartDao;
  public CartDetailHandler(CartDao cartDao) {
    this.cartDao = cartDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[장바구니 상세보기]");
    String nowLoginId = ClientApp.getLoginUser().getId();
    //    String productName = Prompt.inputString("상품명 :");
    int cartNo = Prompt.inputInt("장바구니 번호 : ");
    Cart cart = cartDao.findByNo(cartNo, nowLoginId);

    System.out.printf("상품명: %s\n", cart.getStock().getProduct().getProductName());
    System.out.printf("수량: %s\n", cart.getCartStocks());
    System.out.printf("총액: %s\n", cart.getCartPrice());
    System.out.printf("등록일: %s\n", cart.getRegistrationDate());
    System.out.println();

    request.setAttribute("productName",  cart.getStock().getProduct().getProductName());

    while (true) {

      String choose = Prompt.inputString("\n변경(U), 삭제(D), 예약(1), 이전(0)>");
      System.out.println();
      switch(choose) {
        case "U" :
        case "u" :request.getRequestDispatcher("/cart/update").forward(request); return;
        case "D" :
        case "d" :request.getRequestDispatcher("/cart/delete").forward(request); return;
        case "1" :request.getRequestDispatcher("/booking/add").forward(request); return;
        case "0" : return;
        default : System.out.println("잘못입력하셨습니다.\n"); continue;
      }

    }
  }
}
