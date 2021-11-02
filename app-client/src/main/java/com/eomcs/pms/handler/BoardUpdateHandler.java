package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.BoardTag;
import com.eomcs.util.Prompt;

public class BoardUpdateHandler implements Command {

  BoardDao boardDao;
  SqlSession sqlSession;

  public BoardUpdateHandler(BoardDao boardDao, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[게시글 변경]");

    int no = (int) request.getAttribute("no");

    Board board = boardDao.findByNo(no);

    if (!board.getWriter().getId().equals(ClientApp.getLoginUser().getId())) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.\n");
      return;
    }
    String title = Prompt.inputString(String.format("제목(변경 전 : %s) : ", board.getTitle()));
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", board.getContent()));
    BoardTag boardTag = new BoardTag();
    boardTag.setTag(Prompt.inputString(String.format("태그(변경 전 : %s) : ", board.getBoardTag().getTag())));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      board.setTitle(title);
      board.setContent(content);
      board.setBoardTag(boardTag);

      boardDao.update(board);
      boardDao.update2(board);
      sqlSession.commit();
      System.out.println("게시글을 변경하였습니다.\n");
      return;
    }
    System.out.println("게시글 변경을 취소하였습니다.\n");
    return;
  }

}






