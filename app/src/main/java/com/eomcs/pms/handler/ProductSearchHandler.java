package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class ProductSearchHandler extends AbstractProductHandler {
  ProductPrompt productPrompt;
  StockPrompt stockPrompt;
  MemberPrompt memberPrompt;
  CartPrompt cartPrompt;
  List<Product> productList;

  public ProductSearchHandler(ProductPrompt productPrompt,  StockPrompt stockPrompt, 
      MemberPrompt memberPrompt,  CartPrompt cartPrompt ,List<Product> productList 
      ) {
    this.productPrompt = productPrompt;
    this.stockPrompt = stockPrompt;
    this.memberPrompt = memberPrompt;
    this.cartPrompt = cartPrompt;
    this.productList = productList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String storeName;
    //String storeAdress;
    String nowLoginId = App.getLoginUser().getId();
    HashMap<String, Seller> sellerInfo = null;   
    System.out.println("[상품검색]");


    String input = Prompt.inputString("상품입력: ");

    if (input.equals("")) {
      System.out.println("잘못 입력하셨습니다.");
      //      continue;
    }     

    String productName  = productPrompt.findByProduct2(input);   

    System.out.println("==========상품 목록==========");

    while(true) {
      int size = 1;
      for(Product product : productList) {
        if(product.getProductName().equals(productName)) {
          System.out.printf("상품번호: %d \n", size++);
          System.out.printf("상품명: %s\n", product.getProductName());
          System.out.printf("주종: %s\n", product.getProductType());
          System.out.printf("원산지: %s\n", product.getCountryOrigin());
          System.out.printf("품종: %s\n", product.getVariety());
          System.out.printf("알콜도수: %.2f\n", product.getAlcoholLevel()); 
          System.out.printf("당도: %d, 산도: %d, 바디감:%d\n", product.getSugerLevel(),product.getAcidity(),product.getWeight());
          System.out.println("-----------------------------------------");
        }
      }

      if(App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
        System.out.println("로그인 후 이용가능합니다.");
        return;
      }

      System.out.println("[재고 찾기]");

      while(true) {
        try {
          sellerInfo = memberPrompt.findByAdress(Prompt.inputString("주소입력: ")); 
          break;

        } catch (Exception e) {
          System.out.println("* 주소입력을 다시 해주세요. (예: 서울시 강남구 역삼동) ");
        }

      }

      if(sellerInfo == null) {
        System.out.println("해당 위치에 판매처가 없습니다.");
        return;
      } else {
        for (HashMap.Entry<String, Seller> entry : sellerInfo.entrySet()) {
          System.out.printf("가게명 : %s, 가게주소 : %s\n, 재고수량 : %d",
              entry.getValue().getBusinessName(),
              entry.getValue().getBusinessAddress());
        }
      }

      request.setAttribute("productName", productName); 
      request.getRequestDispatcher("/cart/add").forward(request);
    }
  }
}
















