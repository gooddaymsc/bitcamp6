package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {
  int boardNunmber = 1;
  public BoardAddHandler(List<Board> boardList) {
    super(boardList);

    Board testBoard = new Board();
    testBoard.setNumber(boardNunmber++);
    testBoard.setTitle("와인찾아요");
    testBoard.setContent("스파클링, 드라이");
    testBoard.setWriter("11");
    testBoard.setRegistrationDate(new Date(System.currentTimeMillis()));
    testBoard.setViews(11);
    testBoard.setTag("와인, 추천");
    testBoard.setLikes(11);

    boardList.add(testBoard);

    testBoard = new Board();
    testBoard.setNumber(boardNunmber++);
    testBoard.setTitle("양주 도수 높은순서");
    testBoard.setContent("헤네시, 블랙라벨, 잭다니엘");
    testBoard.setWriter("22");
    testBoard.setRegistrationDate(new Date(System.currentTimeMillis()));
    testBoard.setViews(22);
    testBoard.setTag("양주, 도수");
    testBoard.setLikes(22);

    boardList.add(testBoard);

    testBoard = new Board();
    testBoard.setNumber(boardNunmber++);
    testBoard.setTitle("숙취심한술");
    testBoard.setContent("와인이나 막걸리가 숙취가 심한거 같아요.");
    testBoard.setWriter("33");
    testBoard.setRegistrationDate(new Date(System.currentTimeMillis()));
    testBoard.setViews(33);
    testBoard.setTag("숙취, 와인, 막걸리");
    testBoard.setLikes(33);

    boardList.add(testBoard);

    testBoard = new Board();
    testBoard.setNumber(boardNunmber++);
    testBoard.setTitle("오늘 비가오네요");
    testBoard.setContent("비오는날에는 파전에 막걸리죠");
    testBoard.setWriter("44");
    testBoard.setRegistrationDate(new Date(System.currentTimeMillis()));
    testBoard.setViews(44);
    testBoard.setTag("비, 안주, 파전, 막걸리");
    testBoard.setLikes(44);

    boardList.add(testBoard);

  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
      System.out.println("권한이 없습니다.\n로그인해주세요...");
      return;
    }
    System.out.println("[새 게시글]");
    Board board = new Board();

    board.setNumber(boardNunmber++);
    board.setTitle(Prompt.inputString("제목 : "));
    board.setContent(Prompt.inputString("내용 : "));
    board.setWriter(App.getLoginUser().getId());
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setTag(Prompt.inputString("태그 : "));

    boardList.add(board);
    System.out.println("게시글 등록을 완료하였습니다.");
  }
}






