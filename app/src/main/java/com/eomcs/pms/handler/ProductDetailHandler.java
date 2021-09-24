package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductDetailHandler extends AbstractProductHandler {
  ProductPrompt productPrompt;
  public ProductDetailHandler (ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[상품 상세보기]");

      Product product = productPrompt.findByProduct((String) request.getAttribute("productName"));

      if (product == null) {
        System.out.println("입력하신 상품이 없습니다.\n");
        return;
      }

      System.out.printf("주종: %s\n", product.getProductType());
      System.out.printf("평점: %.2f\n", product.getRate());
      System.out.printf("원산지: %s\n", product.getCountryOrigin());
      System.out.printf("품종: %s\n", product.getVariety());
      System.out.printf("알콜도수: %.1f\n", product.getAlcoholLevel());
      System.out.printf("당도: %d\n", product.getSugerLevel());
      System.out.printf("산도: %d\n", product.getAcidity());
      System.out.printf("바디감: %d\n", product.getWeight());
      System.out.println();

      //장바구니 등록 / 재고등록 / 리뷰보기(CRUD) 

      if (App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER ) {
        while (true) {
          System.out.println("1. 리뷰보기 / 2. 장바구니 등록 / 이전(0)");
          // 상품 목록 후 판매자는 재고에 등록하게.
          int choose1 = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose1) {
            case 1 : request.getRequestDispatcher("/review/list").forward(request); continue Loop;
            case 2 : request.getRequestDispatcher("/cart/add").forward(request); continue Loop;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else if (App.getLoginUser().getAuthority() == Menu.ACCESS_SELLER){
        while (true) {
          System.out.println("1. 리뷰보기 / 2. 상품변경 / 3. 상품삭제 / 4. 재고등록 / 이전(0)");
          int choose2 = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose2) {
            case 1 : request.getRequestDispatcher("/review/list").forward(request); continue Loop;
            case 2 : request.getRequestDispatcher("/product/update").forward(request); continue;
            case 3 : request.getRequestDispatcher("/product/delete").forward(request); continue;
            case 4 : request.getRequestDispatcher("/stock/add").forward(request); continue Loop;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else if (App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
        while (true) {
          System.out.println("1. 리뷰보기 / 2. 상품변경 / 3. 상품삭제 / 이전(0)");
          int choose3 = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose3) {
            case 1 : request.getRequestDispatcher("/review/list").forward(request); continue Loop;
            case 2 : request.getRequestDispatcher("/product/update").forward(request); continue;
            case 3 : request.getRequestDispatcher("/product/delete").forward(request); continue;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else {
        while (true) {
          System.out.println("1. 리뷰보기 / 이전(0)");
          int choose4 = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose4) {
            case 1 : request.getRequestDispatcher("/review/list").forward(request); continue Loop;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      }
    } 
  }
}




