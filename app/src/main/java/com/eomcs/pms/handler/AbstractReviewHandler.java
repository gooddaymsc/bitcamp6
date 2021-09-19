package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.util.Prompt;

public abstract class AbstractReviewHandler implements Command {

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

  protected Review findReviewById(Product product, String id) {
    for (Review review : product.getReviewList()) {
      if (review.getId().equals(id)) {
        return review;
      }
    }
    return null;
  }
}

