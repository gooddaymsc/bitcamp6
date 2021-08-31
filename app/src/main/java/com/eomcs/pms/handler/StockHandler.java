package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class StockHandler {

  List<Stock> stockList;
  List<Product> productList;

  //  Manager loginPrivacy;
  public StockHandler(List<Stock> stockList, List<Product> productList ) {
    this.stockList = stockList;
    this.productList = productList;
    //    this.loginPrivacy = loginPrivacy;
  }

  public void add(int auth) {   
    if (auth == 0 || auth == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    System.out.println("\n[재고등록]");

    Stock stock = new Stock(); 

    stock.setStockType(Prompt.inputString("주종? "));
    // Product 에 있는 이름을 비교해서 있으면 stock.product에 넣는다.
    stock.product = findByProduct(Prompt.inputString("상품명?"));
    stock.setStockName(stock.product.getProductName()); 

    if (stock.product == null) {
      System.out.println("입력하신 상품이 없습니다.");
      return;
    }

    stock.setStockNumber(stock.product.getProductNumber());    
    stock.setStockOrigin(stock.product.getCountryOrigin());       
    stock.setPrice(Prompt.inputInt("판매 가격?"));
    stock.setStocks(Prompt.inputInt("재고 수량?"));
    //원산지
    stockList.add(stock);
  }

  public void list(int auth) {
    if (auth==0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("[재고 목록]");

    Stock[] list = stockList.toArray(new Stock[0]);

    for (Stock stock : list) {
      System.out.printf("%d, %s, %s, %d, %d, %s \n", 
          stock.getStockNumber(), 
          stock.getStockType(), 
          stock.getStockName(), 
          stock.getPrice(),
          stock.getStocks(), 
          stock.getStockOrigin());
    }
  }

  public void detail(int auth) {
    if (auth==0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("[재고 상세보기]");
    String name = Prompt.inputString("상품이름? ");
    Stock stock = findByStock(name);

    if (stock == null) {
      System.out.println("해당 번호의 재고가 없습니다.");
      return;
    }
    System.out.printf("재고번호: %s\n", stock.getStockNumber());
    System.out.printf("주종: %s\n", stock.getStockType());
    System.out.printf("상품이름: %s\n", stock.getStockName());
    System.out.printf("가격: %d\n", stock.getPrice ());
    System.out.printf("재고수량: %d\n", stock.getStocks ());   
    System.out.printf("원산지: %s\n", stock.getStockOrigin());

  }

  public void update(int auth) {
    if (auth == 0 || auth == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    System.out.println("[재고 변경]");
    int no = Prompt.inputInt("번호? ");

    Stock stock = findByNo(no);

    if (stock == null) {
      System. out.println("해당 번호의 재고가 없습니다.");
      return;
    }
    int stocks = Prompt.inputInt("재고수량(" + stock.getStocks() + ")? ");
    int price = Prompt.inputInt("가격(" + stock.getPrice() + ")? ");
    String origin = Prompt.inputString("원산지.(" + stock.getStockOrigin() + ")? ");  //test

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("재고 및 가격 변경을 취소하였습니다.");
      return;
    }

    stock.setPrice(stocks);
    stock.setPrice(price);
    stock.setStockOrigin(origin);   //test
    System.out.println("재고정보를 변경하였습니다.");
  }

  public void delete(int auth) {
    if (auth == 0 || auth == 1 ) {
      System.out.println("해당 메뉴는 판매자 권한입니다.");
      return;
    }
    System.out.println("[재고 삭제]");
    String name = Prompt.inputString("상품이름? ");

    Stock stock = findByStock(name);

    if (stock == null) {
      System. out.println("해당 이름의 재고가 없습니다.");
      return;
    }

    String input = Prompt.inputString("상품을 판매내역에서 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("삭제를 취소하였습니다.");
      return;
    }

    stockList.remove(stock);

    System.out.println("상품을 삭제하였습니다.");
  }

  private Stock findByNo(int no) {
    Stock[] arr = stockList.toArray(new Stock[0]);
    for (Stock stock : arr) {
      if (stock.getStockNumber() == no) {
        return stock;
      }
    }
    return null;
  }

  private Product findByProduct (String ProductName) {
    for (Product product : productList) {
      if (product.getProductName().equals(ProductName)) {
        return product;
      }
    }
    return null;
  } 

  private Stock findByStock (String ProductName) {
    Stock[] arr = stockList.toArray(new Stock[0]);
    for (Stock stock : arr) {
      if (stock.product.getProductName().equals(ProductName)) {
        return stock;
      }
    }
    return null;
  } 

  public boolean exist(String name) {
    Stock[] arr = stockList.toArray(new Stock[0]);
    for (Stock stock : arr) {
      if (stock.getStockName().equals(name)) {
        return true;
      }
    }
    return false;
  }

}






