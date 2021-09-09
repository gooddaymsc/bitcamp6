package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Seller;

public class CartPrompt {
  List<CartList> allCartList;
  StockPrompt stockPrompt;

  public CartPrompt(StockPrompt stockPrompt, List<CartList> allCartList) {
    this.allCartList = allCartList;
    this.stockPrompt = stockPrompt;
  }

  public CartList findCartListById(String id) {
    for (CartList cartList : allCartList) {
      if (cartList.getId().equals(id)) {
        return cartList;
      }
    }
    return null;
  }
  protected Cart findByCart (String ProductName) {
    CartList cartList = findCartListById(App.getLoginUser().getId());
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        return cart;
      }
    }
    return null;
  }

  protected HashMap<Cart, Seller> findByCartList (String ProductName) {
    HashMap<Cart, Seller> hashStock= new HashMap<>();
    CartList cartList = findCartListById(App.getLoginUser().getId());
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        Seller sellerInfo = stockPrompt.findBySellerInfo(cart.getSellerId());
        System.out.printf(">> 가게명 : %s, 담은 갯수 : %s\n" ,
            sellerInfo.getBusinessName(),
            cart.getCartStocks());
        hashStock.put(cart, sellerInfo);
      }
    }
    return hashStock;
  }

}
