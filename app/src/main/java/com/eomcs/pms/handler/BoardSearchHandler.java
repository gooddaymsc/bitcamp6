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
  public void execute() {
    System.out.println("\n[게시글 검색]");
    List<Integer> searchNo = new ArrayList<>();
    String input = Prompt.inputString("검색어 : ");
    while(true) {
      for (Board board : boardList) {
        if (!board.getTitle().contains(input) &&
            !board.getContent().contains(input) &&
            !board.getTag().contains(input)) {
          continue;
        }
        System.out.printf("%d, %s, %s, \"%s\", %s, %s\n", 
            board.getBoardNumber(), 
            board.getTitle(), 
            board.getContent(),
            board.getTag(),
            board.getWriter(),
            board.getRegistrationDate());
        searchNo.add(board.getBoardNumber());
      }

      int boardDetail = Prompt.inputInt("\n(뒤로가기 : 0) \n게시글 번호선택 > ");
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
        System.out.println("[게시글 좋아요 누르기]");

        String inputLike = Prompt.inputString("좋아요를 누르시겠습니까?(y/N) ");
        if (inputLike.equalsIgnoreCase("y")) {
          System.out.println("좋아요를 눌렀습니다.");
          board.setLikes(board.getLikes() + 1);
          System.out.printf("현재 좋아요 수: %d\n", board.getLikes());
          return;
        }
        System.out.println("게시글 좋아요를 취소하였습니다.");
        return;
      }
    }
  }
}
