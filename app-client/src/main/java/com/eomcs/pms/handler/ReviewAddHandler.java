package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public class ReviewAddHandler implements Command {

  ReviewDao reviewDao;
  ProductDao productDao;
  SqlSession sqlSession;

  public ReviewAddHandler (ReviewDao reviewDao, ProductDao productDao, SqlSession sqlSession) {
    this.reviewDao = reviewDao;
    this.productDao = productDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception  {
    System.out.println("[Reviews 작성]");
    Review review = new Review();
    Product product =  productDao.findByNo((Integer)request.getAttribute("productNumber"));

    if (reviewDao.reviewIs(product.getProductNumber(), ClientApp.getLoginUser().getId()) != null) {
      System.out.println("이미 등록한 리뷰가 있습니다.\n");
      return;
    }

    if(ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_BUYER) {
      float scores = ProductValidation.checkNum("맛은 어떠셨나요?(1점-5점):");
      review.setComment(Prompt.inputString("한줄평을 등록해주세요:"));
      // review.setPurchase(reviewDao.findPurchased(product.getProductName())); 
      review.setScore(scores); //개인별 평점
      product.setReviewerNum(product.getReviewerNum()+1);
      review.setProductNo(product.getProductNumber());
      review.setReviewProduct(product.getProductName());
      review.setMember(ClientApp.getLoginUser());

      reviewDao.insertReview(review);
      sqlSession.commit();

      System.out.println("상품평 등록을 완료하였습니다.\n");
      return;

    } else {
      System.out.println("구매자만 등록 가능합니다.\n");
    } 
  }
} 







