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
    //String nowLoginId = App.getLoginUser().getId();
    HashMap<String, Seller> map = new HashMap<>();

    System.out.println("[상품검색]");
    String input = Prompt.inputString("\n상품입력: ");

    Product productIs = productPrompt.findByProduct(input);
    if (productIs == null) {
      System.out.println("입력하신 상품이 없습니다.\n");
      return;
    }

    String productName  = productPrompt.findByProduct2(input);   

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

      System.out.println("[재고 찾기]");

      if(App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
        System.out.println("로그인 후 이용가능합니다.\n");
        return;
      }

      while(true) {
        try {
          String adress = Prompt.inputString("주소입력: ");
          if(adress.equals("0")){
            return; }

          map = memberPrompt.findByAdress(adress); 
          break;

        } catch (Exception e) {
          System.out.println("* 주소입력을 다시 해주세요. (예: 서울시 강남구 역삼동 / 0.취소) ");
        }

      } if(map == null) {
        System.out.println("해당 위치에 판매처가 없습니다.\n");
        break;
      } else {
        System.out.println();
        System.out.println("[현재 상품 판매처]");
        for (HashMap.Entry<String, Seller> entry : map.entrySet()) { //판매자 id 추가
          System.out.printf("%-6s\t%-6s\t%-19s\t%-12s\t%-4s\n","가게명", "판매자", "주소", "연락처", "재고수량");
          System.out.println("--------------------------------------------------------------------------");
          System.out.printf("%-6s\t%-6s\t%-19s\t%-12s\t%-4s\n", 
              entry.getValue().getBusinessName(),
              entry.getValue().getId(),
              entry.getValue().getBusinessAddress(),
              entry.getValue().getBusinessPlaceNumber(),
              stockPrompt.findStockById(entry.getValue().getId(),input).getStocks());
        }
      }

      System.out.println("--------------------------------------------------------------------------");

      request.setAttribute("productName", productName); 
      while(true) {
        System.out.println("1. 장바구니 담기 / 2. 판매자에게 문의하기 / 이전(0)");
        int choose = Prompt.inputInt("선택 > ");
        System.out.println();
        switch(choose) {
          case 1 : request.getRequestDispatcher("/cart/add").forward(request); return;
          case 2 : request.getRequestDispatcher("/message/add").forward(request); break;
          case 0 : return;
        }
      }
    }
  }
}
