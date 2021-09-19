package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;

public class ReviewListHandler extends AbstractReviewHandler {

  ProductPrompt productPrompt;

  public ReviewListHandler (ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("\n[Reviews]");

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

  }
} 





