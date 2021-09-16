//package com.eomcs.pms.handler;
//
//import java.sql.Date;
//import java.util.List;
//import com.eomcs.pms.App;
//import com.eomcs.pms.domain.Board;
//import com.eomcs.pms.domain.Review;
//import com.eomcs.util.Prompt;
//
//public class ReviewAddHandler extends AbstractReviewHandler {
//
//  List<Review> reviewList;
//  public ReviewAddHandler(List<Review> reviewList) {
//    this.reviewList = reviewList;
//  }
//
//  public static int reviewNumber = 1;
//  @Override
//  public void execute() {
//
//    System.out.println("[새 리뷰]");
//    Board board = new Board();
//
//    findByProduct
//
//
//    board.setBoardNumber(boardNumber++);
//    board.setTitle(Prompt.inputString("제목 : "));
//    board.setContent(Prompt.inputString("내용 : "));
//    board.setWriter(App.getLoginUser().getId());
//    board.setRegistrationDate(new Date(System.currentTimeMillis()));
//    board.setTag(Prompt.inputString("태그 : "));
//
//    boardList.add(board);
//    System.out.println("게시글 등록을 완료하였습니다.");
//  }
//}
//
//
//
//
//
//
