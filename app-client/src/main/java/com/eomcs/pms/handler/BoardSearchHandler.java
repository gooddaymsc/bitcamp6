package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BoardSearchHandler implements Command {
  RequestAgent requestAgent;

  public BoardSearchHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 검색] || 이전(0)");

    requestAgent.request("board.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록조회실패!");
      return;
    }

    Collection<Board> boardList = requestAgent.getObjects(Board.class);

    String keyword = Prompt.inputString("검색어 : ");
    HashMap<String, String> params = new HashMap<>();
    params.put("keyword", keyword);

    System.out.println();
    //    requestAgent.request("board.search", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 검색어의 글이 없습니다.");
      return;
    }
    //    Collection<Board> searchedBoardList = requestAgent.getObjects(Board.class);

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
