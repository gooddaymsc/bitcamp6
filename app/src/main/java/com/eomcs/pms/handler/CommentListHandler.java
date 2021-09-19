package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;

public class CommentListHandler extends AbstractCommentHandler {
  public CommentListHandler(BoardPrompt boardPrompt) {
    super(boardPrompt);
  }
  @Override
  public void execute(CommandRequest request) {
    int boardNumber = (int) request.getAttribute("no");
    Board board = boardPrompt.findBoardByNo(boardNumber);

    System.out.printf("\n%-3s\t%-6s\t%-15s\t%-6s\n",
        "No.", "아이디", "내용", "등록일");
    System.out.println("--------------------------------------------------------------");

    for (Comment comment : board.getCommentList()) {
      System.out.printf("%-3d\t%-6s\t%-15s\t%-6s\n", 
          comment.getCommentNumber(),
          comment.getId(),
          comment.getContent(),
          comment.getRegistrationDate());
    }
  }
}






