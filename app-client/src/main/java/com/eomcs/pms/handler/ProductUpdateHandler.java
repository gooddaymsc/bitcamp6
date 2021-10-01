package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Product;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ProductUpdateHandler implements Command {

  RequestAgent requestAgent;
  ProductPrompt productPrompt;
  public ProductUpdateHandler(RequestAgent requestAgent, ProductPrompt productPrompt) {
    this.requestAgent = requestAgent;
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[상품 변경]");
    // String productName = Prompt.inputString("\n상품명 > ");
    String productName = (String) request.getAttribute("productName");
    Product product =  productPrompt.findByProduct(productName);

    if (product == null) {
      System.out.println("해당 상품이 존재하지 않습니다.\n");
      return;
    }

    //    String name = Prompt.inputString("상품이름(" + product.getProductName()  + ")? ");
    String type = productPrompt.checkType("주종(" + product.getProductType() + ")? ");
    String subType = productPrompt.checkSubType2(("상세주종(" + product.getProductSubType() + ")? "),type);
    String made = Prompt.inputString("원산지(" + product.getCountryOrigin() + ")? ");
    String grapes = product.getVariety();
    if(type.equals("와인")) {
      grapes = Prompt.inputString("품종(" + product.getVariety() + ")? ");
    }
    int volumes = Prompt.inputInt("용량(" + product.getVolume() +")?");
    float abv = Prompt.inputFloat("알콜도수(" + product.getAlcoholLevel() + ")? ");
    int sweet = productPrompt.checkNum("당도(" + product.getSugerLevel() + ")? ");
    int acidic = productPrompt.checkNum("산도(" + product.getAcidity() + ")? ");
    int body = productPrompt.checkNum("바디감(" + product.getWeight() + ")? ");


    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      //      product.setProductName(name);
      product.setProductType(type);
      product.setProductSubType(subType);
      product.setCountryOrigin(made);
      if(type.equals("와인")){
        product.setVariety(grapes);
      }
      product.setVariety(null);
      product.setVolume(volumes);
      product.setAlcoholLevel(abv);
      product.setSugerLevel(sweet);
      product.setAcidity(acidic);
      product.setWeight(body);
      requestAgent.request("product.update", product);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("상품변경실패!");
        return;
      }
      System.out.println("상품정보를 변경하였습니다.\n");
      return;
    } else {
      System.out.println("상품정보 변경을 취소하였습니다.\n");
      return;
    }
  }
}













