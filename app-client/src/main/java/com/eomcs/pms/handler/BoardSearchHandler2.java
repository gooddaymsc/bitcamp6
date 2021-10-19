package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardSearchHandler2 implements Command {

  BoardDao boardDao;

  public BoardSearchHandler2(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 검색] || 이전(0)");
    Loop : while(true) {
      String input = Prompt.inputString("검색어 : ");
      System.out.println();
      if (input.equals("0")) { return; }
      List<Integer> boardNumList = new ArrayList<>();

      System.out.printf("%-3s\t%-15s\t%-15s\t%-6s\t%-6s\n",
          "번호", "제목", "내용", "태그", "등록일");
      System.out.println("--------------------------------------------------------------------------");
      while(true) {
        for (Board board : (List<Board>) request.getAttribute("boardMyList")) {
          if (!board.getTitle().contains(input) &&
              !board.getContent().contains(input) 
              //              &&
              //              !board.getTag().contains(input)
              ) {

            continue;
          }

          System.out.printf("%-3d\t%-15s\t%-15s\t%-6s\n", 
              board.getBoardNumber(), 
              board.getTitle(), 
              board.getContent(),
              //              board.getTag(),
              board.getRegistrationDate());
          boardNumList.add(board.getBoardNumber());
        }
        if (boardNumList.size()==0) {
          System.out.println("검색 결과가 없습니다.\n");
          continue Loop;
        } 

        System.out.println();
        request.setAttribute("boardNumList", boardNumList);
        request.getRequestDispatcher("/board/detail2").forward(request);
        return;
      }
    }
  }
}
