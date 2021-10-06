package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardUpdateHandler implements Command {

  BoardDao boardDao;

  public BoardUpdateHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[게시글 변경]");

    int no = (int) request.getAttribute("no");

    Board board = boardDao.findByNo(no);

    if (!board.getWriter().equals(ClientApp.getLoginUser().getId())) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.\n");
      return;
    }
    String title = Prompt.inputString(String.format("제목(변경 전 : %s) : ", board.getTitle()));
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", board.getContent()));
    String tag = Prompt.inputString(String.format("태그(변경 전 : %s) : ", board.getTag()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      board.setTitle(title);
      board.setContent(content);
      board.setTag(tag);

      boardDao.update(board);
      System.out.println("게시글을 변경하였습니다.\n");
      return;
    }
    System.out.println("게시글 변경을 취소하였습니다.\n");
    return;
  }

}






