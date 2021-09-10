package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Seller;

public class CartPrompt {
  List<CartList> allCartList;
  SellerPrompt sellerPrompt;
  public CartPrompt(List<CartList> allCartList, SellerPrompt sellerPrompt) {
    this.sellerPrompt = sellerPrompt;
    this.allCartList = allCartList;
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

  public int findCartListIndexById(String id) {
    for (CartList cartList : allCartList) {
      if (cartList.getId().equals(id)) {
        return cartList.getPrivacyCart().size()+1;
      }
    }
    return -1;
  }

  protected HashMap<Cart, Seller> findByCartList (String ProductName) {
    HashMap<Cart, Seller> hashStock= new HashMap<>();
    CartList cartList = findCartListById(App.getLoginUser().getId());
    for (Cart cart : cartList.getPrivacyCart()) {
      if (cart.getStock().getProduct().getProductName().equals(ProductName)) {
        Seller sellerInfo = sellerPrompt.findBySellerInfo(cart.getSellerId());
        System.out.printf(">> 가게명 : %s, 담은 갯수 : %s\n" ,
            sellerInfo.getBusinessName(),
            cart.getCartStocks());
        hashStock.put(cart, sellerInfo);
      }
    }
    return hashStock;
  }

  public void addCartListById(String id) {
    CartList CartList = new CartList();
    CartList.setId(id);
    allCartList.add(CartList);
  }

  protected int getCartIndexById(String id) {
    for (int i=0; i< allCartList.size(); i++) {
      if (allCartList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  public void removeCartListById(String nowLoginid) {
    allCartList.remove(getCartIndexById(nowLoginid));
  }
}
