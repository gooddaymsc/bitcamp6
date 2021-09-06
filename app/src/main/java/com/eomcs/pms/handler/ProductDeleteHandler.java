package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductDeleteHandler extends AbstractProductHandler {
  ProductPrompt productPrompt;
  public ProductDeleteHandler(ProductPrompt productPrompt) {
    super(productPrompt);
  }

  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_SELLER 
        && App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("해당 메뉴 권한이 없습니다.");
      return;
    }
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
        productPrompt.productList.remove(product);
        return;
      } else {
        System.out.println("상품 삭제를 취소하였습니다.");
        return;
      }
    }
  }

}












