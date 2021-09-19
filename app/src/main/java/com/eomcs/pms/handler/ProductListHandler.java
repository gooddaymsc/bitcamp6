package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductListHandler extends AbstractProductHandler {
  List<Product> productList;
  public ProductListHandler(List<Product> productList) {
    this.productList = productList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[상품 목록]");
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
      System.out.println("\n1. 상세보기 / 이전(0)");
      while(true) {
        int choose = Prompt.inputInt("선택 > ");
        System.out.println();
        switch (choose) {
          case 1 : request.getRequestDispatcher("/product/detail").forward(request); continue Loop;
          case 0 : return;
          default : System.out.println("다시 선택해 주세요.\n"); continue;
        }
      }
    }
  }
}