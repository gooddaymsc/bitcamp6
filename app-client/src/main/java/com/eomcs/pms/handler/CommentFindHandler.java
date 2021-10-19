package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentFindHandler implements Command {

  BoardDao boardDao;

  public CommentFindHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[내가 남긴 댓글 목록]\n");
      System.out.printf("%-3s\t%-10s\t%-15s\t%-6s\n",
          "번호","제목","내가남긴댓글","등록일");
      System.out.println("--------------------------------------------------------------------------");
      List<Integer> boardNumList = new ArrayList<>();
      List<Board> boardMyList = new ArrayList<>();

      Collection<Board> boardList = boardDao.findAll();

      for (Board board : boardList) {
        for (Comment comment : board.getCommentList()) {
          if (comment.getWriter().getId().equals(ClientApp.getLoginUser().getId())) {
            System.out.printf("%-3d\t%-10s\t%-15s\t%-6s\n", 
                board.getBoardNumber(),
                board.getTitle(),
                comment.getContent(),
                comment.getRegistrationDate());
            boardNumList.add(board.getBoardNumber());
            boardMyList.add(board);
          }
        }
      }
      if (boardNumList.size()==0) {
        System.out.println("남긴 댓글이 없습니다.\n");
        return;
      }
      request.setAttribute("boardNumList", boardNumList);
      request.setAttribute("boardMyList", boardMyList);

      System.out.println("\n 상세보기(R) / 검색(1) / 이전(0)");
      while (true) {
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "0" : return;
          case "r" : 
          case "R" : request.getRequestDispatcher("/board/detail2").forward(request); continue Loop;
          case "1" : request.getRequestDispatcher("/board/search2").forward(request); continue Loop;
          default : System.out.println("잘못입력하셨습니다."); continue;
        }
      }
    }
  }
}
