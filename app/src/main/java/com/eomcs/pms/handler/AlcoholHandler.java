package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Alcohol;
import com.eomcs.util.Prompt;

public class AlcoholHandler {
  //상품번호
  //상품명
  //주종
  //원산지
  //품종
  //알콜도수
  //테이스팅 노트 : 당도, 산도, 바디감
  //가격

  List<Alcohol> alcoholList;

  public AlcoholHandler(List<Alcohol> alcoholList) {
    this.alcoholList = alcoholList;
  }

  public void add() {
    System.out.println("[상품 등록]");

    Alcohol alcohol = new Alcohol();

    alcohol.setNo(Prompt.inputInt("번호? "));
    alcohol.setName(Prompt.inputString("상품명? "));
    alcohol.setKind(Prompt.inputString("주종? "));
    alcohol.setMade(Prompt.inputString("원산지? "));
    alcohol.setGrapes(Prompt.inputString("품종? "));
    alcohol.setAbv(Prompt.inputInt("알콜도수? "));
    alcohol.setSweet(Prompt.inputInt("당도(1-5)"));
    alcohol.setAcidic(Prompt.inputInt("산도(1-5)"));
    alcohol.setBody(Prompt.inputInt("바디감(1-5)"));
    alcohol.setPrice(Prompt.inputInt("가격?"));

    alcoholList.add(alcohol);
  }

  public void list() {
    System.out.println("[상품 목록]");

    Alcohol[] list = alcoholList.toArray(new Alcohol[0]);

    for (Alcohol alcohol : list) {
      System.out.printf("%d, %s, %s, %s, %s, %d, %d, %d, %d, %d \n", 
          alcohol.getNo(), 
          alcohol.getName(), 
          alcohol.getKind(), 
          alcohol.getMade(),
          alcohol.getGrapes(),
          alcohol.getAbv(),
          alcohol.getSweet(),
          alcohol.getAcidic(),
          alcohol.getBody(),
          alcohol.getPrice());
    }
  }

  public void detail() {
    System.out.println("[상품 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Alcohol alcohol = findByNo(no);

    if (alcohol == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    System.out.printf("상품이름: %s\n", alcohol.getName());
    System.out.printf("주종: %s\n", alcohol.getKind());
    System.out.printf("원산지: %s\n", alcohol.getMade());
    System.out.printf("품종: %s\n", alcohol.getGrapes());
    System.out.printf("알콜도수: %d\n", alcohol.getAbv());
    System.out.printf("당도: %d\n", alcohol.getSweet());
    System.out.printf("산도: %d\n", alcohol.getAcidic());
    System.out.printf("바디감: %d\n", alcohol.getBody());
    System.out.printf("가격: %d\n", alcohol.getPrice ());
  }

  public void update() {
    System.out.println("[상품 변경]");
    int no = Prompt.inputInt("번호? ");

    Alcohol alcohol = findByNo(no);

    if (alcohol == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    String name = Prompt.inputString("상품이름(" + alcohol.getName()  + ")? ");
    String kind = Prompt.inputString("주종(" + alcohol.getKind() + ")? ");
    String made = Prompt.inputString("원산지(" + alcohol.getMade() + ")? ");
    String grapes = Prompt.inputString("품종(" + alcohol.getGrapes() + ")? ");
    int abv = Prompt.inputInt("알콜도수(" + alcohol.getAbv() + ")? ");
    int sweet = Prompt.inputInt("당도(" + alcohol.getSweet() + ")? ");
    int acidic = Prompt.inputInt("산도(" + alcohol.getAcidic() + ")? ");
    int body = Prompt.inputInt("바디감(" + alcohol.getBody() + ")? ");
    int price = Prompt.inputInt("가격(" + alcohol.getPrice() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    alcohol.setName(name);
    alcohol.setKind(kind);
    alcohol.setMade(made);
    alcohol.setGrapes(grapes);
    alcohol.setAbv(abv);
    alcohol.setSweet(sweet);
    alcohol.setAcidic(acidic);
    alcohol.setBody(body);
    alcohol.setPrice(price);

    System.out.println("상품정보를 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[상품 삭제]");
    int no = Prompt.inputInt("번호? ");

    Alcohol alcohol = findByNo(no);

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

  private Alcohol findByNo(int no) {
    Alcohol[] arr = alcoholList.toArray(new Alcohol[0]);
    for (Alcohol alcohol : arr) {
      if (alcohol.getNo() == no) {
        return alcohol;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Alcohol[] arr = alcoholList.toArray(new Alcohol[0]);
    for (Alcohol Alcohol : arr) {
      if (Alcohol.getName().equals(name)) {
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






