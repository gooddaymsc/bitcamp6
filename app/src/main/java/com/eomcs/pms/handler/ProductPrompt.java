package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;

public class ProductPrompt {

  List<Product> productList;

  public ProductPrompt (List<Product> productList) {
    this.productList = productList;
  }

  protected Product findByProduct (String ProductName) {
    for (Product product : productList) {
      if (product.getProductName().equals(ProductName)) {
        return product;
      }
    }
    return null;
  }

}
