package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.StockList;

public class ProductRankingHandler extends AbstractProductHandler {
  StockPrompt stockPrompt;
  ProductPrompt productPrompt;
  CartPrompt cartPrompt;
  List<Product> productList;
  List<StockList> allStockList;
  SellerPrompt sellerPrompt;

  public ProductRankingHandler(StockPrompt stockPrompt, ProductPrompt productPrompt, CartPrompt cartPrompt, List<Product> productList, List<StockList> allStockList, SellerPrompt sellerPrompt) {
    this.stockPrompt = stockPrompt;
    this.productPrompt = productPrompt;
    this.cartPrompt = cartPrompt;
    this.productList = productList;
    this.allStockList = allStockList;
    this.sellerPrompt = sellerPrompt;
  }



  @Override
  public void execute() {
    //    String nowLoginId = App.getLoginUser().getId();
    System.out.println("[이 달의 술]");
    System.out.println("1위");
    System.out.printf("%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
        "상품번호", "상품명", "주종", "원산지", "품종", "당도","산도","바디감", "도수");
    System.out.println("--------------------------------------------------------------------------");
    for (Product product : productList) {
      System.out.printf("%-6d\t%-6s\t%-6s\t%-6s\t%-6s\t%-6d\t%-6d\t%-6d\t%-6.2f\n", 
          product.getProductNumber(), 
          product.getProductName(), 
          product.getProductType(), 
          product.getCountryOrigin(),
          product.getVariety(),
          product.getSugerLevel(),
          product.getAcidity(),
          product.getWeight(),
          product.getAlcoholLevel());
    }

    int size = productList.size();
    Integer[] rank = new Integer[5];
    Integer[] scores = productList.toArray(new Integer[size]);
    for(int i=0; i<size; i++) {
      rank[i] = 1;
      for(int j=0; j<size; j++) {
        if(scores[i]>scores[j]) {
          rank[i]++;
        }
      } 
    }
    for(int i=0; i<size; i++) {
      System.out.println(scores[i]+"점"+rank[i]+"등");
    }
  }
}
