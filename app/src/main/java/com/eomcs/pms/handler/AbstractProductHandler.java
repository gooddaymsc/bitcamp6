package com.eomcs.pms.handler;

import com.eomcs.util.Prompt;

public abstract class AbstractProductHandler implements Command {

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

  protected String checkType(String label) {
    while(true) {
      String productType = Prompt.inputString(label);
      if (productType.equals("양주") || productType.equals("위스키") 
          || productType.equals("브랜디") || productType.equals("진")
          || productType.equals("보드카") || productType.equals("데킬라")
          || productType.equals("와인") || productType.equals("화이트와인")
          || productType.equals("로제와인")  || productType.equals("레드와인")
          || productType.equals("스파클링와인")  || productType.equals("디저트와인")
          || productType.equals("사케")  || productType.equals("하이볼")) {
        return productType;
      } else {
        System.out.println("정확한 주종을 입력하세요.");
        continue;
      } 
    }
  }
}












