package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Product;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ProductListHandler implements Command {

  RequestAgent requestAgent;

  public ProductListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.printf("[상품 목록]");
      if (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_SELLER ||
          ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
        System.out.println(" || 상품 등록(A) / 이전(0)\n");
      } else {
        System.out.println(" || 이전(0)\n");
      }
      System.out.printf("%-6s\t%-8s\t%-15s\t%-8s\t%-6s\t%-6s\t%-3s\t%-3s\t%-3s\n",
          "상품번호", "상품명", "주종 - 상세주종", "원산지", "용량", "당도", "산도", "바디감", "도수");
      System.out.println("--------------------------------------------------------------------------------------------------------");

      requestAgent.request("product.selectList", null);
      if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("목록조회 실패!");
        return;
      }

      Collection<Product> productList = requestAgent.getObjects(Product.class);

      for (Product product : productList) {
        System.out.printf(" %-8d\t%-8s\t%-4s-%s\t%-9s\t%-6d\t%-6d\t%-3d\t%-3d\t%-3.2f\n", 
            product.getProductNumber(), 
            product.getProductName(), 
            product.getProductType(), 
            product.getProductSubType(),
            product.getCountryOrigin(),
            product.getVolume(),
            product.getSugerLevel(),
            product.getAcidity(),
            product.getWeight(),
            product.getAlcoholLevel());
      }
      System.out.println();
      // buyer-비회원 : 상세정보, 검색 / seller - admin : 상세정보, 등록, 검색

      if ((ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_BUYER) ||
          (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT)) {
        while (true) {
          System.out.println("상세정보보기(R) / 검색(1)");
          // 상품 목록 후 판매자는 재고에 등록하게.
          String choose = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose) {
            case "0" : return;
            case "r" : 
            case "R" : request.getRequestDispatcher("/product/detail").forward(request); continue Loop;
            case "1" : request.getRequestDispatcher("/product/search").forward(request); continue Loop;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else if ((ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_SELLER) ||
          (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN)){
        while (true) {
          System.out.println("상세정보보기(R) / 검색(1)");
          String choose = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose) {
            case "0" : return;
            case "1" : request.getRequestDispatcher("/product/search").forward(request); continue Loop;
            case "a" :
            case "A" : request.getRequestDispatcher("/product/add").forward(request); continue Loop;
            case "r" :
            case "R" : request.getRequestDispatcher("/product/detail").forward(request); continue Loop;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      }
    }
  }
}