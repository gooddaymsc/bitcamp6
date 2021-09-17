package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentDeleteHandler implements Command {

  BoardPrompt boardPrompt;
  public CommentDeleteHandler(BoardPrompt boardPrompt) {
    this.boardPrompt = boardPrompt;
  }
  @Override
  public void execute(CommandRequest request) {

    System.out.println("\n[댓글 삭제]");
    int no = Prompt.inputInt("삭제할 댓글 번호 : ");

    int boardNumber = (int) request.getAttribute("no");
    Board board = boardPrompt.findBoardByNo(boardNumber);
    Comment comment = boardPrompt.findCommentByNo(no, board);

    if (comment == null) {
      System.out.println("해당 번호의 댓글 없습니다.\n");
      return;
    }

    if (!((comment.getId().equals(App.getLoginUser().getId())) ||
        (App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN))) {
      System.out.println("작성자가 아니므로 삭제할 수 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      board.getCommentList().remove(comment);
      System.out.println("댓글 삭제 완료\n");
      return;
    }
    System.out.println("댓글 삭제 취소\n");
    return;
  }

}






