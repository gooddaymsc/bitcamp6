package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductAddHandler extends AbstractProductHandler {

  List<Product> productList;
  ProductPrompt productPrompt;
  List<Integer> totalNumberList;
  public ProductAddHandler (List<Product> productList, ProductPrompt productPrompt,
      List<Integer> totalNumberList) {
    this.productPrompt = productPrompt;
    this.productList = productList;
    this.totalNumberList = totalNumberList;
  }

  @Override
  public void execute(CommandRequest request) {

    System.out.println("[상품 등록]");
    Product product = new Product();
    String productName = Prompt.inputString("상품명 : ");

    if (productPrompt.findByProduct(productName)!=null) {
      System.out.println("이미 추가된 상품입니다.\n");
      return;
    }
    product.setProductName(productName);
    product.setProductType(productPrompt.checkType("주종 : "));
    product.setProductSubType(productPrompt.checkSubType("상세주종 : ",product));
    product.setCountryOrigin(Prompt.inputString("원산지 : "));
    if(product.getProductType().equals("와인")) {
      product.setVariety(Prompt.inputString("품종 : "));
    }
    product.setVolume(Prompt.inputInt("용량 : "));
    product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
    product.setSugerLevel(checkNum("당도(1-5) : "));
    product.setAcidity(checkNum("산도(1-5) : "));
    product.setWeight(checkNum("바디감(1-5) : "));
    product.setProductNumber(totalNumberList.get(App.PROUDCT_NUMBER_INDEX));
    // Numbering은 마지막에
    totalNumberList.set(App.PROUDCT_NUMBER_INDEX, product.getProductNumber()+1);
    productList.add(product);

    System.out.println("상품을 등록하였습니다.\n");
  }

}












