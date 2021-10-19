package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Comment;

public class CommentListHandler implements Command {
  BoardDao boardDao;
  public CommentListHandler(BoardDao boardDao) {
    this.boardDao = boardDao;

  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    int boardNo = (Integer) request.getAttribute("no");
    Collection<Comment> commentList = boardDao.findAll(boardNo);

    System.out.printf("\n%-6s\t%-15s\t%-6s\n",
        "아이디", "내용", "등록일");
    System.out.println("--------------------------------------------------------------");
    if (commentList == null) {
      System.out.println("등록된 댓글이 없습니다.");
      return;
    }

    for (Comment comment : commentList) {
      System.out.printf("%-6s\t%-15s\t%-6s\n", 
          comment.getWriter().getId(),
          comment.getContent(),
          comment.getRegistrationDate());
    }
  }
}






