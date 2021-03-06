package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;


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

  protected String checkType(String label) {
    while(true) {
      System.out.println(" < 와인(1)/ 위스키(2)/ 브랜디,꼬냑(3) / 리큐르,보드카(4)/ 전통주(5) >");
      String productType = Prompt.inputString(label);
      switch(productType){
        case("1") : return "와인";
        case("2") : return "위스키";
        case("3") : return "브랜디/꼬냑";
        case("4") : return "리큐르/보드카";
        case("5") : return "전통주"; 
        default : System.out.println("정확한 주종을 입력하세요"); continue;
      }
    }
  }

  protected String checkSubType(String label, Product product) {
    while(true) {
      if(product.getProductType().equals("와인")) {
        System.out.println(" < 레드와인(1) / 화이트와인(2) / 로제와인(3) / 스위트와인(4) / 스파클링와인(5) >");  
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "레드와인";
          case("2") : return "화이트와인";
          case("3") : return "로제와인";
          case("4") : return "스위트와인";
          case("5") : return "스파클링와인";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      } if(product.getProductType().equals("위스키")) {
        System.out.println(" < 아메리칸위스키(버번)(1)/ 스카치위스키(몰트)(2) / 아이리쉬위스키(3) / 캐나다위스키(4) >"); 
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "아메리칸위스키(버번)";
          case("2") : return "스카치위스키(몰트)";
          case("3") : return "아이리쉬위스키";
          case("4") : return "캐나다위스키";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      } if(product.getProductType().equals("브랜디/꼬냑")) {
        System.out.println(" < 브랜디(1)/ 꼬냑(2) / 알마냑(3) >"); 
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "브랜디";
          case("2") : return "꼬냑";
          case("3") : return "알마냑";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      } if(product.getProductType().equals("리큐르/보드카")) {
        System.out.println(" < 리큐르(1)/ 진(2) / 럼(3) / 보드카(4) / 데낄라(5) / 음료.시럽(6) >"); 
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "리큐르";
          case("2") : return "진";
          case("3") : return "럼";
          case("4") : return "보드카";
          case("5") : return "데낄라";
          case("6") : return "읍료/시럽";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      } if(product.getProductType().equals("전통주")) {
        System.out.println(" < 한국전통주(1)/ 중국전통주(2) / 일본전통주(3) / 기타전통주(4) >"); 
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "한국전통주";
          case("2") : return "중국전통주";
          case("3") : return "일본전통주";
          case("4") : return "기타전통주";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      }
    }
  }


  protected String checkSubType2(String label, String type) {
    while(true) {
      if(type.equals("와인")) {
        System.out.println(" < 레드와인(1) / 화이트와인(2) / 로제와인(3) / 스위트와인(4) / 스파클링와인(5) >");  
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "레드";
          case("2") : return "화이트";
          case("3") : return "로제";
          case("4") : return "스위트";
          case("5") : return "스파클링";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      } if(type.equals("위스키")) {
        System.out.println(" < 아메리칸위스키(버번)(1)/ 스카치위스키(몰트)(2) / 아이리쉬위스키(3) / 캐나다위스키(4) >"); 
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "아메리칸위스키(버번)";
          case("2") : return "스카치위스키(몰트)";
          case("3") : return "아이리쉬위스키";
          case("4") : return "캐나다위스키";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      } if(type.equals("브랜디/꼬냑")) {
        System.out.println(" < 브랜디(1)/ 꼬냑(2) / 알마냑(3) >"); 
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "브랜디";
          case("2") : return "꼬냑";
          case("3") : return "알마냑";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      } if(type.equals("리큐르/보드카")) {
        System.out.println(" < 리큐르(1)/ 진(2) / 럼(3) / 보드카(4) / 데낄라(5) / 음료.시럽(6) >"); 
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "리큐르";
          case("2") : return "진";
          case("3") : return "럼";
          case("4") : return "보드카";
          case("5") : return "데낄라";
          case("6") : return "읍료/시럽";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      } if(type.equals("전통주")) {
        System.out.println(" < 한국전통주(1)/ 중국전통주(2) / 일본전통주(3) / 기타전통주(4) >"); 
        String productType2 = Prompt.inputString(label);
        switch(productType2){
          case("1") : return "한국전통주";
          case("2") : return "중국전통주";
          case("3") : return "일본전통주";
          case("4") : return "기타전통주";
          default : System.out.println("정확한 주종을 입력하세요"); continue;
        }
      }
    }
  }
}



