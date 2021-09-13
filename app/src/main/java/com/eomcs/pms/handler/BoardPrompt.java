package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;

public class BoardPrompt {
  List<Board> boardList;
  public BoardPrompt(List<Board> boardList) {
    this.boardList = boardList;
  }

  public Board findBoardByNo(int boardNumber) {
    for (Board board : boardList) {
      if (board.getBoardNumber()==boardNumber) {
        return board;
      }
    }
    return null;
  }
  public Comment findCommentByNo(int commentNumber, Board board) {
    for (Comment comment : board.getCommentList()) {
      if (comment.getCommentNumber()==commentNumber) {
        return comment;
      }
    }
    return null;
  }
}
