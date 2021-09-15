package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentAddHandler {

  public static void add(int boardNumber, BoardPrompt boardPrompt) {

    System.out.println("\n[댓글 달기]");
    Comment comment = new Comment();
    Board board = boardPrompt.findBoardByNo(boardNumber);
    comment.setCommentNumber(board.getTotalCommentNumber()); 
    comment.setContent(Prompt.inputString("내용 : "));
    comment.setId(App.getLoginUser().getId());
    comment.setRegistrationDate(new Date(System.currentTimeMillis()));

    board.getCommentList().add(comment);
    board.setTotalCommentNumber(comment.getCommentNumber()+1);
    System.out.println("댓글 완료\n");
  }
}






