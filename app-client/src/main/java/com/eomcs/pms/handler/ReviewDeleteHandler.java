package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewDeleteHandler implements Command {

  ReviewDao reviewDao;
  SqlSession sqlSession;

  public ReviewDeleteHandler (ReviewDao reviewDao, SqlSession sqlSession) {
    this.reviewDao = reviewDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[Reviews 삭제]");

    Review review = reviewDao.reviewIs((Integer)request.getAttribute("productNumber"), ClientApp.getLoginUser().getId());

    if (review == null) {
      System.out.println("해당 상품에 작성하신 리뷰가 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      reviewDao.delete(review.getNo());
      sqlSession.commit();
      System.out.println("리뷰 삭제 완료\n");
      return;
    }
    System.out.println("리뷰 삭제 취소\n");
    return;
  }
} 





