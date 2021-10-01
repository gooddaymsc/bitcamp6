package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Product;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ProductDetailHandler implements Command {

  RequestAgent requestAgent;
  ProductPrompt productPrompt;
  public ProductDetailHandler (RequestAgent requestAgent, ProductPrompt productPrompt) {
    this.requestAgent = requestAgent;
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    while(true) {
      System.out.println("[상품 상세보기]");

      Product product = productPrompt.findByProduct(Prompt.inputString("\n상품명 > "));

      request.setAttribute("productName", product.getProductName());

      //      if (product == null) {
      //        System.out.println("입력하신 상품이 없습니다.\n");
      //        return;
      //      }

      System.out.printf("주종: %s - %s\n", product.getProductType(),product.getProductSubType());
      //      System.out.printf("평점: %.2f\n", product.getRate());
      System.out.printf("원산지: %s\n", product.getCountryOrigin());
      if(product.getProductType().equals("와인")){
        System.out.printf("품종: %s\n", product.getVariety());  }
      System.out.printf("용량: %d\n", product.getVolume());
      System.out.printf("알콜도수: %.1f\n", product.getAlcoholLevel());
      System.out.printf("당도: %d\n", product.getSugerLevel());
      System.out.printf("산도: %d\n", product.getAcidity());
      System.out.printf("바디감: %d\n", product.getWeight());
      System.out.println();
      request.setAttribute("productName", product.getProductName());
      //장바구니 등록 / 재고등록 / 리뷰보기(CRUD) 

      if (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_BUYER ) {
        while (true) {
          System.out.println("리뷰보기(1) / 장바구니 등록(2) / 이전(0)");
          // 상품 목록 후 판매자는 재고에 등록하게.
          int choose1 = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose1) {
            case 1 : request.getRequestDispatcher("/review/list").forward(request); return;
            case 2 : request.getRequestDispatcher("/cart/add").forward(request); return;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else if (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_SELLER){
        while (true) {
          System.out.println("상품변경(U) / 상품삭제(D) / 리뷰보기(1) / 재고등록(2) / 이전(0)");
          String choose2 = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose2) {
            case "1" : request.getRequestDispatcher("/review/list").forward(request); return;
            case "2" : request.getRequestDispatcher("/stock/add").forward(request); return;
            case "u":
            case "U" : request.getRequestDispatcher("/product/update").forward(request); continue;
            case "d" :
            case "D" : request.getRequestDispatcher("/product/delete").forward(request); return;
            case "0" : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else if (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
        while (true) {
          System.out.println("상품변경(U) / 상품삭제(D) / 리뷰보기(1) / 이전(0)");
          String choose3 = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose3) {
            case "1" : request.getRequestDispatcher("/review/list").forward(request); return;
            case "u":
            case "U" : request.getRequestDispatcher("/product/update").forward(request); continue;
            case "d" :
            case "D" : request.getRequestDispatcher("/product/delete").forward(request); return;
            case "0" : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else {
        while (true) {
          System.out.println("리뷰보기(1) / 이전(0)");
          int choose4 = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose4) {
            case 1 : request.getRequestDispatcher("/review/list").forward(request); return;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      }
    } 
  }
}




