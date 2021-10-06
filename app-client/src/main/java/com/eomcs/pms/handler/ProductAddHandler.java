package com.eomcs.pms.handler;

import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductAddHandler implements Command {

  ProductDao productDao;
  ProductPrompt productPrompt;

  public ProductAddHandler (ProductDao productDao,  ProductPrompt productPrompt) {
    this.productDao = productDao;
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[상품 등록]");
    Product product = new Product();
    String productName = Prompt.inputString("상품명 : ");

    if (productDao.findByProduct(productName)!=null) {
      System.out.println("이미 추가된 상품입니다.\n");
      return;  }

    product.setProductName(productName);
    product.setProductType(productPrompt.checkType("주종 : "));
    product.setProductSubType(productPrompt.checkSubType("상세주종 : ",product));
    product.setCountryOrigin(Prompt.inputString("원산지 : "));
    if(product.getProductType().equals("와인")) {
      product.setVariety(Prompt.inputString("품종 : "));
    }
    product.setVolume(Prompt.inputInt("용량 : "));
    product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
    product.setSugerLevel(productPrompt.checkNum("당도(1-5) : "));
    product.setAcidity(productPrompt.checkNum("산도(1-5) : "));
    product.setWeight(productPrompt.checkNum("바디감(1-5) : "));

    productDao.insert(product);

    System.out.println("상품을 등록하였습니다.\n");

  }  
}











