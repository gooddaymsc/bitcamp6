package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentDeleteHandler implements Command {
  CommentDao commentDao;
  SqlSession sqlSession;
  public CommentDeleteHandler(CommentDao commentDao, SqlSession sqlSession) {
    this.commentDao = commentDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 삭제]\n");
    int commentNo = Prompt.inputInt("삭제할 댓글 번호 : ");

    Comment comment = commentDao.findByNo(commentNo);

    if (comment == null) {
      System.out.println("해당 번호의 댓글 없습니다.\n");
      return;
    }

    if (!((comment.getWriter().getId().equals(ClientApp.getLoginUser().getId())) ||
        (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN))) {
      System.out.println("작성자가 아니므로 삭제할 수 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      commentDao.delete(comment);
      sqlSession.commit();
      System.out.println("댓글 삭제 완료\n");
      return;
    }
    System.out.println("댓글 삭제 취소\n");
    return;
  }

}






