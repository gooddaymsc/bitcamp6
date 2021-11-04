package com.eomcs.pms.handler;

import java.util.Collection;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.LikeMember;

public class LikeHandler implements Command {

  BoardDao boardDao;
  SqlSession sqlSession;

  public LikeHandler(BoardDao boardDao, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    int boardNo = (Integer) request.getAttribute("no");
    Board board = boardDao.findByNo(boardNo);
    int nowLoginNo = ClientApp.getLoginUser().getNumber();
    Collection<LikeMember> likeList = boardDao.findLikeList(boardNo);
    board.setLikeMember((List<LikeMember>) likeList);
    board.setLikes(likeList.size());
    int i=0;
    for (LikeMember lk : likeList) {
      if (lk.getNumber()==nowLoginNo) {
        System.out.println("좋아요를 취소합니다.\n");
        boardDao.likeDelete(nowLoginNo, boardNo);
        board.getLikeMember().remove(i);
        board.setLikes(board.getLikes() - 1);
        sqlSession.commit();
        return;
      }
      i++;
    }
    System.out.println("좋아요를 눌렀습니다.\n");

    boardDao.likeInsert(nowLoginNo, boardNo);
    LikeMember lm = new LikeMember();
    lm.setNumber(nowLoginNo);
    board.getLikeMember().add(lm);
    board.setLikes(board.getLikes() + 1);
    sqlSession.commit();
  }
}
