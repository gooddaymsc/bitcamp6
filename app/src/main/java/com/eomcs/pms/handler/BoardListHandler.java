package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardListHandler extends AbstractBoardHandler {

  public BoardListHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.printf("[게시글 목록]");
      if (App.getLoginUser().getAuthority()!=Menu.ACCESS_LOGOUT) {
        System.out.println("|| 게시글 등록(A) / 이전(0)\n");
      } else {
        System.out.println("|| 이전(0)\n");
      }
      Board[] boards = new Board[boardList.size()];

      boardList.toArray(boards);
      System.out.printf("%-3s\t%-15s\t%-15s\t%-6s\t%-3s\t%-3s\t%-6s\n",
          "번호", "제목", "태그", "작성자", "조회수","좋아요", "등록일");
      System.out.println("--------------------------------------------------------------------------");

      for (Board board : boards) {
        System.out.printf("%-3d\t%-15s\t%-15s\t%-6s\t%-3d\t%-3d\t%-6s\n", 
            board.getBoardNumber(), 
            board.getTitle(), 
            board.getTag(),
            board.getWriter(),
            board.getViews(), 
            board.getLikes(),
            board.getRegistrationDate());
      }
      if (App.getLoginUser().getAuthority()==Menu.ACCESS_LOGOUT) {
        System.out.println("\n 상세보기(R) / 검색(1)");
        while (true) {
          String choose = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose) {
            case "0" : return;
            case "a" :
            case "A" : System.out.println("로그인 후 가능합니다.\n"); return;
            case "r" :
            case "R" : request.getRequestDispatcher("/board/detail").forward(request); continue Loop;
            case "1" : request.getRequestDispatcher("/board/search").forward(request); continue Loop;
            default : System.out.println("잘못입력하셨습니다."); continue;
          }
        }
      } else {
        System.out.println("\n 상세보기(R) / 검색(1)");
        while (true) {
          String choose = Prompt.inputString("선택 > ");
          System.out.println();
          switch (choose) {
            case "0" : return;
            case "r" :
            case "R" : request.getRequestDispatcher("/board/detail").forward(request); continue Loop;
            case "1" : request.getRequestDispatcher("/board/search").forward(request); continue Loop;
            case "a" :
            case "A" : request.getRequestDispatcher("/board/add").forward(request); continue Loop;
            default : System.out.println("잘못입력하셨습니다."); continue;
          }
        }
      }
    }
  }
}
