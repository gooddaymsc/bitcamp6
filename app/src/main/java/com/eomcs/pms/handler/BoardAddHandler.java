package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {

  public BoardAddHandler(List<Board> boardList) {
    super(boardList);

    Board testBoard = new Board();

    testBoard.setTitle("11");
    testBoard.setContent("11");
    testBoard.setWriter("11");
    testBoard.setRegistrationDate(new Date(System.currentTimeMillis()));
    testBoard.setViews(11);
    testBoard.setTag("11");
    testBoard.setLikes(11);

    boardList.add(testBoard);

    testBoard = new Board();
    testBoard.setTitle("22");
    testBoard.setContent("22");
    testBoard.setWriter("22");
    testBoard.setRegistrationDate(new Date(System.currentTimeMillis()));
    testBoard.setViews(22);
    testBoard.setTag("22");
    testBoard.setLikes(22);

    boardList.add(testBoard);

    testBoard = new Board();
    testBoard.setTitle("33");
    testBoard.setContent("33");
    testBoard.setWriter("33");
    testBoard.setRegistrationDate(new Date(System.currentTimeMillis()));
    testBoard.setViews(33);
    testBoard.setTag("33");
    testBoard.setLikes(33);

    boardList.add(testBoard);

    testBoard = new Board();
    testBoard.setTitle("44");
    testBoard.setContent("44");
    testBoard.setWriter("44");
    testBoard.setRegistrationDate(new Date(System.currentTimeMillis()));
    testBoard.setViews(44);
    testBoard.setTag("44");
    testBoard.setLikes(44);

    boardList.add(testBoard);

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






