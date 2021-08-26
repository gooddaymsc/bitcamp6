package com.eomcs.pms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.domain.Alcohol;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Book;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Member2;
import com.eomcs.pms.domain.Person;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.handler.AlcoholHandler;
import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.BookHandler;
import com.eomcs.pms.handler.CartHandler;
import com.eomcs.pms.handler.Member2Handler;
import com.eomcs.pms.handler.MemberHandler;
import com.eomcs.pms.handler.PersonHandler;
import com.eomcs.pms.handler.StockHandler;
import com.eomcs.util.Prompt;

public class App {
  // 7개 CRUD 
  // 개인회원 정보
  List<Member> memberList = new LinkedList<>();
  List<Member2> member2List = new LinkedList<>();
  // 일반회원
  List<Board> boardList = new ArrayList<>();
  List<Book> bookList = new LinkedList<>();
  List<Cart> cartList = new ArrayList<>();
  // 판매자
  List<Alcohol> alcoholList = new ArrayList<>();
  List<Stock> stockList = new ArrayList<>();
  // 관리자
  List<Person> personList = new LinkedList<>();


  MemberHandler memberHandler = new MemberHandler(memberList);
  Member2Handler member2Handler = new Member2Handler(member2List);
  BoardHandler boardHandler = new BoardHandler(boardList);
  BookHandler bookHandler = new BookHandler(bookList);
  CartHandler cartHandler = new CartHandler(cartList);
  AlcoholHandler alcoholHandler = new AlcoholHandler(alcoholList);
  StockHandler stockHandler = new StockHandler(stockList);
  PersonHandler personHandler = new PersonHandler(personList);


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

    MenuGroup subMenu = new MenuGroup("회원정보");
    mainMenuGroup.add(subMenu);

    MenuGroup memberMenu = new MenuGroup("일반회원");
    subMenu.add(memberMenu);

    memberMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        memberHandler.add(); 
      }});
    memberMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        memberHandler.list(); 
      }});
    memberMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        memberHandler.detail(); 
      }});
    memberMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        memberHandler.update(); 
      }});
    memberMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        memberHandler.delete(); 
      }});

    MenuGroup member2Menu = new MenuGroup("판매자");
    subMenu.add(member2Menu);

    member2Menu.add(new Menu("등록") {
      @Override
      public void execute() {
        member2Handler.add(); 
      }});
    member2Menu.add(new Menu("목록") {
      @Override
      public void execute() {
        member2Handler.list(); 
      }});
    member2Menu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        member2Handler.detail(); 
      }});
    member2Menu.add(new Menu("변경") {
      @Override
      public void execute() {
        member2Handler.update(); 
      }});
    member2Menu.add(new Menu("삭제") {
      @Override
      public void execute() {
        member2Handler.delete(); 
      }});

    MenuGroup boardMenu = new MenuGroup("게시판");
    mainMenuGroup.add(boardMenu);

    boardMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        boardHandler.add(); 
      }});
    boardMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        boardHandler.list(); 
      }});
    boardMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        boardHandler.detail(); 
      }});
    boardMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        boardHandler.update(); 
      }});
    boardMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        boardHandler.delete(); 
      }});

    MenuGroup bookMenu = new MenuGroup("예약");
    mainMenuGroup.add(bookMenu);

    bookMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        bookHandler.add(); 
      }});
    bookMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        bookHandler.list(); 
      }});
    bookMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        bookHandler.detail(); 
      }});
    bookMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        bookHandler.update(); 
      }});
    memberMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        bookHandler.delete(); 
      }});

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

    MenuGroup alcoholMenu = new MenuGroup("상품정보");
    mainMenuGroup.add(alcoholMenu);

    alcoholMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        alcoholHandler.add(); 
      }});
    alcoholMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        alcoholHandler.list(); 
      }});
    alcoholMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        alcoholHandler.detail(); 
      }});
    alcoholMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        alcoholHandler.update(); 
      }});
    memberMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        memberHandler.delete(); 
      }});


    MenuGroup stockMenu = new MenuGroup("재구");
    mainMenuGroup.add(stockMenu);

    stockMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        stockHandler.add(); 
      }});
    stockMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        stockHandler.list(); 
      }});
    stockMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        stockHandler.detail(); 
      }});
    stockMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        stockHandler.update(); 
      }});
    stockMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        stockHandler.delete(); 
      }});

    MenuGroup personMenu = new MenuGroup("회원관리");
    mainMenuGroup.add(personMenu);

    personMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        personHandler.add(); 
      }});
    personMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        personHandler.list(); 
      }});
    personMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        personHandler.detail(); 
      }});
    personMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        personHandler.update(); 
      }});
    personMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        personHandler.delete(); 
      }});

    return mainMenuGroup;
  }
}











