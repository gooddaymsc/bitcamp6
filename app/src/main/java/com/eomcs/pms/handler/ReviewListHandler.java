//package com.eomcs.pms.handler;
//
//import com.eomcs.pms.domain.Product;
//import com.eomcs.pms.domain.Review;
//import com.eomcs.util.Prompt;
//
//public class ReviewListHandler  {
//
//  public void list(int reviewNumber, ProductPrompt productPrompt) {
//    Product product = productPrompt.findByProduct(Prompt.inputString("상품명 : "));
//
//    if (product == null) {
//      System.out.println("입력하신 상품이 없습니다.");
//      return;
//    }
//
//    System.out.println("[상품 리뷰]");
//
//    Review review = new Review();
//
//    for(Review review : reviewList) {
//      review.getComment();
//      review.getScore();
//      review.getReviewer();
//      review.getReviewerNum();
//      review.getRegisteredDate();
//    }
//  }
//}
//
