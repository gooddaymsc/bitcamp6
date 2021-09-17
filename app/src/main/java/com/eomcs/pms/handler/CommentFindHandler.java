package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;

public class CommentFindHandler implements Command {
  List<Board> boardList;
  BoardPrompt boardPrompt;
  MemberPrompt memberPrompt;

  public CommentFindHandler(List<Board> boardList, BoardPrompt boardPrompt, MemberPrompt memberPrompt) {
    this.boardList = boardList;
    this.boardPrompt = boardPrompt;
    this.memberPrompt = memberPrompt;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[내가 남긴 댓글 목록]");
    System.out.printf("%-3s\t%-10s\t%-15s\t%-6s\n",
        "번호","제목","내가남긴댓글","등록일");
    System.out.println("--------------------------------------------------------------------------");

    for (Board board : boardList) {
      for (Comment comment : board.getCommentList()) {
        if (comment.getId().equals(App.getLoginUser().getId())) {
          System.out.printf("%-3d\t%-10s\t%-15s\t%-6s\n", 
              board.getBoardNumber(),
              board.getTitle(),
              comment.getContent(),
              comment.getRegistrationDate());
        }

      }
    }
  }
}
