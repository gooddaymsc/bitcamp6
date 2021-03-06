package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentUpdateHandler implements Command {
  CommentDao commentDao;
  SqlSession sqlSession;
  public CommentUpdateHandler(CommentDao commentDao, SqlSession sqlSession) {
    this.commentDao = commentDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int commentNo = Prompt.inputInt("변경할 댓글 번호 : ");

    Comment comment = commentDao.findByNo(commentNo);

    if (comment == null) {
      System.out.println("해당 번호의 댓글이 없습니다.\n");
      return;
    }

    if (!comment.getWriter().getId().equals(ClientApp.getLoginUser().getId())) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.\n");
      return;
    }
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", comment.getContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {

      comment.setContent(content);
      commentDao.update(comment);
      sqlSession.commit();

      System.out.println("댓글 변경 완료\n");
      return;
    }

    System.out.println("댓글 변경 취소\n");
    return;
  }

}






