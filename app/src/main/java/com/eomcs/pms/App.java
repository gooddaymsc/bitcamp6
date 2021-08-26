package com.eomcs.pms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.domain.Book;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.handler.BookHandler;
import com.eomcs.pms.handler.CartHandler;
import com.eomcs.util.Prompt;

public class App {
  List<Cart> cartList = new ArrayList<>();
  List<Book> bookList = new LinkedList<>();

  CartHandler cartHandler = new CartHandler(cartList);
  BookHandler bookHandler = new BookHandler(bookList);

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    MenuGroup cartMenu = new MenuGroup("장바구니");
    mainMenuGroup.add(cartMenu);

    cartMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        cartHandler.add(); 
      }});
    cartMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        cartHandler.list(); 
      }});
    cartMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        cartHandler.detail(); 
      }});
    cartMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        cartHandler.update(); 
      }});
    cartMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        cartHandler.delete(); 
      }});

    MenuGroup memberMenu = new MenuGroup("예약");
    mainMenuGroup.add(memberMenu);

    memberMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        bookHandler.add(); 
      }});
    memberMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        bookHandler.list(); 
      }});
    memberMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        bookHandler.detail(); 
      }});
    memberMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        bookHandler.update(); 
      }});
    memberMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        bookHandler.delete(); 
      }});

    return mainMenuGroup;
  }
}












