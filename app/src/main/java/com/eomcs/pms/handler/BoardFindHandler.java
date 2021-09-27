package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardFindHandler implements Command {
  List<Board> boardList;
  BoardPrompt boardPrompt;
  MemberPrompt memberPrompt;
  public BoardFindHandler(List<Board> boardList, BoardPrompt boardPrompt, MemberPrompt memberPrompt) {
    this.boardList = boardList;
    this.boardPrompt = boardPrompt;
    this.memberPrompt = memberPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      System.out.println("[내가 남긴 게시글 목록]\n");
      System.out.printf("%-3s\t%-10s\t%-3s\t%-6s\n",
          "번호","제목","댓글수","등록일");
      System.out.println("--------------------------------------------------------------------------");
      List<Integer> boardNumList = new ArrayList<>();

      for (Board board : boardList) {
        if (board.getWriter().equals(App.getLoginUser().getId())) {
          System.out.printf("%-3d\t%-10s\t%-3d\t%-6s\n", 
              board.getBoardNumber(),
              board.getTitle(),
              board.getCommentList().size(),
              board.getRegistrationDate());
          boardNumList.add(board.getBoardNumber());
        }
      }
      request.setAttribute("boardNumList", boardNumList);
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
          case "2" : request.getRequestDispatcher("/board/search").forward(request); continue Loop;
          default : System.out.println("잘못입력하셨습니다."); continue;
        }
      }
    }
  }
}