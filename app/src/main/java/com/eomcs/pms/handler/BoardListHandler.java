package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Board;

public class BoardListHandler extends AbstractBoardHandler {

  public BoardListHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 목록]");

    Board[] boards = new Board[boardList.size()];

    boardList.toArray(boards);
    System.out.printf("%-3s\t%-6s\t%-6s\t%-6s\t%-3s\t%-3s\t%-6s\n",
        "번호", "제목", "태그", "작성자", "조회수","좋아요", "등록일");
    System.out.println("--------------------------------------------------------------------------");

    for (Board board : boards) {
      System.out.printf("%-3d\t%-6s\t%-6s\t%-6s\t%-3d\t%-3d\t%-6s\n", 
          board.getBoardNumber(), 
          board.getTitle(), 
          board.getTag(),
          board.getWriter(),
          board.getViews(), 
          board.getLikes(),
          board.getRegistrationDate());
    }
    request.getRequestDispatcher("/board/detail").forward(request);
  }
}

