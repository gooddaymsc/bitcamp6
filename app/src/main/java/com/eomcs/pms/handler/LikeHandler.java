package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;

public class LikeHandler implements Command {

  BoardPrompt boardPrompt;

  public LikeHandler(BoardPrompt boardPrompt) {
    this.boardPrompt = boardPrompt;
  }
  @Override
  public void execute(CommandRequest request) {

    Board board = boardPrompt.findBoardByNo((int) request.getAttribute("no"));
    if (board.getLikeMember().contains(App.getLoginUser().getId())) {
      System.out.println("좋아요를 취소합니다.\n");
      board.getLikeMember().remove(App.getLoginUser().getId());
      board.setLikes(board.getLikes() - 1);
    } else {
      System.out.println("좋아요를 눌렀습니다.\n");
      board.setLikes(board.getLikes() + 1);
      board.getLikeMember().add(App.getLoginUser().getId());
    }
    return;
  }
}
