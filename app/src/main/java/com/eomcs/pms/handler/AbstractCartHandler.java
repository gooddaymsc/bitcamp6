package com.eomcs.pms.handler;

import com.eomcs.util.Prompt;

public abstract class AbstractCartHandler implements Command {
  CartPrompt cartPrompt;

  public AbstractCartHandler(CartPrompt cartPrompt) {
    this.cartPrompt = cartPrompt;
  }
  //수량 처음 입력시 무조건 1이상이어야함.
  protected int checkNum (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      } 
      return num;       
    }
  }
}
