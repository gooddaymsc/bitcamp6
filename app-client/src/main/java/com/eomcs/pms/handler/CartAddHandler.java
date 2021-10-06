package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;
// 가게명이 유효하지 않을때 에러메세지 구현해야함
public class CartAddHandler implements Command {
  CartDao cartDao;
  public CartAddHandler(CartDao cartDao) {
    this.cartDao = cartDao;
  }

  static boolean search = false;    //productSearch 실행 전/후 구분

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[장바구니 등록]");
    //List<Seller> serachList;
    Cart cart = new Cart();
    String name = (String) request.getAttribute("productName");

    HashMap<String, Stock> hashStock = null;

    String storeName = null;
    int stocks;

    //search 후 등록
    if(search == true) {

      String store = (String) request.getAttribute("storeName");
      hashStock = cartDao.findBySeller((String) request.getAttribute("productName"));
      //----------장바구니 추가
      while(true) {
        String input = Prompt.inputString("가게명을 선택하세요(0.취소) > ");
        if(input.equals("0")) { return; }

        storeName = cartDao.findStoreName(hashStock.keySet(), input);

        if(!store.equals(storeName) || storeName == null) {
          System.out.println("가게명을 다시 입력해주세요.\n");
          continue;
        } else {
          stocks = cartDao.checkNum("수량 : ");
          if (stocks <= hashStock.get(storeName).getStocks()) {
            cart.setCartStocks(stocks);
            cart.setStock(hashStock.get(storeName));
            cart.setCartPrice(hashStock.get(storeName).getPrice()*stocks);
            // 체크!!!
            cart.setSellerId(hashStock.get(storeName).getId());
            cart.setRegistrationDate(new Date(System.currentTimeMillis()));
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

    //search 전 등록
    else { 
      hashStock = cartDao.findBySellerId((String) request.getAttribute("productName"));
      while(true) {
        String input = Prompt.inputString("가게명을 선택하세요(0.취소) > ");
        if(input.equals("0")) { return; }

        storeName = cartDao.findStoreName(hashStock.keySet(), input);

        if (storeName==null) {
          System.out.println("가게명을 다시 입력해주세요.\n");
          continue;
        }

        stocks = cartDao.checkNum("수량 : ");
        if (stocks <= hashStock.get(storeName).getStocks()) {
          cart.setCartStocks(stocks);
          break;
        } else {
          System.out.println("주문수량이 재고를 초과하였습니다.\n");
          return;
        }
      }
      cart.setStock(hashStock.get(storeName));
      cart.setCartPrice(hashStock.get(storeName).getPrice()*stocks);
      // 체크!!!
      cart.setSellerId(hashStock.get(storeName).getId());
      cart.setRegistrationDate(new Date(System.currentTimeMillis()));
      cart.setId(nowLoginId);
      cartDao.insert(cart);
      System.out.println("장바구니가 등록되었습니다.\n");
    }
  }
}