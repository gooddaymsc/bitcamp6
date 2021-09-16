package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.eomcs.pms.domain.Product;

public class RankingHandler implements Command {

  List<Product> productList;
  public RankingHandler(List<Product> productList) {
    this.productList = productList;
  }

  @Override
  public void execute() {  

    System.out.println("[이달의 술]");

    int no = 1;
    ArrayList<Float> arrayList = new ArrayList<Float>();

    for(Product product : productList) {
      arrayList.add(product.getRate());   
      Collections.sort(arrayList, Collections.reverseOrder());
    }

    for(float i : arrayList) {
      System.out.printf("** %d위 (평점: %.2f점) ** \n", no++, i);
      if(no == 6) {break;}
    }
  }
}


