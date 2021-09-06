package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class CartAddHandler extends AbstractCartHandler {
  StockPrompt stockPrompt;
  public CartAddHandler(StockPrompt stockPrompt, CartPrompt cartPrompt) {
    super(cartPrompt);
    this.stockPrompt = stockPrompt;

    CartList testCartList = cartPrompt.findCartListById("aa");
    Cart testCart = new Cart();
    testCart.setCartNumber(testCartList.getPrivacyCart().size());
    testCart.setStock(stockPrompt.findStockListById("aaa").getSellerStock().get(0));
    testCart.setCartNumber(1);
    testCart.setCartStocks(2);
    testCart.setCartPrice(stockPrompt.findStockListById("aaa").getSellerStock().get(0).getPrice()*testCart.getCartStocks());
    testCart.setSellerId("aaa");
    testCart.setRegistrationDate(new Date(System.currentTimeMillis()));

    App.allCartList.get(0).getPrivacyCart().add(testCart);

    testCartList = cartPrompt.findCartListById("aa");
    testCart = new Cart();
    testCart.setCartNumber(testCartList.getPrivacyCart().size());
    testCart.setStock(stockPrompt.findStockListById("aaaa").getSellerStock().get(1));
    testCart.setCartNumber(2);
    testCart.setCartStocks(2);
    testCart.setSellerId("aaaa");
    testCart.setCartPrice(stockPrompt.findStockListById("aaaa").getSellerStock().get(1).getPrice()*testCart.getCartStocks());
    testCart.setRegistrationDate(new Date(System.currentTimeMillis()));

    App.allCartList.get(0).getPrivacyCart().add(testCart);

    testCartList = cartPrompt.findCartListById("a");
    testCart = new Cart();
    testCart.setCartNumber(testCartList.getPrivacyCart().size());
    testCart.setStock(stockPrompt.findStockListById("aaa").getSellerStock().get(0));
    testCart.setCartNumber(3);
    testCart.setCartStocks(1);
    testCart.setSellerId("aaa");
    testCart.setCartPrice(stockPrompt.findStockListById("aaa").getSellerStock().get(0).getPrice()*testCart.getCartStocks());
    testCart.setRegistrationDate(new Date(System.currentTimeMillis()));

    App.allCartList.get(1).getPrivacyCart().add(testCart);

  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_PRIVACY) {

      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("\n[장바구니 등록]");
    Cart cart = new Cart();
    HashMap<String, Stock> hashStock = stockPrompt.findBySellerId(Prompt.inputString("상품명 : "));

    if (hashStock.size() == 0) {
      System.out.println("해당 상품을 갖는 판매자가 없습니다.");
      return;
    }

    //----------장바구니 추가
    String storeName = Prompt.inputString("가게명을 선택하세요 > ");

    cart.setStock(hashStock.get(storeName));
    int stockNumber = Prompt.inputInt("수량 : ");
    cart.setCartStocks(stockNumber);
    cart.setCartPrice(hashStock.get(storeName).getPrice()*stockNumber);
    cart.setCartNumber(stockPrompt.findCartListById(App.getLoginUser().getId()).size()+1);
    cart.setSellerId(stockPrompt.findByPlaceName(storeName).getId());
    cart.setRegistrationDate(new Date(System.currentTimeMillis()));
    System.out.println("장바구니가 등록되었습니다.");
    CartList cartList = cartPrompt.findCartListById(App.getLoginUser().getId());
    cartList.getPrivacyCart().add(cart);
  }
}