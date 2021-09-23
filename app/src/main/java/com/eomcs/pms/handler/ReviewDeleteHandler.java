package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewDeleteHandler extends AbstractReviewHandler {

  ProductPrompt productPrompt;
  public ReviewDeleteHandler (ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[Reviews 삭제]");
    Product product =  productPrompt.findByProduct((String) request.getAttribute("productName"));

    Review review = findReviewById(product, App.getLoginUser().getId());
    if (review == null) {
      System.out.println("해당 상품에 작성하신 리뷰가 없습니다.\n");
      return;
    }

    if (!(review.getId().equals(App.getLoginUser().getId()) ||
        (App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN))) {
      System.out.println("작성자가 아니므로 삭제할 수 없습니다.\n");
      return;    
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      product.getReviewList().remove(review);
      System.out.println("리뷰 삭제 완료\n");
      return;
    }
    System.out.println("리뷰 삭제 취소\n");
    return;
  }
} 





