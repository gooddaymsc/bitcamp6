package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductAddHandler extends AbstractProductHandler {
  ProductPrompt productPrompt;
  int productNumber = 1;

  public ProductAddHandler(ProductPrompt productPrompt) {
    super(productPrompt);

  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_SELLER &
        App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("해당 메뉴 접근 권한이 없습니다.");
      return;
    }

    System.out.println("[상품 등록]");

    Product product = new Product();

    product.setProductNumber(productNumber++);
    product.setProductName(Prompt.inputString("상품명 : "));
    String productType = Prompt.inputString("주종 : ");
    while(true) {
      if (productType.equals("양주") || productType.equals("위스키") 
          || productType.equals("브랜디") || productType.equals("진")
          || productType.equals("보드카") || productType.equals("데킬라")
          || productType.equals("와인") || productType.equals("화이트와인")
          || productType.equals("로제와인")  || productType.equals("레드와인")
          || productType.equals("스파클링와인")  || productType.equals("디저트와인")
          || productType.equals("사케")  || productType.equals("하이볼")) {
        product.setProductType(productType);
        break;
      } else {
        System.out.println("정확한 주종을 입력하세요.");
        return;
      } 
    }
    product.setCountryOrigin(Prompt.inputString("원산지 : "));
    product.setVariety(Prompt.inputString("품종 : "));
    product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
    product.setSugerLevel(checkNum("당도(1-5) : "));
    product.setAcidity(checkNum("산도(1-5) : "));
    product.setWeight(checkNum("바디감(1-5) : "));
    App.productList.add(product);
    System.out.println("상품을 등록하였습니다.");
  }

}












