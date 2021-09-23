package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;


public class ProductPrompt {

  List<Product> productList;

  public ProductPrompt (List<Product> productList) {
    this.productList = productList;
  }

  protected int findProductIndex (String ProductName) {
    for (int i = 1; i < productList.size(); i++) {
      if (productList.get(i).getProductName().equals(ProductName)) {
        return i;
      }
    }

    return -1;
  }

  public Product findByProduct (String ProductName) {
    for (Product product : productList) {
      if (product.getProductName().equals(ProductName)) {
        return product;
      }
    }
    return null;
  }

  protected String findByProduct2 (String ProductName) {
    for (Product product : productList) {
      if (product.getProductName().equals(ProductName)) {
        return ProductName;
      }
    }
    return null;
  }

}
