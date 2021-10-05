package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewFindHandler implements Command {

  ProductDao productDao;

  public ReviewFindHandler  ( ProductDao productDao) {
    this.productDao = productDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[내가 남긴 리뷰 목록]\n");
    System.out.printf(" %-5s\t%-4s\t%-8s\t%-6s\n",
        "상품명","평점","한줄평","등록일");
    System.out.println("---------------------------------------------------------------");

    Collection<Product> productList = productDao.findAll();

    for(Product product : productList) {
      for (Review review : product.getReviewList()) {
        if(review.getId().equals(ClientApp.getLoginUser().getId()))

          System.out.printf("%-5d\t%-5s\t%-4s\t%-8s\t%-6s\n", 
              review.getNo(),
              product.getProductName(),
              review.getScore(),
              review.getComment(),
              review.getRegisteredDate());
      }    
    }

    if (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_BUYER ) {
      System.out.println();
      int reviewNumber = Prompt.inputInt("리뷰번호 : ");
      //리뷰번호의 상품명의 상품번호를 찾아 넘김

      Product product = productDao.findByNo2(reviewNumber);

      request.setAttribute("productNumber", product.getProductNumber());

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


