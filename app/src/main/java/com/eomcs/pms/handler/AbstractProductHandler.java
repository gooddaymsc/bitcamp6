package com.eomcs.pms.handler;

import com.eomcs.util.Prompt;

public abstract class AbstractProductHandler implements Command {

  ProductPrompt productPrompt;

  public AbstractProductHandler(ProductPrompt productPrompt) {
    this.productPrompt= productPrompt;
  }

  protected int checkNum(String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1 || num > 5) {  
        System.out.println("1-5 사이 수를 입력해주세요!"); 
        continue;
      }           
      return num;       
    }
  }
}












