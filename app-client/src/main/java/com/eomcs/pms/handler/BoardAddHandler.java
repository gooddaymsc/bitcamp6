package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddHandler implements Command {

  BoardDao boardDao;

  public BoardAddHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[새 게시글]");


    Board board = new Board();
    board.setTitle(Prompt.inputString("제목 : "));
    board.setContent(Prompt.inputString("내용 : "));
    board.setWriter(ClientApp.getLoginUser());
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setTag(Prompt.inputString("태그 : "));
    // Numbering은 마지막에
    //    board.setBoardNumber(totalNumberList.get(App.BOARD_NUMBER_INDEX));
    //    totalNumberList.set(App.BOARD_NUMBER_INDEX, board.getBoardNumber()+1);

    boardDao.insert(board);

    System.out.println("게시글 등록을 완료하였습니다.\n");
  }
}






