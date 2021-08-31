package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardHandler {

  List<Board> boardList;
  public BoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  public void add() {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("권한이 없습니다.\n로그인해주세요...");
      return;
    }
    System.out.println("[새 게시글]");
    Board board = new Board();

    board.setNumber(Prompt.inputInt("번호? "));
    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(Prompt.inputString("작성자? "));
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    board.setTag(Prompt.inputString("태그? "));

    boardList.add(board);
  }

  public void list() {
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

  public void update(int auth) {
    if (auth == 0) {
      System.out.println("권한이 없습니다.\n로그인해주세요...");
      return;
    }
    System.out.println("[게시글 변경]");
    int no = Prompt.inputInt("번호를 입력해주세요: ");

    Board board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("변경 후의 제목(%s)을 입력해주세요: ", board.getTitle()));
    String content = Prompt.inputString(String.format("변경 후의 내용(%s)을 입력해주세요: ", board.getContent()));
    String tag = Prompt.inputString(String.format("변경 후의 태그(%s)를 입력해주세요: ", board.getTag()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    board.setTitle(title);
    board.setContent(content);
    board.setTag(tag);
    System.out.println("게시글을 변경하였습니다.");
  }

  public void delete(int auth) {
    if (auth == 0) {
      System.out.println("권한이 없습니다.\n로그인해주세요...");
      return;
    }
    System.out.println("[게시글 삭제]");
    int no = Prompt.inputInt("번호를 입력해주세요: ");

    Board board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    boardList.remove(board);

    System.out.println("게시글을 삭제하였습니다.");
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






