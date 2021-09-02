package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockAddHandler extends AbstractStockHandler {
  int stockNumber = 1;
  ProductListHandler productListHandler;

  public StockAddHandler(List<Stock> stockList, ProductListHandler productListHandler) {
    super(stockList);
    this.productListHandler = productListHandler;

    Stock testStock = new Stock();
    testStock.setProduct(productListHandler.productList.get(0));
    testStock.setStocks(3);
    testStock.setPrice(38000);

    stockList.add(testStock);

    testStock = new Stock();
    testStock.setProduct(productListHandler.productList.get(1));
    testStock.setStocks(4);
    testStock.setPrice(52000);

    stockList.add(testStock);

  }

  @Override
  public void execute() {   
    if (App.getLoginUser().getAuthority() == 0 || App.getLoginUser().getAuthority() == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }

    System.out.println("\n[재고등록]");
    Stock stock = new Stock(); 
    Product product = productListHandler.findByProduct(Prompt.inputString("상품명 : "));

    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.");
      return;
    }

    stock.setProduct(product);
    stock.setStockNumber(stockNumber++);
    stock.setPrice(Prompt.inputInt("판매 가격 :"));
    stock.setStocks(Prompt.inputInt("재고 수량 :"));

    stockList.add(stock);
    System.out.println("재고 등록을 완료하였습니다.");
  }

}






