package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardDetailHandler extends AbstractBoardHandler {

  public BoardDetailHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute() {
    System.out.println("[게시글 상세보기]");
    int no = Prompt.inputInt("게시글 번호 : ");

    Board board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목 : %s\n", board.getTitle());
    System.out.printf("내용 : %s\n", board.getContent());
    System.out.printf("작성자 : %s\n", board.getWriter());
    System.out.printf("등록일 : %s\n", board.getRegistrationDate());

    board.setViews(board.getViews() + 1);
    System.out.printf("조회수 : %d\n", board.getViews());
    System.out.printf("좋아요 수 : %d\n", board.getLikes());
    System.out.printf("태그 : %s\n", board.getTag());
    System.out.println("");
    System.out.println("[게시글 좋아요 누르기]");

    String input = Prompt.inputString("좋아요를 누르시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      System.out.println("좋아요를 눌렀습니다.");
      board.setLikes(board.getLikes() + 1);
      System.out.printf("현재 좋아요 수: %d\n", board.getLikes());
      return;
    }
    System.out.println("게시글 좋아요를 취소하였습니다.");
    return;

    //    if ( == 1) { 
    //      Privacy[] list = memberList.toArray(new Privacy[0]);
    //      for (Privacy member : list) {                        // 회원(구매자) 목록
    //        System.out.printf("좋아요 누른 사람: %s\n", member.getId());
    //      }
    //    }
  }
}






