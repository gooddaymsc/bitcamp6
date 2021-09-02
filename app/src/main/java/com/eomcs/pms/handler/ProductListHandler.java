package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;

public class ProductListHandler extends AbstractProductHandler {

  public ProductListHandler(List<Product> productList) {
    super(productList);
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
  }

}












