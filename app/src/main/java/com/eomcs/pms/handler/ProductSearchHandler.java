package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.util.Prompt;

public class ProductSearchHandler extends AbstractProductHandler {

  List<SellerPrivacy> sellerList;
  public ProductSearchHandler(List<Product> productList, List<SellerPrivacy> sellerList) {
    super(productList);
    this.sellerList = sellerList;
  }

  @Override
  public void execute() {
    System.out.println("[상품검색]");


    String adress = Prompt.inputString("주소입력: ");       //구까지 입력받도록 해야함

    Product productName = findByProduct(Prompt.inputString("상품입력"));

    if (productName == null) {
      System.out.println("입력하신 상품이 없습니다.");
      return;
    }

    //    for(Product productName : productList) {
    //      System.out.printf("상품명: %s\n", productName.getProductName());
    //      System.out.printf("주종: %s\n", productName.getProductType());
    //      System.out.printf("원산지: %s\n", productName.getCountryOrigin());
    //      System.out.printf("품종: %s\n", productName.getVariety());
    //      System.out.printf("알콜도수: %.2f\n", productName.getAlcoholLevel()); 
    //      System.out.printf("당도: %d, 산도: %d, 바디감:%d\n", productName.getSugerLevel(),productName.getAcidity(),productName.getWeight());
    //      System.out.println("===============================");
    //       }

    int input = Prompt.inputInt("찾을 상품번호 선택 >>  ");

    while(true) {

      for(SellerPrivacy member : sellerList) { 
        if((member.getBusinessAddress()).contains(adress)) {
          System.out.printf("상점 이름 : %s \n", member.getBusinessName());
          System.out.printf("상점 주소 : %s \n", member.getBusinessAddress());
          System.out.printf("재고수량 : %s \n", member.getBusinessAddress());
          return;
        }
        else{
          System.out.println("해당지역에 판매처가 없습니다.");
          return;
        }
      }
    }






  }
}













