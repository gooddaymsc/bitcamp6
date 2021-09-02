package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardDetailHandler extends AbstractBoardHandler {

  @Override
  public void execute(int i) {}


  public BoardDetailHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute() {
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
    System.out.printf("좋아요 수: %d입니다.\n", board.getLikes());
    System.out.printf("태그: %s입니다.\n", board.getTag());

  }
}






