package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewListHandler extends AbstractReviewHandler {

  ProductPrompt productPrompt;

  public ReviewListHandler (ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[Reviews]");

      Product product = (Product) request.getAttribute("상품");

      System.out.printf("%-6s\t%-10s\t%-6s\t%-6s\n",
          "평점", "코멘트", "작성자", "등록일");
      System.out.println("--------------------------------------------------------------------------");


      for(Review re : product.getReviewList()) {
        System.out.printf("%-6s\t%-10s\t%-6s\t%-6s\n",  
            re.getScore(),
            re.getComment(),
            re.getId(),
            re.getRegisteredDate());
      }
      System.out.println();

      if (App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER ) {
        System.out.println("1. 리뷰작성 / 2. 리뷰변경 / 3. 리뷰삭제 / 이전(0)");
        // 상품 목록 후 판매자는 재고에 등록하게.
        while (true) {
          int choose = Prompt.inputInt("선택 > ");
          System.out.println();
          switch (choose) {
            case 1 : request.getRequestDispatcher("/review/add").forward(request); continue Loop;
            case 2 : request.getRequestDispatcher("/review/update").forward(request); continue Loop;
            case 3 : request.getRequestDispatcher("/review/delete").forward(request); continue Loop;
            case 0 : return;
            default : System.out.println("다시 선택해 주세요.\n"); continue;
          }
        }
      } else {
        return;
      }
    }
  } 
}





