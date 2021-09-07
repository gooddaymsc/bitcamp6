package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class StockAddHandler extends AbstractStockHandler {
  public StockAddHandler(StockPrompt stockPrompt) {
    super(stockPrompt);

  }

  @Override
  public void execute() {   
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_SELLER ) {

      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }

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

    stock.setProduct(product);
    StockList stockList = stockPrompt.findStockListById(App.getLoginUser().getId());
    stock.setStockNumber(stockList.getSellerStock().size()+1);
    stock.setPrice(Prompt.inputInt("판매 가격 :"));
    stock.setStocks(Prompt.inputInt("재고 수량 :"));
    stockList.getSellerStock().add(stock);
    System.out.println("재고 등록을 완료하였습니다.");
  }

}