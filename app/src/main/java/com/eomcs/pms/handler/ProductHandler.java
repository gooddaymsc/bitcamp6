package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductHandler {
  //상품번호
  //상품명
  //주종
  //원산지
  //품종
  //알콜도수
  //테이스팅 노트 : 당도, 산도, 바디감
  //가격

  List<Product> alcoholList;

  public ProductHandler(List<Product> alcoholList) {
    this.alcoholList = alcoholList;
  }

  public void add() {
    System.out.println("[상품 등록]");

    Product product = new Product();

    product.setProductNumber(Prompt.inputInt("번호? "));
    product.setProductName(Prompt.inputString("상품명? "));
    product.setProductType(Prompt.inputString("주종? "));
    product.setCountryOrigin(Prompt.inputString("원산지? "));
    product.setVariety(Prompt.inputString("품종? "));
    product.setAlcoholLevel(Prompt.inputInt("알콜도수? "));
    product.setSugerLevel(Prompt.inputInt("당도(1-5)"));
    product.setAcidity(Prompt.inputInt("산도(1-5)"));
    product.setWeight(Prompt.inputInt("바디감(1-5)"));
    product.setPrice(Prompt.inputInt("가격?"));

    alcoholList.add(product);
  }

  public void list() {
    System.out.println("[상품 목록]");

    Product[] list = alcoholList.toArray(new Product[0]);

    for (Product alcohol : list) {
      System.out.printf("%d, %s, %s, %s, %s, %d, %d, %d, %d, %d \n", 
          alcohol.getProductNumber(), 
          alcohol.getProductName(), 
          alcohol.getProductType(), 
          alcohol.getCountryOrigin(),
          alcohol.getVariety(),
          alcohol.getAlcoholLevel(),
          alcohol.getSugerLevel(),
          alcohol.getAcidity(),
          alcohol.getWeight(),
          alcohol.getPrice());
    }
  }

  public void detail() {
    System.out.println("[상품 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Product alcohol = findByNo(no);

    if (alcohol == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    System.out.printf("상품이름: %s\n", alcohol.getProductName());
    System.out.printf("주종: %s\n", alcohol.getProductType());
    System.out.printf("원산지: %s\n", alcohol.getCountryOrigin());
    System.out.printf("품종: %s\n", alcohol.getVariety());
    System.out.printf("알콜도수: %d\n", alcohol.getAlcoholLevel());
    System.out.printf("당도: %d\n", alcohol.getSugerLevel());
    System.out.printf("산도: %d\n", alcohol.getAcidity());
    System.out.printf("바디감: %d\n", alcohol.getWeight());
    System.out.printf("가격: %d\n", alcohol.getPrice ());
  }

  public void update() {
    System.out.println("[상품 변경]");
    int no = Prompt.inputInt("번호? ");

    Product alcohol = findByNo(no);

    if (alcohol == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    String name = Prompt.inputString("상품이름(" + alcohol.getProductName()  + ")? ");
    String kind = Prompt.inputString("주종(" + alcohol.getProductType() + ")? ");
    String made = Prompt.inputString("원산지(" + alcohol.getCountryOrigin() + ")? ");
    String grapes = Prompt.inputString("품종(" + alcohol.getVariety() + ")? ");
    int abv = Prompt.inputInt("알콜도수(" + alcohol.getAlcoholLevel() + ")? ");
    int sweet = Prompt.inputInt("당도(" + alcohol.getSugerLevel() + ")? ");
    int acidic = Prompt.inputInt("산도(" + alcohol.getAcidity() + ")? ");
    int body = Prompt.inputInt("바디감(" + alcohol.getWeight() + ")? ");
    int price = Prompt.inputInt("가격(" + alcohol.getPrice() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    alcohol.setProductName(name);
    alcohol.setProductType(kind);
    alcohol.setCountryOrigin(made);
    alcohol.setVariety(grapes);
    alcohol.setAlcoholLevel(abv);
    alcohol.setSugerLevel(sweet);
    alcohol.setAcidity(acidic);
    alcohol.setWeight(body);
    alcohol.setPrice(price);

    System.out.println("상품정보를 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[상품 삭제]");
    int no = Prompt.inputInt("번호? ");

    Product alcohol = findByNo(no);

    if (alcohol == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("상품 삭제를 취소하였습니다.");
      return;
    }

    alcoholList.remove(alcohol);

    System.out.println("상품을 삭제하였습니다.");
  }

  private Product findByNo(int no) {
    Product[] arr = alcoholList.toArray(new Product[0]);
    for (Product alcohol : arr) {
      if (alcohol.getProductNumber() == no) {
        return alcohol;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Product[] arr = alcoholList.toArray(new Product[0]);
    for (Product Alcohol : arr) {
      if (Alcohol.getProductName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public String promptAlcohol(String label) {
    while (true) {
      String owner = Prompt.inputString(label);
      if (this.exist(owner)) {
        return owner;
      } else if (owner.length() == 0) {
        return null;
      }
      System.out.println("등록된 상품이 아닙니다.");
    }
  }

  public String promptAlcohols(String label) {
    String Alcohols = "";
    while (true) {
      String Alcohol = Prompt.inputString(label);
      if (this.exist(Alcohol)) {
        if (Alcohols.length() > 0) {
          Alcohols += ",";
        }
        Alcohols += Alcohol;
        continue;
      } else if (Alcohol.length() == 0) {
        break;
      } 
      System.out.println("등록된 상품이 아닙니다.");
    }
    return Alcohols;
  }
}






