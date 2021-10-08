package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentAddHandler implements Command {
  BoardDao boardDao;
  MemberDao memberDao;
  public CommentAddHandler(BoardDao boardDao, MemberDao memberDao) {
    this.boardDao = boardDao;
    this.memberDao = memberDao;

  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 달기]");
    Comment comment = new Comment();
    int boardNumber = (int) request.getAttribute("no");
    Board board = boardDao.findByNo(boardNumber);

    comment.setBoardNumber(boardNumber);
    comment.setCommentNumber(board.getTotalCommentNumber()); 
    comment.setContent(Prompt.inputString("내용 : "));
    comment.setId(ClientApp.getLoginUser().getId());
    comment.setRegistrationDate(new Date(System.currentTimeMillis()));

    boardDao.insert(comment);
    memberDao.changeCommentUpdate(board.getWriter(), true);
    System.out.println("댓글 등록 완료\n");
  }
}






