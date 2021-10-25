package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentAddHandler implements Command {
  CommentDao commentDao;
  MemberDao memberDao;
  SqlSession sqlSession;
  public CommentAddHandler(CommentDao commentDao, MemberDao memberDao, SqlSession sqlSession) {
    this.commentDao = commentDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;

  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 달기]");
    Comment comment = new Comment();
    int boardNumber = (int) request.getAttribute("no");

    comment.setBoardNumber(boardNumber);
    comment.setContent(Prompt.inputString("내용 : "));
    comment.setWriter(ClientApp.getLoginUser());

    commentDao.insert(comment);
    sqlSession.commit();

    //    memberDao.changeCommentUpdate(board.getWriter(), true);
    System.out.println("댓글 등록 완료\n");
  }
}






