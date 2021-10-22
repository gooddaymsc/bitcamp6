package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardDeleteHandler implements Command {

  BoardDao boardDao;
  SqlSession sqlSession;

  public BoardDeleteHandler(BoardDao boardDao, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[게시글 삭제]");
    int no = (Integer) request.getAttribute("no");
    Board board = boardDao.findByNo(no);

    if (!((board.getWriter().getNumber() == ClientApp.getLoginUser().getNumber()) ||
        (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN))) {
      System.out.println("작성자가 아니므로 삭제할 수 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      boardDao.delete(no);
      sqlSession.commit();
      System.out.println("게시글을 삭제하였습니다.\n");
      return;
    }

    System.out.println("게시글 삭제를 취소하였습니다.\n");
    return;
  }

}






