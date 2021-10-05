package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewUpdateHandler implements Command {

  ProductDao productDao;
  ProductPrompt productPrompt;

  public ReviewUpdateHandler (ProductDao productDao, ProductPrompt productPrompt) {
    this.productDao = productDao;
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[Reviews 변경]");
    Product product =  productDao.findByNo((Integer)request.getAttribute("productNumber"));
    Review review = productDao.findReviewById(product, ClientApp.getLoginUser().getId());

    if (!review.getId().equals(ClientApp.getLoginUser().getId())) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.\n");
      return;
    }

    float scores = productPrompt.checkNum("맛은 어떠셨나요?(1점-5점):");
    review.setScore(scores);
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", review.getComment()));
    review.setComment(content);
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      productDao.updateReview(review);
      System.out.println("리뷰 변경 완료\n");
      return;
    }

    System.out.println("리뷰 변경 취소\n");
    return;
  }
} 





