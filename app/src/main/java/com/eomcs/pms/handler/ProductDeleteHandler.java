package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductDeleteHandler extends AbstractProductHandler {

  public ProductDeleteHandler(List<Product> productList) {
    super(productList);
  }

  public void execute() {
    if (App.getLoginUser().getAuthority() == 0 || App.getLoginUser().getAuthority() == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    while(true) {
      System.out.println("[상품 삭제]");

      Product product = findByProduct(Prompt.inputString("상품명 : "));

      if (product == null) {
        System.out.println("해당 상품이 존재하지 않습니다.");
        return;
      }

      String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        System.out.println("상품을 삭제하였습니다.");
        return;
      } else {
        System.out.println("상품 삭제를 취소하였습니다.");
        return;
      }
    }
  }

}












