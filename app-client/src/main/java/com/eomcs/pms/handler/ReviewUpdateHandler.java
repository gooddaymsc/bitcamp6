package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewUpdateHandler implements Command {

  ReviewDao reviewDao;
  ProductDao productDao;
  SqlSession sqlSession;

  public ReviewUpdateHandler (ReviewDao reviewDao, ProductDao productDao, SqlSession sqlSession) {
    this.reviewDao = reviewDao;
    this.productDao = productDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[Reviews 변경]");

    Review review = reviewDao.reviewIs((Integer)request.getAttribute("productNumber"), ClientApp.getLoginUser().getId());

    if(review == null) {
      System.out.println("작성하신 리뷰가 없습니다.");
    }

    float scores = ProductValidation.checkNum("맛은 어떠셨나요?(1점-5점):");
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", review.getComment()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      review.setScore(scores);
      review.setComment(content);
      reviewDao.update(review);
      sqlSession.commit();
      System.out.println("리뷰 변경 완료\n");
      return;
    }

    System.out.println("리뷰 변경 취소\n");
    return;
  }
} 





