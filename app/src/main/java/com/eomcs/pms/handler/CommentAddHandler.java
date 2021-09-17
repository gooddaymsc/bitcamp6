package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentAddHandler implements Command {

  BoardPrompt boardPrompt;

  public CommentAddHandler(BoardPrompt boardPrompt) {
    this.boardPrompt = boardPrompt;

  }

  @Override
  public void execute(CommandRequest request) {

    System.out.println("\n[댓글 달기]");
    Comment comment = new Comment();
    int boardNumber = (int) request.getAttribute("no");
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






