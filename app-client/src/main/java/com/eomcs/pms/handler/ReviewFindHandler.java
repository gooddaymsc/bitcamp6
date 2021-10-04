package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewFindHandler implements Command {

  ReviewDao reviewDao;

  public ReviewFindHandler  (ReviewDao reviewDao) {
    this.reviewDao = reviewDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[내가 남긴 리뷰 목록]\n");
    System.out.printf(" %-5s\t%-4s\t%-8s\t%-6s\n",
        "상품명","평점","한줄평","등록일");
    System.out.println("---------------------------------------------------------------");


    for(Product product : productList) {
      for (Review review : product.getReviewList()) {
        if(review.getId().equals(App.getLoginUser().getId()))

          System.out.printf("%-5s\t%-4s\t%-8s\t%-6s\n", 
              product.getProductName(),
              review.getScore(),
              review.getComment(),
              review.getRegisteredDate());
      }    
    }

    if (App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER ) {
      System.out.println();
      String productName = Prompt.inputString("상품명 : ");
      request.setAttribute("productName", productName);

      System.out.println("리뷰변경(U) / 리뷰삭제(D) / 이전(0)");
      // 상품 목록 후 판매자는 재고에 등록하게.
      while (true) {
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "u" :
          case "U" :
            request.getRequestDispatcher("/review/update").forward(request); return;
          case "d":
          case "D":
            request.getRequestDispatcher("/review/delete").forward(request); return;
          case "0" : return;
          default : System.out.println("다시 선택해 주세요."); continue;
        }
      }
    } else {
      return;
    }

  }
}


