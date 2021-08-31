package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Book;
import com.eomcs.util.Prompt;

public class BookHandler {

  List<Book> booktList;

  public BookHandler(List<Book> bookList) {
    this.booktList = bookList;
  }

  public void add() {
    System.out.println("[예약 등록]");

    Book book = new Book();

    book.setNo(Prompt.inputInt("번호를 입력해주세요: "));
    book.setName(Prompt.inputString("예약을 입력해주세요: "));
    book.setKind(Prompt.inputString("종류를 입력해주세요: "));
    book.setMadeIn(Prompt.inputString("원산지를 입력해주세요: "));
    book.setPhoto(Prompt.inputString("사진을 등록해주세요: "));
    book.setPrice(Prompt.inputString("가격을 입력해주세요: "));
    book.setBook(Prompt.inputString("예약시간을 입력해주세요: "));

    booktList.add(book);
  }

  public void list() {
    System.out.println("[예약 목록]");

    Book[] list = booktList.toArray(new Book[0]);

    for (Book book : list) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", 
          book.getNo(), 
          book.getName(), 
          book.getKind(), 
          book.getMadeIn(), 
          book.getPhoto(), 
          book.getPrice(), 
          book.getBook());
    }
  }

  public void detail() {
    System.out.println("[예약 상세보기]");
    int no = Prompt.inputInt("번호를 입력해주세요: ");

    Book Book = findByNo(no);

    if (Book == null) {
      System.out.println("해당 번호의 예약이 없습니다.");
      return;
    }

    System.out.printf("예약명: %s입니다.\n", Book.getName());
    System.out.printf("종류: %s입니다.\n", Book.getKind());
    System.out.printf("원산지: %s입니다.\n", Book.getMadeIn());
    System.out.printf("사진: %s입니다.\n", Book.getPhoto());
    System.out.printf("가격: %s입니다.\n", Book.getPrice());
    System.out.printf("예약시간: %s입니다.\n", Book.getBook());
  }

  public void update() {
    System.out.println("[예약 변경]");
    int no = Prompt.inputInt("번호를 입력해주세요: ");

    Book Book = findByNo(no);

    if (Book == null) {
      System.out.println("해당 번호의 예약이 없습니다.");
      return;
    }

    String name = Prompt.inputString("변경후의 예약명을 입력해주세요.(" + Book.getName()  + ") ");
    String kind = Prompt.inputString("변경후의 종류를 입력해주세요.(" + Book.getKind() + ") ");
    String madeIn = Prompt.inputString("변경후의 원산지를 입력해주세요.(" + Book.getMadeIn() + ") ");
    String photo = Prompt.inputString("변경후의 사진을 등록해주세요.(" + Book.getPhoto() + ") ");
    String price = Prompt.inputString("변경후의 가격을 입력해주세요.(" + Book.getPrice() + ") ");
    String book = Prompt.inputString("변경후의 예약시간을 입력해주세요.(" + Book.getBook() + ") ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("예약 변경을 취소하였습니다.");
      return;
    }

    Book.setName(name);
    Book.setKind(kind);
    Book.setMadeIn(madeIn);
    Book.setPhoto(photo);
    Book.setPrice(price);
    Book.setBook(book);

    System.out.println("예약을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[예약 삭제]");
    int no = Prompt.inputInt("번호를 입력해주세요: ");

    Book Book = findByNo(no);

    if (Book == null) {
      System.out.println("해당 번호의 예약이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("예약 삭제를 취소하였습니다.");
      return;
    }

    booktList.remove(Book);

    System.out.println("예약을 삭제하였습니다.");
  }

  private Book findByNo(int no) {
    Book[] arr = booktList.toArray(new Book[0]);
    for (Book Book : arr) {
      if (Book.getNo() == no) {
        return Book;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Book[] arr = booktList.toArray(new Book[0]);
    for (Book Book : arr) {
      if (Book.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public String promptBook(String label) {
    while (true) {
      String owner = Prompt.inputString(label);
      if (this.exist(owner)) {
        return owner;
      } else if (owner.length() == 0) {
        return null;
      }
      System.out.println("등록된 예약이 아닙니다.");
    }
  }

  public String promptBooks(String label) {
    String Books = "";
    while (true) {
      String Book = Prompt.inputString(label);
      if (this.exist(Book)) {
        if (Books.length() > 0) {
          Books += ",";
        }
        Books += Book;
        continue;
      } else if (Book.length() == 0) {
        break;
      } 
      System.out.println("등록된 예약이 아닙니다.");
    }
    return Books;
  }
}







