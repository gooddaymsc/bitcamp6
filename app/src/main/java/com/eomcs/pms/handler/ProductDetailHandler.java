package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductDetailHandler extends AbstractProductHandler {

  ProductPrompt productPrompt;

  public ProductDetailHandler (ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }
  @Override
  public void execute() {
    String nowLoginId = App.getLoginUser().getId();
    System.out.println("[상품 상세보기]");

    Product product = productPrompt.findByProduct(Prompt.inputString("상품명 : "));

    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.");
      return;
    }

    System.out.printf("주종: %s\n", product.getProductType());
    System.out.printf("상품명: %s\n", product.getProductName());
    System.out.printf("평점: %.2f\n", product.getScore());
    System.out.printf("원산지: %s\n", product.getCountryOrigin());
    System.out.printf("품종: %s\n", product.getVariety());
    System.out.printf("알콜도수: %.1f\n", product.getAlcoholLevel());
    System.out.printf("당도: %d\n", product.getSugerLevel());
    System.out.printf("산도: %d\n", product.getAcidity());
    System.out.printf("바디감: %d\n", product.getWeight());

    String input = Prompt.inputString("\n상품평 등록(y/N) >  ");
    if (input.equalsIgnoreCase("y")) {
      float scores = checkNum("맛은 어떠셨나요?(1점-5점):");
      product.setScore((product.getScore()*product.getParticipants()+scores)/(product.getParticipants()+1));
      product.setParticipants(product.getParticipants()+1);
      product.setComment("한줄평을 등록해주세요:");
      System.out.println("상품평 등록을 완료하였습니다.");
      System.out.println(product.getScore());
      return;
    } else {
      System.out.println("상품평 등록을 취소하였습니다.");
      return;
    }
  }

}




