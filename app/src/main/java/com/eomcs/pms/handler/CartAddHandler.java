package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class CartAddHandler extends AbstractCartHandler {
  int cartNumber = 1;
  AbstractStockHandler abstractStockHandler;

  public CartAddHandler(List<Cart> cartList, AbstractStockHandler abstractStockHandler) {
    super(cartList);
    this.abstractStockHandler = abstractStockHandler;


    Cart testCart = new Cart();
    testCart.setStock(abstractStockHandler.stockList.get(0));
    testCart.setCartNumber(1111);
    testCart.setCartPrice(1111);
    testCart.setCartStocks(1111);
    testCart.setRegistrationDate(new Date(System.currentTimeMillis()));

    cartList.add(testCart);

    testCart = new Cart();
    testCart.setStock(abstractStockHandler.stockList.get(1));
    testCart.setCartNumber(2222);
    testCart.setCartPrice(2222);
    testCart.setCartStocks(2222);
    testCart.setRegistrationDate(new Date(System.currentTimeMillis()));
    cartList.add(testCart);

  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 등록]");

    Cart cart = new Cart();


    cart.setCartNumber(cartNumber++);

    Stock cartProduct = abstractStockHandler.findByStock(Prompt.inputString("상품명을 입력해주세요: "));
    if (cartProduct == null) {
      System.out.println("해당 상품이 없습니다.");
      return;
    }
    cart.setStock(cartProduct);
    cart.setCartStocks(Prompt.inputInt("수량 : "));
    cart.setRegistrationDate(new Date(System.currentTimeMillis()));

    cartList.add(cart);
    System.out.println("장바구니가 등록되었습니다.");
  }
}
