package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductAddHandler extends AbstractProductHandler {

  List<Product> productList;

  public ProductAddHandler (List<Product> productList ) {
    this.productList = productList;
  }

  @Override
  public void execute() {

    System.out.println("[상품 등록]");

    Product product = new Product();

    product.setProductNumber(productList.size() +1);
    product.setProductName(Prompt.inputString("상품명 : "));
    product.setProductType(checkType("주종 : "));
    product.setCountryOrigin(Prompt.inputString("원산지 : "));
    product.setVariety(Prompt.inputString("품종 : "));
    product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
    product.setSugerLevel(checkNum("당도(1-5) : "));
    product.setAcidity(checkNum("산도(1-5) : "));
    product.setWeight(checkNum("바디감(1-5) : "));
    productList.add(product);
    System.out.println("상품을 등록하였습니다.");
  }

}












