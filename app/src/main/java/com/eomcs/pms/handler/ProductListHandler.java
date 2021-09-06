package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class ProductListHandler extends AbstractProductHandler {
  StockPrompt stockPrompt;
  static int stockNumber = 1;
  public ProductListHandler(List<Product> productList, StockPrompt stockPrompt) {
    super(productList);
    this.stockPrompt = stockPrompt;
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

    if (App.getLoginUser().getAuthority() == Menu.ACCESS_PRIVACY ) {

      return;
    } else if (App.getLoginUser().getAuthority() == Menu.ACCESS_SELLER){
      System.out.println("\n[재고등록]");
      Stock stock = new Stock(); 
      String productName = Prompt.inputString("상품명 : ");
      Product product = findByProduct(productName);


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
        StockList stockList = stockPrompt.findById(App.getLoginUser().getId());
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
