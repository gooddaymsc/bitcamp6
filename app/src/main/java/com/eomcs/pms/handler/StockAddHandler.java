package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class StockAddHandler extends AbstractStockHandler {
  //  int stockNumber = 1;
  ProductListHandler productListHandler;

  public StockAddHandler(ProductListHandler productListHandler) {
    //    super(stockList);
    this.productListHandler = productListHandler;
    StockList teststockList = findById("aaaa");
    Stock testStock = new Stock();
    testStock.setStockNumber(teststockList.getSellerStock().size());
    testStock.setProduct(productListHandler.productList.get(0));
    testStock.setStocks(3);
    testStock.setPrice(38000);

    App.allStockList.get(0).getSellerStock().add(testStock);

    teststockList = findById("aaaa");
    testStock = new Stock();
    testStock.setStockNumber(teststockList.getSellerStock().size());
    testStock.setProduct(productListHandler.productList.get(1));
    testStock.setStocks(4);
    testStock.setPrice(52000);

    App.allStockList.get(0).getSellerStock().add(testStock);

    teststockList = findById("aaa");
    testStock = new Stock();
    testStock.setStockNumber(teststockList.getSellerStock().size());
    testStock.setProduct(productListHandler.productList.get(0));
    testStock.setStocks(3);
    testStock.setPrice(38000);

    App.allStockList.get(1).getSellerStock().add(testStock);


  }

  @Override
  public void execute() {   
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_SELLER ) {

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
    StockList stockList = findById(App.getLoginUser().getId());
    stock.setStockNumber(stockList.getSellerStock().size());
    stock.setPrice(Prompt.inputInt("판매 가격 :"));
    stock.setStocks(Prompt.inputInt("재고 수량 :"));
    stockList.getSellerStock().add(stock);
    System.out.println("재고 등록을 완료하였습니다.");
  }

}