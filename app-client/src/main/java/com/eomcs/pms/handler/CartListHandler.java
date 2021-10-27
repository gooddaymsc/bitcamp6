package com.eomcs.pms.handler;

import java.util.Collection;
import java.util.List;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.util.Prompt;

public class CartListHandler implements Command {
  CartDao cartDao;
  SellerDao sellerDao;
  public CartListHandler(CartDao cartDao, SellerDao sellerDao) {
    this.cartDao = cartDao;
    this.sellerDao = sellerDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception{
    String nowLoginId = ClientApp.getLoginUser().getId();
    Loop : while(true) {
      System.out.println("[장바구니 목록]");
      Collection<Cart> carts = cartDao.findAll(nowLoginId);
      CartList cartList = new CartList();
      cartList.setId(nowLoginId);
      cartList.setPrivacyCart((List<Cart>) carts);

      if (cartList.getPrivacyCart().size() == 0) {
        System.out.println("장바구니에 담긴 상품이 없습니다.");
        return;
      }
      System.out.printf("%-3s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
          "번호", "가게명", "판매자", "상품명", "수량", "금액","등록일");
      System.out.println("--------------------------------------------------------------------------");
      int total = 0;
      for (Cart cart : cartList.getPrivacyCart()) {
        System.out.printf("%-6d\t%-6s\t%-6s\t%-6s\t%-6d\t%-6d\t%-6s\n", // 장바구니 번호, 가게명, 상품명, 수량, 총액, 등록일
            cart.getCartNumber(), 
            sellerDao.findById(cart.getStock().getId()).getBusinessName(),
            cart.getStock().getId(),
            cart.getStock().getProduct().getProductName(), 
            cart.getCartStocks(), 
            cart.getStock().getPrice(),
            cart.getRegistrationDate());
        total += cart.getStock().getPrice() * cart.getCartStocks();
      }
      System.out.printf("\n>>> 총 금액 : %d원\n", total);
      System.out.println();

      while(true) {
        System.out.println("1. 장바구니 상세보기 / 2. 판매자에게 문의하기 / 이전(0)");
        int choose = Prompt.inputInt("선택 > ");
        System.out.println();
        switch(choose) {
          case 1 : request.getRequestDispatcher("/cart/detail").forward(request); continue Loop;
          case 2 : request.getRequestDispatcher("/message/add").forward(request); continue Loop;
          case 0 : return;
        }
      }
    }
  }
}

