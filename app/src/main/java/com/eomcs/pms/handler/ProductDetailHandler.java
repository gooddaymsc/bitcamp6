package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductDetailHandler extends AbstractProductHandler {
  @Override
  public void execute(int i) {}

  public ProductDetailHandler(List<Product> alcoholList) {
    super(alcoholList);
  }

  @Override
  public void execute() {
    System.out.println("[상품 상세보기]");

    Product product = findByProduct(Prompt.inputString("상품명 : "));

    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.");
      return;
    }

    System.out.printf("상품명: %s\n", product.getProductName());
    System.out.printf("주종: %s\n", product.getProductType());
    System.out.printf("원산지: %s\n", product.getCountryOrigin());
    System.out.printf("품종: %s\n", product.getVariety());
    System.out.printf("알콜도수: %.2f\n", product.getAlcoholLevel());
    System.out.printf("당도: %d\n", product.getSugerLevel());
    System.out.printf("산도: %d\n", product.getAcidity());
    System.out.printf("바디감: %d\n", product.getWeight());

  }

}












