package com.eomcs.pms.handler;

import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductDeleteHandler implements Command {

  ProductDao productDao;

  public ProductDeleteHandler (ProductDao productDao) {
    this.productDao = productDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[상품 삭제]");
    int productNumber = (Integer) request.getAttribute("productNumber");    

    Product product = productDao.findByNo(productNumber);
    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      productDao.delete(product);
      System.out.println("상품을 삭제하였습니다.\n");

    }else {
      System.out.println("상품 삭제를 취소하였습니다.\n");
      return;
    }
  }
}














