package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductAddHandler extends AbstractProductHandler {

  @Override
  public void execute(int i) {}

  int i = 1;
  public ProductAddHandler(List<Product> productList) {
    super(productList);

    Product testProduct = new Product();
    testProduct.setProductName("쇼비뇽");
    testProduct.setProductType("와인");
    testProduct.setCountryOrigin("프랑스");
    testProduct.setVariety("1");
    testProduct.setAlcoholLevel(1);
    testProduct.setSugerLevel(1);
    testProduct.setAcidity(1);
    testProduct.setWeight(1);

    productList.add(testProduct);

    testProduct = new Product();
    testProduct.setProductName("앱솔루트");
    testProduct.setProductType("보트카");
    testProduct.setCountryOrigin("스웨덴");
    testProduct.setVariety("2");
    testProduct.setAlcoholLevel(2);
    testProduct.setSugerLevel(2);
    testProduct.setAcidity(2);
    testProduct.setWeight(2);

    productList.add(testProduct);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0 || App.getLoginUser().getAuthority() == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }

    System.out.println("[상품 등록]");

    Product product = new Product();

    product.setProductNumber(i++);
    product.setProductName(Prompt.inputString("상품명 : "));
    product.setProductType(Prompt.inputString("주종 : "));
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












