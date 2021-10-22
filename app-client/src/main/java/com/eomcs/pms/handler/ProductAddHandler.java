package com.eomcs.pms.handler;

import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ProductType;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductAddHandler implements Command {

  ProductDao productDao;

  public ProductAddHandler (ProductDao productDao) {
    this.productDao = productDao;
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
    String type = ProductValidation.checkType("주종 : ");
    String subType = ProductValidation.checkSubType("상세주종 : ",product.getProductTypes().getType());

    ProductType productType = new ProductHandlerHelper(productDao).promptType(type, subType);

    product.setProductTypes(productType);
    product.setCountryOrigin(Prompt.inputString("원산지 : "));
    if(product.getProductTypes().getType().equals("와인")) {
      product.setVariety(Prompt.inputString("품종 : "));
    }
    product.setVolume(Prompt.inputInt("용량 : "));
    product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
    product.setSugarLevel(ProductValidation.checkNum("당도(1-5) : "));
    product.setAcidity(ProductValidation.checkNum("산도(1-5) : "));
    product.setWeight(ProductValidation.checkNum("바디감(1-5) : "));

    productDao.insert(product);

    System.out.println("상품을 등록하였습니다.\n");

  }  
}











