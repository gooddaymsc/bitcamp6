package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardLikeHandler extends AbstractBoardHandler {

  public BoardLikeHandler(List<Board> boardList) {
    super(boardList);
  }

  public void execute() {
    System.out.println("[게시글 좋아요 누르기]");
    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    String input = Prompt.inputString("좋아요를 누르시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      System.out.println("좋아요를 눌렀습니다.");
      board.setLikes(board.getLikes() + 1);
      System.out.printf("현재 좋아요 수: %d입니다.\n", board.getLikes());
      return;
    } 
    System.out.println("게시글 좋아요를 취소하였습니다.");
    return;

    //    if ( == 1) { 
    //      Privacy[] list = memberList.toArray(new Privacy[0]);
    //      for (Privacy member : list) {                        // 회원(구매자) 목록
    //        System.out.printf("좋아요 누른 사람: %s입니다.\n", member.getId());
    //      }
    //    }
  }
}






