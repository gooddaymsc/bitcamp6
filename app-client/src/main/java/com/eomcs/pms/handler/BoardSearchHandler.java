package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardSearchHandler implements Command {
  BoardDao boardDao;

  public BoardSearchHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 검색] || 이전(0)");

    //1. 게시판리스트 출력

    Collection<Board> boardList = boardDao.findAll();

    String keyword = Prompt.inputString("검색어 : ");
    HashMap<String, String> params = new HashMap<>();
    params.put("keyword", keyword);

    System.out.println();

    if (keyword.equals("0")) { return; }
    List<Integer> boardNumList = new ArrayList<>();
    System.out.printf("%-3s\t%-15s\t%-15s\t%-6s\t%-6s\n",
        "번호", "제목", "내용", "태그", "등록일");
    System.out.println("--------------------------------------------------------------------------");
    while(true) {
      for (Board board : boardList) {
        if (!board.getTitle().contains(keyword) &&
            !board.getContent().contains(keyword) &&
            !board.getTag().contains(keyword)) {

          continue;
        }

        System.out.printf("%-3d\t%-15s\t%-15s\t%-6s\t%-6s\n", 
            board.getBoardNumber(), 
            board.getTitle(), 
            board.getContent(),
            board.getTag(),
            board.getRegistrationDate());
        boardNumList.add(board.getBoardNumber());
      }
      if (boardNumList.size()==0) {
        System.out.println("검색 결과가 없습니다.\n");
        return;
      }
      System.out.println();

      //      request.setAttribute("boardNumList", boardNumList);
      //      request.getRequestDispatcher("/board/detail2").forward(request);
      return;

    }
  }
}
