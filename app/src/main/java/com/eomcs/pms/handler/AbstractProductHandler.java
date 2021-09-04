package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public abstract class AbstractProductHandler implements Command {

  List<Product> productList;

  public AbstractProductHandler(List<Product> productList) {
    this.productList = productList;
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

  protected Product findByProduct (String ProductName) {
    for (Product product : productList) {
      if (product.getProductName().equals(ProductName)) {
        return product;
      }
    }
    return null;
  }

  protected StockList findById(String id) {
    for (StockList stockList : App.allStockList) {
      if (stockList.getId().equals(id)) {
        return stockList;
      }
    }
    return null;
  }
}












