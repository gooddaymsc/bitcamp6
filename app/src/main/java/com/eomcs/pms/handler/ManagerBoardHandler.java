package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class ManagerBoardHandler {
  //sc 작성중
  List<Board> boardList;

  public ManagerBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  public void delete(int auth) {
    if (auth != 3) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }
    System.out.println("[게시글 삭제]");
    int no = Prompt.inputInt("게시글번호를 입력해주세요: ");

    Board board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제시키겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    boardList.remove(board);
    System.out.println("게시글을 삭제시켰습니다.");

  }

  private Board findByNo(int no) { 
    Board[] arr = boardList.toArray(new Board[0]);
    for (Board board : arr) {
      if (board.getNumber() == no) {
        return board;
      }
    }
    return null;
  }

}
