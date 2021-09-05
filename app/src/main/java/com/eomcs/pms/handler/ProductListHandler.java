package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class ProductListHandler extends AbstractProductHandler {
  int stockNumber = 1;
  public ProductListHandler(List<Product> productList) {
    super(productList);
  }

  @Override
  public void execute() {

    System.out.println("[상품 목록]");
    for (Product product : productList) {
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

    if (App.getLoginUser().getAuthority() != Menu.ACCESS_SELLER ) {
      return;
    } else {
      System.out.println("\n[재고등록]");
      Stock stock = new Stock(); 
      Product product = findByProduct(Prompt.inputString("상품명 : "));


      if (product == null) {
        System.out.println("입력하신 상품이 없습니다.");
        return;
      }

      stock.setPrice(Prompt.inputInt("판매 가격 :"));
      stock.setStocks(Prompt.inputInt("재고 수량 :"));

      String input = Prompt.inputString("정말 등록하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        stock.setProduct(product);
        stock.setStockNumber(stockNumber++);

        StockList stockList = findById(App.getLoginUser().getId());
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
