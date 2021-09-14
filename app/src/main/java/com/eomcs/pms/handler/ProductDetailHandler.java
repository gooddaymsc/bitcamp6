package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductDetailHandler extends AbstractProductHandler {

  ProductPrompt productPrompt;
  List<Product> productList;

  public ProductDetailHandler (ProductPrompt productPrompt, List<Product> productList) {
    this.productPrompt = productPrompt;
    this.productList = productList;

  }

  @Override
  public void execute() {
    //    String nowLoginId = App.getLoginUser().getId();
    System.out.println("[상품 상세보기]");

    Product product = productPrompt.findByProduct(Prompt.inputString("상품명 : "));


    if (product == null) {
      System.out.println("입력하신 상품이 없습니다.");
      return;
    }

    System.out.printf("상품명: %s\n", product.getProductName());
    System.out.printf("주종: %s\n", product.getProductType());
    System.out.printf("평점: %.2f\n", product.getScore());
    System.out.printf("원산지: %s\n", product.getCountryOrigin());
    System.out.printf("품종: %s\n", product.getVariety());
    System.out.printf("알콜도수: %.1f\n", product.getAlcoholLevel());
    System.out.printf("당도: %d\n", product.getSugerLevel());
    System.out.printf("산도: %d\n", product.getAcidity());
    System.out.printf("바디감: %d\n", product.getWeight());

    System.out.println("\n[Reviews]");

    Product[] products = new Product[productList.size()];
    productList.toArray(products);

    System.out.printf("%-6s\t%-10s\t%-6s\t%-6s\n",
        "평점", "코멘트", "작성자", "등록일");
    System.out.println("--------------------------------------------------------------------------");


    for(Product productReview : products) {

      System.out.printf("%-6s\t%-10s\t%-6s\t%-6s\n",  
          productReview.getScore(),
          productReview.getComment(),
          productReview.getReviewer(),
          productReview.getRegisteredDate());
    }


    if(App.getLoginUser().getAuthority() != Menu.ACCESS_LOGOUT) {
      String input = Prompt.inputString("\n상품평 등록(y/N) >  ");
      if (input.equalsIgnoreCase("y")) {
        if(App.getLoginUser().getId().equals(product.getReviewer())){
          System.out.println("이미 상품폄을 등록하셨습니다.");
          return;
        } else {
          float scores = checkNum("맛은 어떠셨나요?(1점-5점):");
          product.setScore((product.getScore()*product.getReviewerNum()+scores)/(product.getReviewerNum()+1));
          product.setReviewerNum(product.getReviewerNum()+1);
          product.setComment(Prompt.inputString("한줄평을 등록해주세요:"));
          System.out.println("상품평 등록을 완료하였습니다.");
          product.setRegisteredDate(new Date(System.currentTimeMillis()));
          product.setReviewer(App.getLoginUser().getId());
          return;
        }
      } else {
        System.out.println("상품평 등록을 취소하였습니다.");
        return;
      }
    } else {
      System.out.println("로그인 후 등록가능합니다.");
    }
  }
} 





