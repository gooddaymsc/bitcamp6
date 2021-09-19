package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardSearchHandler extends AbstractBoardHandler {

  public BoardSearchHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("\n[게시글 검색] || 0. 이전");

    List<Integer> searchNo = new ArrayList<>();
    String input = Prompt.inputString("검색어 : ");
    if (input.equals("0")) { return; }
    while(true) {
      boolean resultSearch = false;
      for (Board board : boardList) {
        if (!board.getTitle().contains(input) &&
            !board.getContent().contains(input) &&
            !board.getTag().contains(input)) {

          continue;
        }
        resultSearch = true;
        System.out.printf("%-3s\t%-6s\t%-15s\t%-6s\t%-6s\n",
            "번호", "제목", "내용", "태그", "등록일");
        System.out.println("--------------------------------------------------------------------------");

        System.out.printf("%-3d\t%-6s\t%-15s\t%-6s\t%-6s\n", 
            board.getBoardNumber(), 
            board.getTitle(), 
            board.getContent(),
            board.getTag(),
            board.getRegistrationDate());
        searchNo.add(board.getBoardNumber());
      }
      if (!resultSearch) {
        System.out.println("검색 결과가 없습니다.");
        return;
      }

      int boardDetail = Prompt.inputInt("\n게시글 번호선택 > ");
      if (boardDetail == 0) {
        return;
      } else {

        if (!searchNo.contains(boardDetail)) {
          System.out.println("검색된 게시글 번호가 아닙니다.");
          return;
        }

        Board board = findByNo(boardDetail);

        System.out.printf("\n제목 : %s\n", board.getTitle());
        System.out.printf("내용 : %s\n", board.getContent());
        System.out.printf("작성자 : %s\n", board.getWriter());
        System.out.printf("등록일 : %s\n", board.getRegistrationDate());

        board.setViews(board.getViews() + 1);
        System.out.printf("조회수 : %d\n", board.getViews());
        System.out.printf("좋아요 수 : %d\n", board.getLikes());
        System.out.printf("태그 : \"%s\"\n", board.getTag());
        System.out.println("");

      }
    }
  }
}
