package com.eomcs.pms.handler;

public abstract class AbstractCommentHandler implements Command {
  BoardPrompt boardPrompt;
  public AbstractCommentHandler(BoardPrompt boardPrompt) {
    this.boardPrompt = boardPrompt;
  }
}
