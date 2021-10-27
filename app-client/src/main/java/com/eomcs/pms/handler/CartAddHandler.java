package com.eomcs.pms.handler;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;
// 가게명이 유효하지 않을때 에러메세지 구현해야함
public class CartAddHandler implements Command {
  CartDao cartDao;
  CartHandlerHelper cartHelper;
  public CartAddHandler(CartDao cartDao, CartHandlerHelper cartHelper) {
    this.cartDao = cartDao;
    this.cartHelper = cartHelper;
  }

  static boolean search = false;    //productSearch 실행 전/후 구분

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[장바구니 등록]");

    Cart cart = new Cart();
    HashMap<String, Stock> hashStock = null;
    String storeName = null;
    Collection<Cart> carts = cartDao.findAll(nowLoginId);
    CartList cartList = new CartList();
    cartList.setId(nowLoginId);
    cartList.setPrivacyCart((List<Cart>) carts);
    int stocks;

    //search 후 등록
    if(search == true) {
      //      String store = (String) request.getAttribute("storeName");
      //      hashStock = cartDao.findBySeller((String) request.getAttribute("productName"));
      //
      //      while(true) {
      //        String input = Prompt.inputString("가게명을 선택하세요(0.취소) > ");
      //        if(input.equals("0")) { return; }
      //
      //        storeName = cartDao.findStoreName(hashStock.keySet(), input);
      //
      //        if(!store.equals(storeName) || storeName == null) {
      //          System.out.println("가게명을 다시 입력해주세요.\n");
      //          continue;
      //        } else {
      //          stocks = CartValidation.checkNum("수량 : ");
      //          if (stocks <= hashStock.get(storeName).getStocks()) {
      //            for(Cart privacyCart : cartList.getPrivacyCart()) {
      //              if(privacyCart.getStock().getProduct().getProductName().equals(request.getAttribute("productName"))
      //                  && cartDao.findBySellerInfo(privacyCart.getSellerId()).getBusinessName().equals(storeName)) {
      //                String input2 = Prompt.inputString("이미 등록된 상품입니다. 정말 등록하시겠습니까(y/N)? ");
      //                if(input2.equalsIgnoreCase("y")) {
      //                  if((privacyCart.getCartStocks()+stocks)<= hashStock.get(storeName).getStocks()) {
      //                    privacyCart.setCartStocks(privacyCart.getCartStocks() + stocks);
      //                    privacyCart.setCartPrice(privacyCart.getCartPrice()+(privacyCart.getStock().getPrice()*stocks));
      //                    cartDao.update(privacyCart); 
      //                    return;
      //                  } else {
      //                    System.out.println("주문수량이 재고를 초과하였습니다.\n");
      //                    return;
      //                  }
      //                } else if (input2.equalsIgnoreCase("N")) {
      //                  System.out.println("장바구니 등록을 취소하였습니다.");
      //                  return;
      //                } 
      //                System.out.println("해당 문자는 유효하지 않습니다. ");
      //                return;
      //              }
      //            }
      //            cart.setCartStocks(stocks);
      //            cart.setStock(hashStock.get(storeName));
      //            cart.setCartPrice(hashStock.get(storeName).getPrice()*stocks);
      //            // 체크!!!
      //            cart.setSellerId(hashStock.get(storeName).getId());
      //            cart.setRegistrationDate(new Date(System.currentTimeMillis()));
      //            cart.setId(nowLoginId);
      //            cartDao.insert(cart);
      //            System.out.println("장바구니가 등록되었습니다.\n");
      //            break;
      //          } else {
      //            System.out.println("주문수량이 재고를 초과하였습니다.\n");
      //            return;
      //          }
      //        }
      //      }
    }

    //search 전 등록
    else { 
      hashStock = cartHelper.findBySellerId((Integer) request.getAttribute("productNumber"));

      while(true) {
        String input = Prompt.inputString("가게명을 선택하세요(0.취소) > ");
        if(input.equals("0")) { return; }

        storeName = cartHelper.findStoreName(hashStock.keySet(), input);

        if (storeName==null) {
          System.out.println("가게명을 다시 입력해주세요.\n");
          continue;
        }

        stocks = CartValidation.checkNum("수량 : ");
        if (stocks <= hashStock.get(storeName).getStocks()) {

          for(Cart privacyCart : cartList.getPrivacyCart()) {
            if(privacyCart.getStock().getProduct().getProductName().equals(request.getAttribute("productName"))
                && privacyCart.getStock().getSeller().getBusinessName().equals(storeName)) {
              String input2 = Prompt.inputString("이미 등록된 상품입니다. 정말 등록하시겠습니까(y/N)? ");
              if(input2.equalsIgnoreCase("y")) {
                if((privacyCart.getCartStocks()+stocks)<= hashStock.get(storeName).getStocks()) {
                  privacyCart.setCartStocks(privacyCart.getCartStocks() + stocks);
                  privacyCart.setCartPrice(privacyCart.getCartPrice()+(privacyCart.getStock().getPrice()*stocks));
                  cartDao.update(privacyCart); 
                  System.out.println("장바구니가 등록되었습니다.\n");         
                  return;
                } else {
                  System.out.println("주문수량이 재고를 초과하였습니다.\n");
                  return;
                }
              } else {
                System.out.println("장바구니 등록을 취소하였습니다.");
                return;
              } 
            }
          }
          cart.setCartStocks(stocks);
          cart.setStock(hashStock.get(storeName));
          cart.setCartPrice(hashStock.get(storeName).getPrice()*stocks);
          // 체크!!!
          cart.setId(nowLoginId);
          cartDao.insert(cart);
          System.out.println("장바구니가 등록되었습니다.\n");         
          break;
        } else {
          System.out.println("주문수량이 재고를 초과하였습니다.\n");
          return;
        }
      }
    }
  }
}