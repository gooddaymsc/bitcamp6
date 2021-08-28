package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

public class BookingHandler {

  List<Booking> booktList;

  public BookingHandler(List<Booking> bookList) {
    this.booktList = bookList;
  }

  public void add(int i, int auth) {
    if (auth == 0 || auth == 3) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[예약 등록]");

    Booking booking = new Booking();

    booking.setBookingNumber(Prompt.inputInt("번호? "));
    booking.setProductName(Prompt.inputString("예약명? "));
    booking.setProductType(Prompt.inputString("종류? "));
    booking.setCountryOrigin(Prompt.inputString("원산지? "));
    booking.setProductPhoto(Prompt.inputString("사진? "));
    booking.setPrice(Prompt.inputString("가격? "));
    booking.setReservation(Prompt.inputString("예약시간? "));
    booking.setRegisteredDate(new Date(System.currentTimeMillis()));


    booktList.add(booking);
  }

  public void list(int i, int auth) {
    if (auth == 0 || auth == 3) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 목록]");

    Booking[] list = booktList.toArray(new Booking[0]);

    for (Booking book : list) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", 
          book.getBookingNumber(), 
          book.getProductName(), 
          book.getProductType(), 
          book.getCountryOrigin(), 
          book.getProductPhoto(), 
          book.getPrice(), 
          book.getReservation());
    }
  }

  public void detail(int i, int auth) {
    if (auth == 0 || auth == 3) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Booking Book = findByNo(no);

    if (Book == null) {
      System.out.println("해당 번호의 예약이 없습니다.");
      return;
    }

    System.out.printf("예약명: %s\n", Book.getProductName());
    System.out.printf("종류: %s\n", Book.getProductType());
    System.out.printf("원산지: %s\n", Book.getCountryOrigin());
    System.out.printf("사진: %s\n", Book.getProductPhoto());
    System.out.printf("가격: %s\n", Book.getPrice());
    System.out.printf("예약시간: %s\n", Book.getReservation());
  }

  public void update(int i, int auth) {
    if (auth == 0 || auth == 3) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 변경]");
    int no = Prompt.inputInt("번호? ");

    Booking Book = findByNo(no);

    if (Book == null) {
      System.out.println("해당 번호의 예약이 없습니다.");
      return;
    }

    String name = Prompt.inputString("예약명(" + Book.getProductName()  + ")? ");
    String kind = Prompt.inputString("종류(" + Book.getProductType() + ")? ");
    String madeIn = Prompt.inputString("원산지(" + Book.getCountryOrigin() + ")? ");
    String photo = Prompt.inputString("사진(" + Book.getProductPhoto() + ")? ");
    String price = Prompt.inputString("가격(" + Book.getPrice() + ")? ");
    String book = Prompt.inputString("예약시간(" + Book.getReservation() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("예약 변경을 취소하였습니다.");
      return;
    }

    Book.setProductName(name);
    Book.setProductType(kind);
    Book.setCountryOrigin(madeIn);
    Book.setProductPhoto(photo);
    Book.setPrice(price);
    Book.setReservation(book);

    System.out.println("예약을 변경하였습니다.");
  }

  public void delete(int i, int auth) {
    if (auth == 0 || auth == 3) {
      System.out.println("권한이 없습니다.구매자 또는 판매자 기능입니다.");
      return;
    }
    System.out.println("[예약 삭제]");
    int no = Prompt.inputInt("번호? ");

    Booking Book = findByNo(no);

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

  private Booking findByNo(int no) {
    Booking[] arr = booktList.toArray(new Booking[0]);
    for (Booking Book : arr) {
      if (Book.getBookingNumber() == no) {
        return Book;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Booking[] arr = booktList.toArray(new Booking[0]);
    for (Booking Book : arr) {
      if (Book.getProductName().equals(name)) {
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






