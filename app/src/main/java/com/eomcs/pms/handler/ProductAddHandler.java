package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductAddHandler extends AbstractProductHandler {

  List<Product> productList;
  ProductPrompt productPrompt;
  public ProductAddHandler (List<Product> productList, ProductPrompt productPrompt) {
    this.productList = productList;
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute() {

    System.out.println("[상품 등록]");
    Product product = new Product();
    String productName = Prompt.inputString("상품명 : ");

    if (productPrompt.findByProduct(productName)!=null) {
      System.out.println("이미 추가된 상품입니다.");
      return;
    }
    product.setProductName(productName);
    product.setProductType(checkType("주종 : "));
    product.setCountryOrigin(Prompt.inputString("원산지 : "));
    product.setVariety(Prompt.inputString("품종 : "));
    product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
    product.setSugerLevel(checkNum("당도(1-5) : "));
    product.setAcidity(checkNum("산도(1-5) : "));
    product.setWeight(checkNum("바디감(1-5) : "));
    product.setProductNumber(App.totalNumberList.get(App.PROUDCT_NUMBER_INDEX));
    // Numbering은 마지막에
    App.totalNumberList.set(App.PROUDCT_NUMBER_INDEX, product.getProductNumber()+1);
    productList.add(product);

    System.out.println("상품을 등록하였습니다.");
  }

}












