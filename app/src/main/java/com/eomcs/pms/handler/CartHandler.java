package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class CartHandler {

  List<Cart> cartList;
  int cartNumber=1;
  AbstractStockHandler test;
  public CartHandler(List<Cart> cartList, AbstractStockHandler test) {
    this.cartList = cartList;
    this.test = test;
  }

  public void add() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 등록]");

    Cart cart = new Cart();

    cart.setCartNumber(cartNumber++);

    Stock stock = test.findByStock(Prompt.inputString("상품명을 입력해주세요: "));
    if (stock == null) {
      System.out.println("해당 상품이 없습니다.");
      return;
    }
    cart.setStock(stock);
    cart.setCartStocks(Prompt.inputInt("수량 : "));
    cart.setRegistrationDate(new Date(System.currentTimeMillis()));

    cartList.add(cart);

    System.out.println("장바구니가 등록되었습니다.");
  }

  public void list() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 목록]");

    Cart[] list = cartList.toArray(new Cart[0]);

    for (Cart cart : list) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          cart.getCartNumber(), 
          cart.getCartNumber(), 
          cart.getProduct().getProductName(), 
          cart.getProduct().getCountryOrigin(), 
          cart.getRegistrationDate());
    }
  }

  public void detail() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 상세보기]");
    int no = Prompt.inputInt("번호를 입력하세요: ");

    Cart cart = findByNo(no);

    if (cart == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    System.out.printf("상품명: %s입니다.\n", cart.getProductName());
    System.out.printf("종류: %s입니다.\n", cart.getProductType());
    System.out.printf("원산지: %s입니다.\n", cart.getCountryOrigin());
    System.out.printf("사진: %s입니다.\n", cart.getProductPhoto());
    System.out.printf("가격: %s입니다.\n", cart.getPrice());
    System.out.printf("등록일: %s입니다.\n", cart.getRegistrationDate());
  }

  public void update() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 변경]");
    int no = Prompt.inputInt("번호를 입력하세요: ");

    Cart cart = findByNo(no);

    if (cart == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    String name = Prompt.inputString("변경 후의 상품명을 입력해주세요:(" + cart.getProductName()  + ")? ");
    String kind = Prompt.inputString("변경 후의 종류를 입력해주세요:(" + cart.getProductType() + ")? ");
    String madeIn = Prompt.inputString("변경 후의 원산지를 입력해주세요:" + cart.getCountryOrigin() + ")? ");
    String photo = Prompt.inputString("변경 후의 사진을 등록해주세요:(" + cart.getProductPhoto() + ")? ");
    String price = Prompt.inputString("변경 후의 가격을 입력해주세요:(" + cart.getPrice() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("장바구니 변경을 취소하였습니다.");
      return;
    }

    cart.setProductName(name);
    cart.setProductType(kind);
    cart.setCountryOrigin(madeIn);
    cart.setProductPhoto(photo);
    cart.setPrice(price);

    System.out.println("장바구니를 변경하였습니다.");
  }

  public void delete() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 삭제]");
    int no = Prompt.inputInt("번호를 입력하세요: ");

    Cart cart = findByNo(no);

    if (cart == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("장바구니 삭제를 취소하였습니다.");
      return;
    }

    cartList.remove(cart);

    System.out.println("장바구니를 삭제하였습니다.");
  }

  private Cart findByNo(int no) {
    Cart[] arr = cartList.toArray(new Cart[0]);
    for (Cart cart : arr) {
      if (cart.getCartNumber() == no) {
        return cart;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Cart[] arr = cartList.toArray(new Cart[0]);
    for (Cart cart : arr) {
      if (cart.getProductName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public String promptCart(String label) {
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

  public String promptCarts(String label) {
    String carts = "";
    while (true) {
      String cart = Prompt.inputString(label);
      if (this.exist(cart)) {
        if (carts.length() > 0) {
          carts += ",";
        }
        carts += cart;
        continue;
      } else if (cart.length() == 0) {
        break;
      } 
      System.out.println("등록된 상품이 아닙니다.");
    }
    return carts;
  }
}






