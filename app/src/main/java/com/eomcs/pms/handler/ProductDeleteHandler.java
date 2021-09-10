package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductDeleteHandler extends AbstractProductHandler {

  List<Product> productList;
  ProductPrompt productPrompt;

  public ProductDeleteHandler(ProductPrompt productPrompt, List<Product> productList) {
    this.productPrompt = productPrompt;
    this.productList = productList;
  }
  @Override
  public void execute() {
    while(true) {
      System.out.println("[상품 삭제]");

      Product product = productPrompt.findByProduct(Prompt.inputString("상품명 : "));

      if (product == null) {
        System.out.println("해당 상품이 존재하지 않습니다.");
        return;
      }

      String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        System.out.println("상품을 삭제하였습니다.");
        productList.remove(product);
        return;
      } else {
        System.out.println("상품 삭제를 취소하였습니다.");
        return;
      }
    }
  }

}












