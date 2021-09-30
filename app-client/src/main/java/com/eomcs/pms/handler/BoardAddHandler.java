package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.Collection;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Board;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BoardAddHandler implements Command {
  Collection<Board> productList ;
  RequestAgent requestAgent;

  public BoardAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[새 게시글]");


    Board board = new Board();
    board.setTitle(Prompt.inputString("제목 : "));
    board.setContent(Prompt.inputString("내용 : "));
    board.setWriter(ClientApp.getLoginUser().getId());
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setTag(Prompt.inputString("태그 : "));
    // Numbering은 마지막에
    //    board.setBoardNumber(totalNumberList.get(App.BOARD_NUMBER_INDEX));
    //    totalNumberList.set(App.BOARD_NUMBER_INDEX, board.getBoardNumber()+1);
    requestAgent.request("board.insert", board);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("게시글 등록을 완료하였습니다.\n");
      return;
    }
    System.out.println("게시글 등록 실패!");
  }
}






