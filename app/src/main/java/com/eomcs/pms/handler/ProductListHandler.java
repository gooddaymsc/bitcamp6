package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductListHandler extends AbstractProductHandler {
  List<Product> productList;
  ProductPrompt productPrompt;
  public ProductListHandler(List<Product> productList, ProductPrompt productPrompt) {
    this.productList = productList;
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.printf("[상품 목록]");
      if (App.getLoginUser().getAuthority() == Menu.ACCESS_SELLER ||
          App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
        System.out.println(" || 상품 등록(A) / 이전(0)\n");
      } else {
        System.out.println(" || 이전(0)\n");
      }
      System.out.printf("\n%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
          "상품번호", "상품명", "주종", "원산지", "품종", "당도","산도","바디감", "도수");
      System.out.println("--------------------------------------------------------------------------");
      if (productList.size()==0) {
        System.out.println("등록된 상품이 없습니다.\n");
      }
      for (Product product : productList) {
        System.out.printf("%-6d\t%-6s\t%-6s\t%-6s\t%-6s\t%-6d\t%-6d\t%-6d\t%-6.2f\n", 
            product.getProductNumber(), 
            product.getProductName(), 
            product.getProductType(), 
            product.getCountryOrigin(),
            product.getVariety(),
            product.getSugerLevel(),
            product.getAcidity(),
            product.getWeight(),
            product.getAlcoholLevel());
      }
      System.out.println();
      // buyer-비회원 : 상세정보, 검색 / seller - admin : 상세정보, 등록, 검색

      if ((App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER) ||
          (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT)) {
        while (true) {
          System.out.println("상세정보보기(R) / 검색(1)");
          // 상품 목록 후 판매자는 재고에 등록하게.
          String choose = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose) {
            case "0" : return;
            case "r" : 
            case "R" : request.getRequestDispatcher("/product/detail").forward(request); continue;
            case "1" : request.getRequestDispatcher("/product/search").forward(request); continue Loop;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else if ((App.getLoginUser().getAuthority() == Menu.ACCESS_SELLER) ||
          (App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN)){
        while (true) {
          System.out.println("상세정보보기(R) /  검색(1)");
          String choose = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose) {
            case "0" : return;
            case "1" : request.getRequestDispatcher("/product/search").forward(request); continue Loop;
            case "a" :
            case "A" : request.getRequestDispatcher("/product/add").forward(request); continue Loop;
            case "r" :
            case "R" : request.getRequestDispatcher("/product/detail").forward(request); continue;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      }
    }
  }
}