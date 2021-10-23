package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;

public class CommentListHandler implements Command {
  CommentDao commentDao;
  public CommentListHandler(CommentDao commentDao) {
    this.commentDao = commentDao;

  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    int boardNo = (Integer) request.getAttribute("no");
    Collection<Comment> commentList = commentDao.findAll(boardNo);

    System.out.printf("\n%-3s\t%-6s\t%-15s\t%-6s\n",
        "번호", "아이디", "내용", "등록일");
    System.out.println("--------------------------------------------------------------");
    if (commentList == null) {
      System.out.println("등록된 댓글이 없습니다.");
      return;
    }

    for (Comment comment : commentList) {
      System.out.printf("%-3d\t%-6s\t%-15s\t%-6s\n", 
          comment.getCommentNumber(),
          comment.getWriter().getId(),
          comment.getContent(),
          comment.getRegistrationDate());
    }
  }
}






