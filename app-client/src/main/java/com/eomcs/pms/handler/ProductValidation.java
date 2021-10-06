package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;

public class ProductValidation {

  public static Review findReviewById(Product product, String id) {
    for (Review review : product.getReviewList()) {
      if (review.getId().equals(id)) {
        return review;
      }
    }
    return null;
  }
}
