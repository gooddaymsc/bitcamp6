package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductHandler {

  List<Product> alcoholList;
  //Manager loginPrivacy;
  public ProductHandler(List<Product> alcoholList) {
    this.alcoholList = alcoholList;
    //this.loginPrivacy = loginPrivacy;
  }

  public void add(int auth) {
    if (auth == 0 || auth == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    System.out.println("[상품 등록]");

    Product product = new Product();

    product.setProductNumber(Prompt.inputInt("번호를 입력해주세요: "));
    product.setProductName(Prompt.inputString("상품명를 입력해주세요: "));
    product.setProductType(Prompt.inputString("주종을 입력해주세요: "));
    product.setCountryOrigin(Prompt.inputString("원산지를 입력해주세요: "));
    product.setVariety(Prompt.inputString("품종을 입력해주세요: "));
    product.setAlcoholLevel(Prompt.inputInt("알콜도수를 입력해주세요: "));
    product.setSugerLevel(Prompt.inputInt("당도(1~5)를 입력해주세요: "));
    product.setAcidity(Prompt.inputInt("산도(1~5)를 입력해주세요: "));
    product.setWeight(Prompt.inputInt("바디감(1~5)를 입력해주세요: "));
    product.setPrice(Prompt.inputInt("가격을 입력해주세요: "));

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
    int no = Prompt.inputInt("번호를 입력해주세요: ");

    Product alcohol = findByNo(no);

    if (alcohol == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    System.out.printf("상품이름: %s입니다.\n", alcohol.getProductName());
    System.out.printf("주종: %s입니다.\n", alcohol.getProductType());
    System.out.printf("원산지: %s입니다.\n", alcohol.getCountryOrigin());
    System.out.printf("품종: %s입니다.\n", alcohol.getVariety());
    System.out.printf("알콜도수: %d입니다.\n", alcohol.getAlcoholLevel());
    System.out.printf("당도: %d입니다.\n", alcohol.getSugerLevel());
    System.out.printf("산도: %d입니다.\n", alcohol.getAcidity());
    System.out.printf("바디감: %d입니다.\n", alcohol.getWeight());
    System.out.printf("가격: %d입니다.\n", alcohol.getPrice ());
  }

  public void update(int auth) {
    if (auth == 0 || auth == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    System.out.println("[상품 변경]");
    int no = Prompt.inputInt("번호를 입력해주세요: ");

    Product alcohol = findByNo(no);

    if (alcohol == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    String name = Prompt.inputString("변경 후의 상품이름(" + alcohol.getProductName()  + ")을 입력해주세요: ");
    String kind = Prompt.inputString("변경 후의 주종(" + alcohol.getProductType() + ")을 입력해주세요: ");
    String made = Prompt.inputString("변경 후의 원산지(" + alcohol.getCountryOrigin() + ")를 입력해주세요: ");
    String grapes = Prompt.inputString("변경 후의 품종(" + alcohol.getVariety() + ")을 입력해주세요: ");
    int abv = Prompt.inputInt("변경 후의 알콜도수(" + alcohol.getAlcoholLevel() + ")를 입력해주세요: ");
    int sweet = Prompt.inputInt("변경 후의 당도(" + alcohol.getSugerLevel() + ")를 입력해주세요: ");
    int acidic = Prompt.inputInt("변경 후의 산도(" + alcohol.getAcidity() + ")를 입력해주세요: ");
    int body = Prompt.inputInt("변경 후의 바디감(" + alcohol.getWeight() + ")을 입력해주세요: ");
    int price = Prompt.inputInt("변경 후의 가격(" + alcohol.getPrice() + ")을 입력해주세요: ");

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

  public void delete(int auth) {
    if (auth == 0 || auth == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    System.out.println("[상품 삭제]");
    int no = Prompt.inputInt("번호를 입력해주세요: ");

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






