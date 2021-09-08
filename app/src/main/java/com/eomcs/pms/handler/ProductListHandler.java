package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class ProductListHandler extends AbstractProductHandler {
  StockPrompt stockPrompt;
  CartPrompt cartPrompt;
  static int stockNumber = 1;
  public ProductListHandler(StockPrompt stockPrompt, CartPrompt cartPrompt) {
    this.stockPrompt = stockPrompt;
    this.cartPrompt = cartPrompt;
  }

  @Override
  public void execute() {

    System.out.println("[상품 목록]");
    for (Product product : App.productList) {
      System.out.printf("%d, %s, %s, %s, %s, %.2f, %d, %d, %d \n", 
          product.getProductNumber(), 
          product.getProductName(), 
          product.getProductType(), 
          product.getCountryOrigin(),
          product.getVariety(),
          product.getAlcoholLevel(),
          product.getSugerLevel(),
          product.getAcidity(),
          product.getWeight());
    }

    if (App.getLoginUser().getAuthority() == Menu.ACCESS_PRIVACY ) {
      System.out.println("\n[장바구니 등록]");
      Cart cart =new Cart();
      HashMap<String, Stock> hashStock = stockPrompt.findBySellerId(Prompt.inputString("상품명 : "));
      if (hashStock == null) {
        System.out.println("해당 상품이 없습니다.");
        return;
      }

      if (hashStock.size() == 0) {
        System.out.println("해당 상품을 갖는 판매자가 없습니다.");
        return;
      }
      String storeName = Prompt.inputString("가게명을 선택하세요 > ");

      cart.setStock(hashStock.get(storeName));
      int stockNumber = Prompt.inputInt("수량 : ");

      while(true) {
        if (stockNumber <= hashStock.get(storeName).getStocks()) {
          cart.setCartStocks(stockNumber);
          break;
        } else {
          System.out.println("주문수량이 재고를 초과하였습니다.");
          return;
        }
      }
      //      cart.setCartStocks(stockNumber);
      cart.setCartPrice(hashStock.get(storeName).getPrice()*stockNumber);
      //      cart.setCartNumber(stockPrompt.findCartListById(App.getLoginUser().getId()).size()+1);
      cart.setSellerId(stockPrompt.findByPlaceName(storeName).getId());
      cart.setRegistrationDate(new Date(System.currentTimeMillis()));
      System.out.println("장바구니가 등록되었습니다.");
      CartList cartList = cartPrompt.findCartListById(App.getLoginUser().getId());
      cartList.getPrivacyCart().add(cart);
      return;

    } else if (App.getLoginUser().getAuthority() == Menu.ACCESS_SELLER){
      System.out.println("\n[재고등록]");
      Stock stock = new Stock(); 
      String productName = Prompt.inputString("상품명 : ");
      Product product = ProductPrompt.findByProduct(productName);


      if (product == null) {
        System.out.println("입력하신 상품이 없습니다.");
        return;
      }

      if (stockPrompt.findByStock(productName) != null) {
        System.out.println("이미 추가된 상품입니다.");
        return;
      }
      stock.setPrice(Prompt.inputInt("판매 가격 :"));
      stock.setStocks(Prompt.inputInt("재고 수량 :"));

      String input = Prompt.inputString("정말 등록하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        StockList stockList = stockPrompt.findStockListById(App.getLoginUser().getId());
        stock.setProduct(product);
        stock.setStockNumber(stockList.getSellerStock().size()+1);
        stockList.getSellerStock().add(stock);
        System.out.println("재고 등록을 완료하였습니다.");
        return;
      } else {
        System.out.println("재고 등록을 취소하였습니다.");
        return;
      }
    }
  }
}
