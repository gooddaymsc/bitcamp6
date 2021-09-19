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
    System.out.println("\n[상품 상세보기]");

    Product product = productPrompt.findByProduct(Prompt.inputString("상품명 : "));

    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.");
      return;
    }

    System.out.printf("상품명: %s\n", product.getProductName());
    System.out.printf("주종: %s\n", product.getProductType());
    System.out.printf("평점: %.2f\n", product.getRate());
    System.out.printf("원산지: %s\n", product.getCountryOrigin());
    System.out.printf("품종: %s\n", product.getVariety());
    System.out.printf("알콜도수: %.1f\n", product.getAlcoholLevel());
    System.out.printf("당도: %d\n", product.getSugerLevel());
    System.out.printf("산도: %d\n", product.getAcidity());
    System.out.printf("바디감: %d\n", product.getWeight());
    request.setAttribute("상품", product);
    request.getRequestDispatcher("/review/list").forward(request);

    if (App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER ) {
      System.out.println("\n1. 장바구니 등록 / 2. 이전(0)");
      // 상품 목록 후 판매자는 재고에 등록하게.
      int choose = Prompt.inputInt("선택 > ");
      while (true) {
        switch (choose) {
          case 1 : request.getRequestDispatcher("/cart/add").forward(request); return;
          case 0 : return;
          default : System.out.println("다시 선택해 주세요"); continue;
        }
      }
    } else if (App.getLoginUser().getAuthority() == Menu.ACCESS_SELLER){
      System.out.println("\n1. 상품변경 / 2. 상품삭제 / 3. 재고등록 / 이전(0)");
      int choose = Prompt.inputInt("선택 > ");
      while (true) {
        switch (choose) {
          case 1 : request.getRequestDispatcher("/product/update").forward(request); return;
          case 2 : request.getRequestDispatcher("/product/delete").forward(request); return;
          case 3 : request.getRequestDispatcher("/stock/add").forward(request); return;
          case 0 : return;
          default : System.out.println("다시 선택해 주세요"); continue;
        }
      }

    } else if (App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
      System.out.println("\n1. 상품변경 / 2. 상품삭제 / 이전(0)");
      int choose = Prompt.inputInt("선택 > ");
      while (true) {
        switch (choose) {
          case 1 : request.getRequestDispatcher("/product/update").forward(request); return;
          case 2 : request.getRequestDispatcher("/product/delete").forward(request); return;
          case 0 : return;
          default : System.out.println("다시 선택해 주세요"); continue;
        }
      }
    }
  } 
}




