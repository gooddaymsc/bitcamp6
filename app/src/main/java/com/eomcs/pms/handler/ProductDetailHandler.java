package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Product;

public class ProductDetailHandler extends AbstractProductHandler {
  ProductPrompt productPrompt;
  public ProductDetailHandler (ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[상품 상세보기]");

    Product product = productPrompt.findByProduct((String) request.getAttribute("productName"));

    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.\n");
      return;
    }

    System.out.printf("주종: %s\n", product.getProductType());
    System.out.printf("평점: %.2f\n", product.getRate());
    System.out.printf("원산지: %s\n", product.getCountryOrigin());
    System.out.printf("품종: %s\n", product.getVariety());
    System.out.printf("알콜도수: %.1f\n", product.getAlcoholLevel());
    System.out.printf("당도: %d\n", product.getSugerLevel());
    System.out.printf("산도: %d\n", product.getAcidity());
    System.out.printf("바디감: %d\n", product.getWeight());
    System.out.println();


  } 
}




