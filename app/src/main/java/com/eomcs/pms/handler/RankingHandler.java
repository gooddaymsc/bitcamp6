package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class RankingHandler implements Command {

  List<Product> productList;
  ProductPrompt productPrompt;

  public RankingHandler(List<Product> productList, ProductPrompt productPrompt) {
    this.productList = productList;
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {  

    //    System.out.println("[실시간 랭킹]\n");
    System.out.println("  -----------   ");
    System.out.println(" |  RANKING  |   ");
    System.out.println("  -----------  \n ");


    HashMap <String, Float > map = new HashMap<>();
    HashMap <Integer, String> numberMap = new HashMap<>();

    for(Product product : productList) {  
      map.put(product.getProductName(),product.getRate());
    }

    List<Entry<String, Float>> entries = new ArrayList<>(map.entrySet());

    Collections.sort(entries, new Comparator<Entry<String, Float>>(){

      @Override
      public int compare(Entry<String, Float> obj1, Entry<String,Float> obj2) {
        return obj2.getValue().compareTo(obj1.getValue()); 
      }
    });

    int no = 1;
    for(Entry<String, Float> entry : entries) {
      System.out.printf(" * %d위 %s *  (평점: %.2f점)  \n", no++, entry.getKey(), entry.getValue());
      for(Product product : productList)
      {
        if(entry.getKey().equals(product.getProductName())){ 
          System.out.printf("- 주종 : %s\n", product.getProductType());
          System.out.printf("- 원산지 : %s\n", product.getCountryOrigin());
          System.out.printf("- 알콜도수 : %.2f\n", product.getAlcoholLevel());
          System.out.println("-----------------------------------------------");
          numberMap.put(no-1, product.getProductName());
        }
      }
      if(no == 6) {break;}
    }
    Loop: while(true) {
      System.out.println();
      System.out.println("1. 상품 상세정보 보기 / 0. 이전 ");
      int choose = Prompt.inputInt("선택 > ");
      switch (choose) {
        case 1 : 
          int chooseNum = productPrompt.checkNum("상품선택(1-5위) > ");
          System.out.printf("\n # 상품명 : %s \n", numberMap.get(chooseNum));
          request.setAttribute("productName", numberMap.get(chooseNum));
          System.out.println();
          request.getRequestDispatcher("/product/detail").forward(request);       //상품 상세보기

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
        case 0 : return;
        default : System.out.println("다시 선택해 주세요."); continue;
      }
    }
  }
}




