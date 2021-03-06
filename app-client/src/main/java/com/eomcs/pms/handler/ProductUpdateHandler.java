package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductUpdateHandler implements Command {

  ProductDao productDao;
  SqlSession sqlSession;
  public ProductUpdateHandler (ProductDao productDao, SqlSession sqlSession) {
    this.productDao = productDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[상품 변경]");

    int productNumber = (Integer) request.getAttribute("productNumber");

    Product product =  productDao.findByNo(productNumber);

    String name = Prompt.inputString("상품이름(" + product.getProductName()  + ")? ");
    if (productDao.findByProduct(name)!=null) {
      System.out.println("중복되는 이름입니다.\n");
      return;  }
    String type = ProductValidation.checkType("주종(" + product.getProductType().getType() + ")? ");
    String subType = ProductValidation.checkSubType(("상세주종(" + product.getProductType().getSubType() + ")? "),type);
    String made = Prompt.inputString("원산지(" + product.getCountryOrigin() + ")? ");
    String grapes = product.getVariety();
    if(type.equals("와인")) {
      grapes = Prompt.inputString("품종(" + product.getVariety() + ")? ");
    }
    int volumes = Prompt.inputInt("용량(" + product.getVolume() +")?");
    float abv = Prompt.inputFloat("알콜도수(" + product.getAlcoholLevel() + ")? ");
    int sweet = ProductValidation.checkNum("당도(" + product.getSugarLevel() + ")? ");
    int acidic = ProductValidation.checkNum("산도(" + product.getAcidity() + ")? ");
    int body = ProductValidation.checkNum("바디감(" + product.getWeight() + ")? ");


    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      product.setProductName(name);
      product.setProductType(new ProductHandlerHelper(productDao).promptType(type, subType));
      product.setCountryOrigin(made);
      if(type.equals("와인")){
        product.setVariety(grapes);
      } else {
        product.setVariety(null);
      }
      product.setVolume(volumes);
      product.setAlcoholLevel(abv);
      product.setSugarLevel(sweet);
      product.setAcidity(acidic);
      product.setWeight(body);

      productDao.update(product);
      sqlSession.commit();
      System.out.println("상품정보를 변경하였습니다.\n");
    } else {
      System.out.println("상품정보 변경을 취소하였습니다.\n");
      return;
    }
  }
}













