package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewUpdateHandler extends AbstractReviewHandler {

  ProductPrompt productPrompt;
  List<Product> productList;


  public ReviewUpdateHandler (ProductPrompt productPrompt, List<Product> productList) {
    this.productPrompt = productPrompt;
    this.productList = productList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("\n[Reviews 변경]");
    Product product = productPrompt.findByProduct(Prompt.inputString("상품명 : "));
    Review review = findReviewById(product, App.getLoginUser().getId());

    if (review == null) {
      System.out.println("해당 상품에 작성하신 리뷰가 없습니다.");
      return;
    }

    if (!review.getId().equals(App.getLoginUser().getId())) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.\n");
      return;
    }

    float scores = checkNum("맛은 어떠셨나요?(1점-5점):");
    review.setScore(scores);
    product.setRate((product.getRate()*product.getReviewerNum()+scores)/(product.getReviewerNum()+1)); //상품 총점
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", review.getComment()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      review.setComment(content);
      System.out.println("리뷰 변경 완료\n");
      return;
    }

    System.out.println("리뷰 변경 취소\n");
    return;
  }
} 





