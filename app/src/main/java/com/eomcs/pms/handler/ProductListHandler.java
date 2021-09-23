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
      System.out.println("[상품 목록]");
      if (productList.size()==0) {
        System.out.println("등록된 상품이 없습니다.\n");
      }
      System.out.printf("%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
          "상품번호", "상품명", "주종", "원산지", "품종", "당도","산도","바디감", "도수");
      System.out.println("--------------------------------------------------------------------------");
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
      String productName = Prompt.inputString("\n상품명 선택 (0.이전) > ");
      if (productName.equals("0")) {
        return;
      } else {
        request.setAttribute("productName", productName);
        if (productPrompt.findByProduct2(productName)==null) {
          System.out.println("목록에 없는 상품입니다.\n");
          continue Loop;
        }
      }
      if (App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER ) {
        while (true) {
          System.out.println("1. 상세정보보기 / 2. 리뷰보기 / 3. 장바구니 등록 / 이전(0)");
          // 상품 목록 후 판매자는 재고에 등록하게.
          int choose = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose) {
            case 1 : request.getRequestDispatcher("/product/detail").forward(request); continue;
            case 2 : request.getRequestDispatcher("/review/list").forward(request); continue Loop;
            case 3 : request.getRequestDispatcher("/cart/add").forward(request); continue Loop;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else if (App.getLoginUser().getAuthority() == Menu.ACCESS_SELLER){
        while (true) {
          System.out.println("1. 상세정보보기 / 2. 리뷰보기 / 3. 상품변경 / 4. 상품삭제 / 5. 재고등록 / 이전(0)");
          int choose = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose) {
            case 1 : request.getRequestDispatcher("/product/detail").forward(request); continue;
            case 2 : request.getRequestDispatcher("/review/list").forward(request); continue Loop;
            case 3 : request.getRequestDispatcher("/product/update").forward(request); continue;
            case 4 : request.getRequestDispatcher("/product/delete").forward(request); continue;
            case 5 : request.getRequestDispatcher("/stock/add").forward(request); continue Loop;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }

      } else if (App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
        while (true) {
          System.out.println("1. 상세정보보기 / 2. 리뷰보기 / 3. 상품변경 / 4. 상품삭제 / 이전(0)");
          int choose = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose) {
            case 1 : request.getRequestDispatcher("/product/detail").forward(request); continue;
            case 2 : request.getRequestDispatcher("/review/list").forward(request); continue Loop;
            case 3 : request.getRequestDispatcher("/product/update").forward(request); continue;
            case 4 : request.getRequestDispatcher("/product/delete").forward(request); continue;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else {
        while (true) {
          System.out.println("1. 상세정보보기 / 2. 리뷰보기 / 이전(0)");
          int choose = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose) {
            case 1 : request.getRequestDispatcher("/product/detail").forward(request); continue;
            case 2 : request.getRequestDispatcher("/review/list").forward(request); continue Loop;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      }
    }
  }
}