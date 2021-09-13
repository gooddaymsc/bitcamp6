package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentUpdateHandler {

  public static void update(int boardNumber, BoardPrompt boardPrompt) {

    //    System.out.println("[댓글 변경]");
    int no = Prompt.inputInt("\n변경할 댓글 번호 : ");

    Board board = boardPrompt.findBoardByNo(boardNumber);
    Comment comment = boardPrompt.findCommentByNo(no, board);

    if (comment == null) {
      System.out.println("해당 번호의 댓글이 없습니다.");
      return;
    }

    if (comment.getId() != App.getLoginUser().getId()) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.\n");
      return;
    }
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", comment.getContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      comment.setContent(content);
      System.out.println("댓글 변경 완료\n");
      return;
    }

    System.out.println("댓글 변경 취소\n");
    return;
  }

}






