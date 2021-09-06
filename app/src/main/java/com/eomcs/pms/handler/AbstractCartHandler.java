package com.eomcs.pms.handler;

public abstract class AbstractCartHandler implements Command {

  CartPrompt cartPrompt;
  public AbstractCartHandler(CartPrompt cartPrompt) {
    this.cartPrompt = cartPrompt;
  }
}
