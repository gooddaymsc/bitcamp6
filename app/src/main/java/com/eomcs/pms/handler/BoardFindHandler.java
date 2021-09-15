package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;

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
  public void execute() {
    System.out.println("[내가 남긴 게시글 목록]");
    System.out.printf("%-3s\t%-10s\t%-3s\t%-6s\n",
        "번호","제목","댓글수","등록일");
    System.out.println("--------------------------------------------------------------------------");

    for (Board board : boardList) {
      if (board.getWriter().equals(App.getLoginUser().getId())) {
        System.out.printf("%-3d\t%-10s\t%-3d\t%-6s\n", 
            board.getBoardNumber(),
            board.getTitle(),
            board.getCommentList().size(),
            board.getRegistrationDate());
      }
    }
    System.out.println();
    BoardDetailHandler boardDetailHandler = new BoardDetailHandler(boardList, boardPrompt, memberPrompt);
    boardDetailHandler.execute();
  }
}