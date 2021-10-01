package com.eomcs.pms.handler;

import java.util.Collection;
import java.util.HashMap;
import com.eomcs.pms.domain.Product;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ProductDeleteHandler implements Command {

  Collection<Product> productList;
  RequestAgent requestAgent;
  public ProductDeleteHandler (RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[상품 삭제]");
    //String productName = Prompt.inputString("상품명 >");
    String productName = (String) request.getAttribute("productName");
    HashMap<String, String> params = new HashMap<>();

    params.put("productName", productName);
    requestAgent.request("product.selectOne", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 상품이 존재하지 않습니다.\n");
      return;
    }

    Product product = requestAgent.getObject(Product.class);

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      System.out.println("상품을 삭제하였습니다.\n");
      requestAgent.request("product.delete", product);
      if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("상품 삭제 실패");
        System.out.println(requestAgent.getObject(String.class));
        return;
      }
    }else {
      System.out.println("상품 삭제를 취소하였습니다.\n");
      return;
    }
  }
}














