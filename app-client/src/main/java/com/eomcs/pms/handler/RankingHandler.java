package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class RankingHandler implements Command {

  ProductDao productDao;

  public RankingHandler(ProductDao productDao) {
    this.productDao = productDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {  
    Loop : while(true) {
      System.out.println("  -----------     ");
      System.out.println(" |  RANKING  |    ");
      System.out.println("  -----------  \n ");


      HashMap <String, Float > map = new HashMap<>();
      HashMap <Integer, String> numberMap = new HashMap<>();
      Collection<Product> productList = productDao.findAll();

      for(Product product : productList) {  
        map.put(product.getProductName(),product.getRate());
      }

      List<Entry<String, Float>> entries = new ArrayList<>(map.entrySet());

      //객체 정렬
      Collections.sort(entries, new Comparator<Entry<String, Float>>(){

        @Override
        public int compare(Entry<String, Float> obj1, Entry<String,Float> obj2) {
          return obj2.getValue().compareTo(obj1.getValue()); 
        }
      });

      int no = 1;
      for(Entry<String, Float> entry : entries) {
        System.out.printf(" * %d위 %s *  (평점: %.2f점)  \n", no++, entry.getKey(), entry.getValue());
        for(Product product : productList)
        {
          if(entry.getKey().equals(product.getProductName())){ 
            System.out.printf("- 주종 : %s\n", product.getProductType());
            System.out.printf("- 원산지 : %s\n", product.getCountryOrigin());
            System.out.printf("- 알콜도수 : %.2f\n", product.getAlcoholLevel());
            System.out.println("-----------------------------------------------");
            numberMap.put(no-1, product.getProductName());
          }
        }
        if(no == 6) {break;}
      }
      System.out.println();
      System.out.println("상품 상세정보(1) / 이전(0) ");
      while(true) {
        int choose = Prompt.inputInt("선택 > ");
        switch (choose) {
          case 1 : 
            int chooseNum = ProductValidation.checkNum("상품선택(1-5위) > ");
            System.out.printf("\n # 상품명 : %s \n", numberMap.get(chooseNum));
            request.setAttribute("productName", numberMap.get(chooseNum));
            System.out.println();
            request.getRequestDispatcher("/product/detail").forward(request);       //상품 상세보기
            continue Loop;
          case 0 : return;
          default : System.out.println("다시 선택해 주세요."); continue;
        }
      }
    }
  }
}




