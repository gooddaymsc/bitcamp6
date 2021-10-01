package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Board;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BoardFindHandler implements Command {

  RequestAgent requestAgent;

  public BoardFindHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[내가 남긴 게시글 목록]\n");
      System.out.printf("%-3s\t%-10s\t%-3s\t%-6s\n",
          "번호","제목","댓글수","등록일");
      System.out.println("--------------------------------------------------------------------------");

      List<Integer> boardNumList = new ArrayList<>();
      List<Board> boardMyList = new ArrayList<>();

      requestAgent.request("board.selectList", null);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("목록조회실패!");
        return;
      }

      Collection<Board> boardList = requestAgent.getObjects(Board.class);

      for (Board board : boardList) {
        if (board.getWriter().equals(ClientApp.getLoginUser().getId())) {
          System.out.printf("%-3d\t%-10s\t%-6s\n", /*"%-3d\t%-10s\t%-3d\t%-6s\n",*/ 
              board.getBoardNumber(),
              board.getTitle(),
              //              board.getCommentList().size(),
              board.getRegistrationDate());
          boardNumList.add(board.getBoardNumber());
          boardMyList.add(board);
        }
      }
      request.setAttribute("boardNumList", boardNumList);
      request.setAttribute("boardMyList", boardMyList);
      if (boardNumList.size()==0) {
        System.out.println("남긴 게시글이 없습니다.\n");
        return;
      }
      System.out.println("\n상세보기(1) / 검색(2) / 이전(0)");
      while (true) {
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "0" : return;
          case "1" : request.getRequestDispatcher("/board/detail2").forward(request); continue Loop;
          case "2" : request.getRequestDispatcher("/board/search2").forward(request); continue Loop;
          default : System.out.println("잘못입력하셨습니다."); continue;
        }
      }
    }
  }
}