package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.eomcs.pms.domain.Product;

public class RankingHandler implements Command {

  List<Product> productList;
  public RankingHandler(List<Product> productList) {
    this.productList = productList;
  }

  @Override
  public void execute(CommandRequest request) {  

    System.out.println("[이달의 술]\n");


    HashMap <String, Float > map = new HashMap<>();


    //   ArrayList<Float> arrayList = new ArrayList<Float>();

    for(Product product : productList) {  
      map.put(product.getProductName(),product.getRate());
    }

    List<Entry<String, Float>> entries = new ArrayList<>(map.entrySet());

    Collections.sort(entries, new Comparator<Entry<String, Float>>(){

      @Override
      public int compare(Entry<String, Float> obj1, Entry<String,Float> obj2) {
        return obj2.getValue().compareTo(obj1.getValue()); 
      }
    });


    int no = 1;
    for(Entry<String, Float> entry : entries) {
      System.out.printf(" * %d위 %s (평점: %.2f점) * \n", no++, entry.getKey(), entry.getValue());
      for(Product product : productList)
      {
        if(entry.getKey().equals(product.getProductName())){ 
          System.out.printf("주종 : %s\n", product.getProductType());
          System.out.printf("원산지 : %s\n", product.getCountryOrigin());
          System.out.printf("알콜도수 : %.2f\n", product.getAlcoholLevel());
          System.out.println("---------------------------------------");
        }
      }
      if(no == 6) {break;}

    }
  }
}


