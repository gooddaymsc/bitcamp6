package com.eomcs.pms.handler;

import com.eomcs.pms.App;
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
    //    if (product.getReviewList().size()==0) {
    //      return null;
    //    }
    for (Review review : product.getReviewList()) {
      if (review.getId().equals(id)) {
        return review;
      }
    }
    return null;
  }

  protected boolean reviewIs(Product product) {
    for (Review review : product.getReviewList()) {
      if (review.getId().equals(App.getLoginUser().getId())) {
        return true;
      }
    }
    return false;
  }
}

