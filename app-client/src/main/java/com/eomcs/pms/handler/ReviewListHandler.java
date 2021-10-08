package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewListHandler implements Command {

  ProductDao productDao;

  public ReviewListHandler (ProductDao productDao) {
    this.productDao = productDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[Reviews]");

      int productNumber = (Integer)request.getAttribute("productNumber");


      System.out.printf("%-6s\t%-6s\t%-10s\t%-6s\t%-6s\n",
          "no","평점", "코멘트", "작성자", "등록일");
      System.out.println("--------------------------------------------------------------------------");

      Collection<Review> reviewList = productDao.findAll(productNumber);

      if (reviewList.equals(null)) {
        System.out.println("아직 등록된 리뷰가 없습니다.");

      } else {
        for(Review re : reviewList) {
          System.out.printf("%-6s\t%-6s\t%-10s\t%-6s\t%-6s\n",  
              re.getNo(),
              re.getScore(),
              re.getComment(),
              re.getId(),
              re.isPurchase(),
              re.getRegisteredDate());
        }
      }
      System.out.println();

      if (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_BUYER ) {
        System.out.println("리뷰작성(C) / 리뷰상세(P) / 리뷰변경(U) / 리뷰삭제(D) / 이전(0)");
        // 상품 목록 후 판매자는 재고에 등록하게.
        while (true) {
          String choose = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose) {
            case "C" :
            case "c" : request.getRequestDispatcher("/review/add").forward(request); continue Loop;
            case "P" :
            case "p" : request.getRequestDispatcher("/review/detail").forward(request); continue Loop;
            case "U" :
            case "u" : request.getRequestDispatcher("/review/update").forward(request); continue Loop;
            case "D" :
            case "d" : request.getRequestDispatcher("/review/delete").forward(request); continue Loop;
            case "0" : return;
            default : System.out.println("다시 선택해 주세요."); continue;
          }
        }
      } else {
        return;
      }
    }
  }
}




