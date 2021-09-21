package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewAddHandler extends AbstractReviewHandler {

  ProductPrompt productPrompt;
  public ReviewAddHandler (ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[Reviews 작성]");
    Review review = new Review();
    Product product = (Product) request.getAttribute("상품");

    if (reviewIs(product)) {
      System.out.println("이미 등록한 리뷰가 있습니다.\n");
      return;
    }


    if(App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER) {
      float scores = checkNum("맛은 어떠셨나요?(1점-5점):");
      review.setScore(scores); //개인별 평점
      product.setRate((product.getRate()*product.getReviewerNum()+scores)/(product.getReviewerNum()+1)); //상품 총점
      product.setReviewerNum(product.getReviewerNum()+1);

      review.setComment(Prompt.inputString("한줄평을 등록해주세요:"));
      System.out.println("상품평 등록을 완료하였습니다.");
      review.setNo(review.getNo()+1);
      review.setRegisteredDate(new Date(System.currentTimeMillis()));
      review.setId(App.getLoginUser().getId());
      product.getReviewList().add(review);
      return;
    } else {
      System.out.println("구매자만 등록 가능합니다.");
    } 
  }
} 





