package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockAddHandler extends AbstractStockHandler {
  public StockAddHandler(StockPrompt stockPrompt) {
    super(stockPrompt);

  }

  @Override
  public void execute() {   
    String nowLoginId = App.getLoginUser().getId();

    System.out.println("\n[재고등록]");
    Stock stock = new Stock(); 
    String productName = Prompt.inputString("상품명 : ");
    Product product = ProductPrompt.findByProduct(productName);
    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.");
      return;
    }

    if (stockPrompt.findByStock(productName, nowLoginId)) {
      System.out.println("이미 추가된 상품입니다.");
      return;
    }

    stock.setProduct(product);
    stock.setPrice(Prompt.inputInt("판매 가격 :"));
    stock.setStocks(Prompt.inputInt("재고 수량 :"));

    int[] sizeIndex = stockPrompt.getStockListSizeById(nowLoginId);
    stock.setStockNumber(sizeIndex[0]);
    App.allStockList.get(sizeIndex[1]).getSellerStock().add(stock);

    System.out.println("재고 등록을 완료하였습니다.");
  }

}