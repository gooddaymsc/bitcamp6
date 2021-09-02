package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;

public abstract class AbstractBookingHandler implements Command {

  List<Booking> bookList;
  List<Cart> cartList;

  public AbstractBookingHandler(List<Booking> bookList, List<Cart> cartList) {
    this.bookList = bookList;
    this.cartList = cartList;
  }

  protected Booking findByNo(int no) {
    for (Booking Book : bookList) {
      if (Book.getBookingNumber() == no) {
        return Book;
      }
    }
    return null;
  }

  protected Cart findStock(String name) {
    for (Cart cart : cartList) {
      if (cart.getStock().getProduct().getProductName().equals(name)) {
        return cart;
      }
    }
    return null;
  }

  protected Booking findBooking(String name) {
    for (Booking book : bookList) {
      if (book.getCart().getStock().getProduct().getProductName().equals(name)) {
        return book;
      }
    }
    return null;
  }
}






