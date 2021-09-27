package com.eomcs.pms.handler;

import com.eomcs.util.Prompt;

public abstract class AbstractStockHandler implements Command {

  // Stock findByStock(String ProductName) 과 StockList findById(String id) 
  // => StockPrompt 에 있음.
  StockPrompt stockPrompt;
  public AbstractStockHandler(StockPrompt stockPrompt) {
    this.stockPrompt = stockPrompt;
  }

  //수량 처음 입력시 무조건 1이상이어야함.
  protected int checkNum (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      } else if (num >= 100) {
        System.out.println("입력하신 수는 100을 넘습니다.\n잘못 입력하셨다면 My Store에서 수량울 변경해 주세요.\n");
      }
      return num;       
    }
  }
  // 수량 변경시 0이상 가능.
  protected int checkNum2 (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 0) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      } else if (num >= 100) {
        System.out.println("입력하신 수는 100을 넘습니다.\n잘못 입력하셨다면 My Store에서 수량울 변경해 주세요.\n");
      }
      return num;       
    }
  }

  protected int checkPrice(String label) {
    while(true) {
      int price = Prompt.inputInt(label);
      if(price < 1) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      } 
      return price;       
    }
  }
}