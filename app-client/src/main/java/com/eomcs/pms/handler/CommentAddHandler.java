package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.MemberDao;
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

    comment.setBoardNumber(boardNumber);
    comment.setContent(Prompt.inputString("내용 : "));
    comment.setWriter(ClientApp.getLoginUser());

    boardDao.insert(comment);
    //    memberDao.changeCommentUpdate(board.getWriter(), true);
    System.out.println("댓글 등록 완료\n");
  }
}






