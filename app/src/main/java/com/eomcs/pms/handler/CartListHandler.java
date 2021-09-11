package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;

public class CartListHandler extends AbstractCartHandler {
  SellerPrompt sellerPrompt;
  public CartListHandler(CartPrompt cartPrompt, SellerPrompt sellerPrompt) {
    super(cartPrompt);
    this.sellerPrompt = sellerPrompt;
  }
  @Override
  public void execute() {

    System.out.println("\n[장바구니 목록]");
    CartList cartList = cartPrompt.findCartListById(App.getLoginUser().getId());

    if (cartList.getPrivacyCart().size() == 0) {
      System.out.println("아직 추가한 장바구니가 없습니다.");
      return;
    }
    System.out.printf("%-3s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
        "번호", "가게명", "상품명", "재고", "금액","등록일");
    System.out.println("--------------------------------------------------------------------------");
    int total = 0;
    for (Cart cart : cartList.getPrivacyCart()) {
      System.out.printf("%-6d\t%-6s\t%-6s\t%-6d\t%-6d\t%-6s\n", // 장바구니 번호, 가게명, 상품명, 수량, 총액, 등록일
          cart.getCartNumber(), 
          sellerPrompt.findBySellerInfo(cart.getSellerId()).getBusinessName(),
          cart.getStock().getProduct().getProductName(), 
          cart.getCartStocks(), 
          cart.getCartPrice(),
          cart.getRegistrationDate());
      total += cart.getCartPrice();
    }
    System.out.printf("\n>>> 총 금액 : %d원\n", total);
  }
}


