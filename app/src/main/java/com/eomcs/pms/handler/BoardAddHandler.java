package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {

  public BoardAddHandler(List<Board> boardList) {
    super(boardList);

    Board board = new Board();

    board.setTitle("11");
    board.setContent("11");
    board.setWriter("11");
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setViews(11);
    board.setTag("11");
    board.setLikes(11);

    board = new Board();

    board.setTitle("22");
    board.setContent("22");
    board.setWriter("22");
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setViews(22);
    board.setTag("22");
    board.setLikes(22);

    board = new Board();

    board.setTitle("33");
    board.setContent("33");
    board.setWriter("33");
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setViews(33);
    board.setTag("33");
    board.setLikes(33);

    board = new Board();

    board.setTitle("44");
    board.setContent("44");
    board.setWriter("44");
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setViews(44);
    board.setTag("44");
    board.setLikes(44);

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






