package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;

public class LikeHandler implements Command {

  BoardDao boardDao;

  public LikeHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {

    Board board = boardDao.findByNo((Integer) request.getAttribute("no"));
    if (board.getLikeMember().contains(ClientApp.getLoginUser().getNumber())) {
      System.out.println("좋아요를 취소합니다.\n");
      boardDao.like(board, false);
    } else {
      System.out.println("좋아요를 눌렀습니다.\n");
      boardDao.like(board, true);
    }

  }
}
