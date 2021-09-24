package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;

public class CartListHandler extends AbstractCartHandler {
  List<CartList> allCartList;
  MemberPrompt memberPrompt;
  public CartListHandler(List<CartList> allCartList, CartPrompt cartPrompt, MemberPrompt memberPrompt) {
    super(cartPrompt);
    this.allCartList = allCartList;
    this.memberPrompt = memberPrompt;
  }
  @Override
  public void execute(CommandRequest request) throws Exception{
    String nowLoginId = App.getLoginUser().getId();
    System.out.println("[장바구니 목록]\n");
    CartList cartList = allCartList.get(cartPrompt.getCartIndexById(nowLoginId));

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
          memberPrompt.findBySellerInfo(cart.getSellerId()).getBusinessName(),
          cart.getStock().getProduct().getProductName(), 
          cart.getCartStocks(), 
          cart.getCartPrice(),
          cart.getRegistrationDate());
      total += cart.getCartPrice();
    }
    System.out.printf("\n>>> 총 금액 : %d원\n", total);
    System.out.println();
    request.getRequestDispatcher("/cart/detail").forward(request);
  }
}


