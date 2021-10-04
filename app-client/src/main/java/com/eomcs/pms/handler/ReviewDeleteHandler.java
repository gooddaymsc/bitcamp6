package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewDeleteHandler implements Command {

  ProductDao productDao;

  public ReviewDeleteHandler ( ProductDao productDao) {
    this.productDao = productDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[Reviews 삭제]");
    Product product =  productDao.findByProduct((String) request.getAttribute("productName"));

    Review review = productDao.findReviewById(product, ClientApp.getLoginUser().getId());

    //    if (review == null) {
    //      System.out.println("해당 상품에 작성하신 리뷰가 없습니다.\n");
    //      return;
    //    }

    if (!(review.getId().equals(ClientApp.getLoginUser().getId()) ||
        (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN))) {
      System.out.println("작성자가 아니므로 삭제할 수 없습니다.\n");
      return;    
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      productDao.deleteReview(review);
      System.out.println("리뷰 삭제 완료\n");
      return;
    }
    System.out.println("리뷰 삭제 취소\n");
    return;
  }
} 





