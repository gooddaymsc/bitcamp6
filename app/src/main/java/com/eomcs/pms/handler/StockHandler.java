package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockHandler {
  //재고번호
  //상품명
  //재고수량
  //원산지
  //가격

  List<Stock> stockList;

  public StockHandler(List<Stock> stockList) {
    this.stockList = stockList;
  }

  public void add() {
    System.out.println("[재고 등록]");

    Stock stock = new Stock();

    stock.setNo(Prompt.inputInt("번호? "));
    stock.setKind(Prompt.inputString("주종? "));
    stock.setName(Prompt.inputString("상품명? "));
    stock.setStock(Prompt.inputInt("재고수량? "));
    stock.setMade(Prompt.inputString("원산지? "));
    stock.setPrice(Prompt.inputInt("가격?"));

    stockList.add(stock);
  }

  public void list() {
    System.out.println("[재고 목록]");

    Stock[] list = stockList.toArray(new Stock[0]);

    for (Stock stock : list) {
      System.out.printf("%d, %s, %s, %s, %d \n", 
          stock.getNo(), 
          stock.getKind(), 
          stock.getName(), 
          stock.getMade(),
          stock.getPrice());
    }
  }

  public void detail() {
    System.out.println("[재고 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Stock stock = findByNo(no);

    if (stock == null) {
      System.out.println("해당 번호의 재고가 없습니다.");
      return;
    }
    System.out.printf("주종: %s\n", stock.getKind());
    System.out.printf("상품이름: %s\n", stock.getName());
    System.out.printf("원산지: %s\n", stock.getMade());
    System.out.printf("가격: %d\n", stock.getPrice ());
  }

  public void update() {
    System.out.println("[재고 변경]");
    int no = Prompt.inputInt("번호? ");

    Stock stock = findByNo(no);

    if (stock == null) {
      System. out.println("해당 번호의 재고가 없습니다.");
      return;
    }
    String kind = Prompt.inputString("주종(" + stock.getKind() + ")? ");
    String name = Prompt.inputString("상품이름(" + stock.getName()  + ")? ");
    String made = Prompt.inputString("원산지(" + stock.getMade() + ")? ");
    int price = Prompt.inputInt("가격(" + stock.getPrice() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    stock.setKind(kind);
    stock.setName(name);
    stock.setMade(made);
    stock.setPrice(price);

    System.out.println("재고정보를 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[재고 삭제]");
    int no = Prompt.inputInt("번호? ");

    Stock stock = findByNo(no);

    if (stock == null) {
      System.out.println("해당 번호의 재고가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("상품 삭제를 취소하였습니다.");
      return;
    }

    stockList.remove(stock);

    System.out.println("상품을 삭제하였습니다.");
  }

  private Stock findByNo(int no) {
    Stock[] arr = stockList.toArray(new Stock[0]);
    for (Stock stock : arr) {
      if (stock.getNo() == no) {
        return stock;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Stock[] arr = stockList.toArray(new Stock[0]);
    for (Stock stock : arr) {
      if (stock.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  //  public String promptStock(String label) {
  //    while (true) {
  //      String owner = Prompt.inputString(label);
  //      if (this.exist(owner)) {
  //        return owner;
  //      } else if (owner.length() == 0) {
  //        return null;
  //      }
  //      System.out.println("등록된 상품이 아닙니다.");
  //    }
  //  }

  public String promptStocks(String label) {
    String Stocks = "";
    while (true) {
      String Stock = Prompt.inputString(label);
      if (this.exist(Stocks)) {
        if (Stocks.length() > 0) {
          Stocks += ",";
        }
        Stocks += Stock;
        continue;
      } else if (Stock.length() == 0) {
        break;
      } 
      System.out.println("등록된 재고가 아닙니다.");
    }
    return Stocks;
  }
}






