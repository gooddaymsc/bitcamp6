package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public class StockAddHandler extends AbstractStockHandler {
  List<StockList> allStockList;
  ProductPrompt productPrompt;
  public StockAddHandler(List<StockList> allStockList, StockPrompt stockPrompt, ProductPrompt productPrompt) {
    super(stockPrompt);
    this.allStockList = allStockList;
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) {   

    String nowLoginId = App.getLoginUser().getId();

    System.out.println("[재고등록]");
    Stock stock = new Stock();
    String productName = (String) request.getAttribute("productName");
    Product product = productPrompt.findByProduct(productName);
    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.\n");
      return;
    }

    if (stockPrompt.findByStock(productName, nowLoginId)) {
      System.out.println("이미 추가된 상품입니다.\n");
      return;
    }

    stock.setProduct(product);
    stock.setPrice(checkPrice("판매 가격 : "));
    stock.setStocks(checkNum("재고 수량 : "));

    stockPrompt.putStockListById(nowLoginId, stock);
    System.out.println("재고 등록을 완료하였습니다.\n");
  }

}