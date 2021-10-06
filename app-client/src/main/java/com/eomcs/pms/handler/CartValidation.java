package com.eomcs.pms.handler;

import java.util.Set;
import com.eomcs.util.Prompt;

public class CartValidation {

  public static String findStoreName(Set<String> keySet, String storeName) {
    for (String str : keySet) {
      if (str.equals(storeName)) {
        return storeName;
      }
    }
    return null;
  }

  public static int checkNum(String label) throws Exception {
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
