package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartHandler {

  List<Cart> cartList;

  public CartHandler(List<Cart> cartList) {
    this.cartList = cartList;
  }

  public void add() {
    System.out.println("[장바구니 등록]");

    Cart cart = new Cart();

    cart.setNo(Prompt.inputInt("번호? "));
    cart.setName(Prompt.inputString("상품명? "));
    cart.setKind(Prompt.inputString("종류? "));
    cart.setMadeIn(Prompt.inputString("원산지? "));
    cart.setPhoto(Prompt.inputString("사진? "));
    cart.setPrice(Prompt.inputString("가격? "));
    cart.setRegisteredDate(new Date(System.currentTimeMillis()));

    cartList.add(cart);
  }

  public void list() {
    System.out.println("[장바구니 목록]");

    Cart[] list = cartList.toArray(new Cart[0]);

    for (Cart cart : list) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          cart.getNo(), 
          cart.getName(), 
          cart.getKind(), 
          cart.getMadeIn(), 
          cart.getRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("[장바구니 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Cart cart = findByNo(no);

    if (cart == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    System.out.printf("상품명: %s\n", cart.getName());
    System.out.printf("종류: %s\n", cart.getKind());
    System.out.printf("원산지: %s\n", cart.getMadeIn());
    System.out.printf("사진: %s\n", cart.getPhoto());
    System.out.printf("가격: %s\n", cart.getPrice());
    System.out.printf("등록일: %s\n", cart.getRegisteredDate());
  }

  public void update() {
    System.out.println("[장바구니 변경]");
    int no = Prompt.inputInt("번호? ");

    Cart cart = findByNo(no);

    if (cart == null) {
      System.out.println("해당 번호의 상품이 없습니다.");
      return;
    }

    String name = Prompt.inputString("상품명(" + cart.getName()  + ")? ");
    String kind = Prompt.inputString("종류(" + cart.getKind() + ")? ");
    String madeIn = Prompt.inputString("원산지(" + cart.getMadeIn() + ")? ");
    String photo = Prompt.inputString("사진(" + cart.getPhoto() + ")? ");
    String price = Prompt.inputString("가격(" + cart.getPrice() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("장바구니 변경을 취소하였습니다.");
      return;
    }

    cart.setName(name);
    cart.setKind(kind);
    cart.setMadeIn(madeIn);
    cart.setPhoto(photo);
    cart.setPrice(price);

    System.out.println("장바구니를 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[장바구니 삭제]");
    int no = Prompt.inputInt("번호? ");

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
      if (cart.getNo() == no) {
        return cart;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Cart[] arr = cartList.toArray(new Cart[0]);
    for (Cart cart : arr) {
      if (cart.getName().equals(name)) {
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






