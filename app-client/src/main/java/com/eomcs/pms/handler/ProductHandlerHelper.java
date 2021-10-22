package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ProductType;

public class ProductHandlerHelper {

  ProductDao productDao;

  public ProductHandlerHelper (ProductDao productDao) {
    this.productDao = productDao;
  }

  public ProductType promptType(String type, String subType) throws Exception{

    List<ProductType> typeList = productDao.findAllProductType();

    for(ProductType productType : typeList) {
      if(productType.getType().equals(type)) {
        if(productType.getSubType().equals(subType)) {
          return productType;
        }
      }
    }
    return null;
  }
}
