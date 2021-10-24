package com.eomcs.pms.handler;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;

public class LikeHandler implements Command {

  BoardDao boardDao;
  SqlSession sqlSession;

  public LikeHandler(BoardDao boardDao, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {

    Board board = boardDao.findByNo((Integer) request.getAttribute("no"));
    String nowLoginId = ClientApp.getLoginUser().getId();
    Board board2 = new Board();
    board2.setRegistrationDate(new Date(System.currentTimeMillis()));
    if (board.getLikeMember() == ClientApp.getLoginUser().getNumber()) {
      System.out.println("좋아요를 취소합니다.\n");
      boardDao.likeDelete(nowLoginId, board.getBoardNumber());
      board.setLikeMember(0);
      board.setLikes(board.getLikes() - 1);
      boardDao.update2(board);
      sqlSession.commit();
    } else {
      System.out.println("좋아요를 눌렀습니다.\n");
      boardDao.like(nowLoginId, board.getBoardNumber(), board2.getRegistrationDate());
      board.setLikeMember(ClientApp.getLoginUser().getNumber());
      board.setLikes(board.getLikes() + 1);
      boardDao.update2(board);
      sqlSession.commit();
    }    
    return;
  }
}
