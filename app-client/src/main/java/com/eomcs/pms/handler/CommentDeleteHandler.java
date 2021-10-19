package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentDeleteHandler implements Command {
  BoardDao boardDao;
  public CommentDeleteHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[댓글 삭제]\n");
    int commentNo = Prompt.inputInt("삭제할 댓글 번호 : ");

    Comment comment = boardDao.findCommentByNo(commentNo);

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
      boardDao.delete(comment);
      System.out.println("댓글 삭제 완료\n");
      return;
    }
    System.out.println("댓글 삭제 취소\n");
    return;
  }

}






