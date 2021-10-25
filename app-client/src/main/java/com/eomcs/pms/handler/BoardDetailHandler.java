package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardDetailHandler implements Command {

  BoardDao boardDao;
  MemberDao memberDao;
  SqlSession sqlSession;
  public BoardDetailHandler(BoardDao boardDao, MemberDao memberDao, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    Board board = boardDao.findByNo(Prompt.inputInt("게시글 번호 : "));

    Loop : while(true) {
      board = boardDao.findByNo(board.getBoardNumber());
      System.out.printf("[게시글 상세보기]");

      if (ClientApp.getLoginUser().getAuthority()!=Menu.ACCESS_LOGOUT) {
        System.out.println("|| 게시글 변경(U) / 게시글 삭제(D) / 이전(0)\n");
      } else {
        System.out.println("|| 이전(0)\n");
      }

      if (ClientApp.getLoginUser().isCommentUpdate() && 
          board.getWriter().getId().equals(ClientApp.getLoginUser().getId())) {
        //        memberDao.changeCommentUpdate(board.getWriter(), false);
      }

      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("내용 : %s\n", board.getContent());
      System.out.printf("작성자 : %s\n", board.getWriter().getId());
      System.out.printf("등록일 : %s\n", board.getRegistrationDate());

      board.setViews(board.getViews() + 1);
      System.out.printf("조회수 : %d\n", board.getViews());

      board.setLikes(boardDao.findLikeList(board.getBoardNumber()).size());
      System.out.printf("좋아요 수 : %d\n", board.getLikes());
      System.out.printf("태그 : %s\n", board.getTag());

      boardDao.updateCount(board.getBoardNumber());
      sqlSession.commit();

      request.setAttribute("no", board.getBoardNumber());
      request.getRequestDispatcher("/comment/list").forward(request);

      System.out.println("\n< 좋아요(1) / 댓글등록(2) / 댓글수정(3) / 댓글삭제(4) >");
      if (ClientApp.getLoginUser().getAuthority()==Menu.ACCESS_LOGOUT) {
        System.out.println("로그인 후 가능합니다.\n");
        return;
      }
      while(true) {
        String choose2 = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose2) {
          case "0" : return;
          case "1" : request.getRequestDispatcher("/comment/like").forward(request); continue Loop;
          case "2" : request.getRequestDispatcher("/comment/add").forward(request); continue Loop;
          case "3" : request.getRequestDispatcher("/comment/update").forward(request); continue Loop;
          case "4" : request.getRequestDispatcher("/comment/delete").forward(request); continue Loop;
          case "u": 
          case "U": request.getRequestDispatcher("/board/update").forward(request); return;
          case "d": 
          case "D": request.getRequestDispatcher("/board/delete").forward(request); return;
          default : System.out.println("잘못입력하셨습니다"); continue;
        }
      }
    }
  }
}







