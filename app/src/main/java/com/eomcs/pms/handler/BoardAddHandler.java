package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {

  @Override
  public void execute(int i) {}

  public BoardAddHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("권한이 없습니다.\n로그인해주세요...");
      return;
    }
    System.out.println("[새 게시글]");
    Board board = new Board();

    board.setNumber(Prompt.inputInt("번호? "));
    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(Prompt.inputString("작성자? "));
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setTag(Prompt.inputString("태그? "));

    boardList.add(board);
  }
}






