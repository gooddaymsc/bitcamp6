package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class ManagerBoardHandler {
  List<Board> boardList;

  public ManagerBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  public void list() {
    if (App.getLoginUser().getAuthority() != 3) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }
    System.out.println("[게시글 목록]");

    Board[] boards = new Board[boardList.size()];

    boardList.toArray(boards);

    for (Board board : boards) {
      System.out.printf("%d, %s, %s, %s, %d, %s, %d\n", 
          board.getNumber(), 
          board.getTitle(), 
          board.getWriter(),
          board.getRegistrationDate(),
          board.getViews(), 
          board.getTag(), 
          board.getLikes());
    }
  }

  public void detail() {
    if (App.getLoginUser().getAuthority() != 3) {
      System.out.println("권한이 없습니다. 관리자 기능입니다.");
      return;
    }
    System.out.println("[게시글 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s입니다.\n", board.getTitle());
    System.out.printf("내용: %s입니다.\n", board.getContent());
    System.out.printf("작성자: %s입니다.\n", board.getWriter());
    System.out.printf("등록일: %s입니다.\n", board.getRegistrationDate());

    board.setViews(board.getViews() + 1);
    System.out.printf("조회수: %d입니다.\n", board.getViews());
    board.setLikes(board.getLikes() + 1);
    System.out.printf("좋아요 수: %d입니다.\n", board.getLikes());
    System.out.printf("태그: %s입니다.\n", board.getTag());

  }

  public void delete() {
    if (App.getLoginUser().getAuthority() != 3) {
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
