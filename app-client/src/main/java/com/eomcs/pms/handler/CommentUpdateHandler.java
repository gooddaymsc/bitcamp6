package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.util.Prompt;

public class CommentUpdateHandler implements Command {
  BoardDao boardDao;
  public CommentUpdateHandler(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int commentNo = Prompt.inputInt("변경할 댓글 번호 : ");

    int boardNo = (int) request.getAttribute("no");
    Comment comment = boardDao.findCommentByNo(boardNo, commentNo);

    if (comment == null) {
      System.out.println("해당 번호의 댓글이 없습니다.\n");
      return;
    }

    if (!comment.getId().equals(ClientApp.getLoginUser().getId())) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.\n");
      return;
    }
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", comment.getContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {

      comment.setContent(content);
      boardDao.update(comment);

      System.out.println("댓글 변경 완료\n");
      return;
    }

    System.out.println("댓글 변경 취소\n");
    return;
  }

}






