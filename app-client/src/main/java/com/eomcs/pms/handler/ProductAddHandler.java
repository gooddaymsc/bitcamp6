package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.pms.domain.Product;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ProductAddHandler implements Command {

  Collection<Product> productList;
  RequestAgent requestAgent;
  public ProductAddHandler (RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[상품 등록]");
    Product product = new Product();
    String productName = Prompt.inputString("상품명 : ");


    //    if (findByProduct(productName)!=null) {
    //      System.out.println("이미 추가된 상품입니다.\n");
    //      return;
    //    }
    product.setProductName(productName);
    product.setProductType(checkType("주종 : "));
    product.setProductSubType(checkSubType("상세주종 : ",product));
    product.setCountryOrigin(Prompt.inputString("원산지 : "));
    if(product.getProductType().equals("와인")) {
      product.setVariety(Prompt.inputString("품종 : "));
    }
    product.setVolume(Prompt.inputInt("용량 : "));
    product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
    product.setSugerLevel(checkNum("당도(1-5) : "));
    product.setAcidity(checkNum("산도(1-5) : "));
    product.setWeight(checkNum("바디감(1-5) : "));
    //    product.setProductNumber(totalNumberList.get(App.PROUDCT_NUMBER_INDEX));
    //    // Numbering은 마지막에
    //    totalNumberList.set(App.PROUDCT_NUMBER_INDEX, product.getProductNumber()+1);
    //    productList.add(product);

    requestAgent.request("product.insert", product);

    if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("상품을 등록하였습니다.\n");
    } else {
      System.out.println("회원 등록 실패");
    }
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
  //
  //  protected int findProductIndex (String ProductName) {
  //    for (int i = 1; i < productList.size(); i++) {
  //      if (productList.get(i).getProductName().equals(ProductName)) {
  //        return i;
  //      }
  //    }
  //
  //    return -1;
  //  }

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











