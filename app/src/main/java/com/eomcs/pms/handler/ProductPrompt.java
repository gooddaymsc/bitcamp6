package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;

public class ProductPrompt {

  protected static Product findByProduct (String ProductName) {
    for (Product product : App.productList) {
      if (product.getProductName().equals(ProductName)) {
        return product;
      }
    }
    return null;
  }

}
