package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {
  public BoardAddHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute() {

    System.out.println("[새 게시글]");
    Board board = new Board();

    board.setNumber(boardList.size() +1);
    board.setTitle(Prompt.inputString("제목 : "));
    board.setContent(Prompt.inputString("내용 : "));
    board.setWriter(App.getLoginUser().getId());
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setTag(Prompt.inputString("태그 : "));

    boardList.add(board);
    System.out.println("게시글 등록을 완료하였습니다.");
  }
}






