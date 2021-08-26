package com.eomcs.pms;

import java.util.LinkedList;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
<<<<<<< HEAD
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
=======
import com.eomcs.pms.domain.Member;
<<<<<<< HEAD
import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.MemberHandler;
=======
import com.eomcs.pms.domain.Member2;
import com.eomcs.pms.handler.MemberHandler;
import com.eomcs.pms.handler.MemberHandler2;
>>>>>>> 6c4f183e47537657ddd2ba48dd4a1f0dc247d036
import com.eomcs.util.Prompt;

public class App {
  List<Member> memberList = new LinkedList<>();
<<<<<<< HEAD
=======
  List<Member2> memberList2 = new LinkedList<>();
>>>>>>> 6c4f183e47537657ddd2ba48dd4a1f0dc247d036

  MemberHandler memberHandler = new MemberHandler(memberList);
<<<<<<< HEAD

=======
  MemberHandler2 memberHandler2 = new MemberHandler2(memberList2);
>>>>>>> 6c4f183e47537657ddd2ba48dd4a1f0dc247d036
>>>>>>> fe7e5918fafaa42a973008c9d27ed9f478fa92b7

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

<<<<<<< HEAD
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
=======
    MenuGroup mg1 = new MenuGroup("회원정보");
    mainMenuGroup.add(mg1);

    MenuGroup memberMenu = new MenuGroup("일반회원");
    mg1.add(memberMenu);
>>>>>>> fe7e5918fafaa42a973008c9d27ed9f478fa92b7

    memberMenu.add(new Menu("등록") {
      public void execute() {
        bookHandler.add(); 
      }});
    memberMenu.add(new Menu("목록") {
      public void execute() {
        bookHandler.list(); 
      }});
    memberMenu.add(new Menu("상세보기") {
      public void execute() {
        bookHandler.detail(); 
      }});
    memberMenu.add(new Menu("변경") {
      public void execute() {
        bookHandler.update(); 
      }});
    memberMenu.add(new Menu("삭제") {
      public void execute() {
        bookHandler.delete(); 
      }});

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
    MenuGroup member2Menu = new MenuGroup("판매자");
    mg1.add(member2Menu);

    member2Menu.add(new Menu("등록") {
      public void execute() {
        memberHandler2.add(); 
      }});
    member2Menu.add(new Menu("목록") {
      public void execute() {
        memberHandler2.list(); 
      }});
    member2Menu.add(new Menu("상세보기") {
      public void execute() {
        memberHandler2.detail(); 
      }});
    member2Menu.add(new Menu("변경") {
      public void execute() {
        memberHandler2.update(); 
      }});
    member2Menu.add(new Menu("삭제") {
      public void execute() {
        memberHandler2.delete(); 
      }});

    /*
    MenuGroup mg1 = new MenuGroup("관리1");
    mainMenuGroup.add(mg1);

    MenuGroup mg2 = new MenuGroup("관리2");
    mg1.add(mg2);

    MenuGroup mg3 = new MenuGroup("관리3");
    mg2.add(mg3);
     */
>>>>>>> 6c4f183e47537657ddd2ba48dd4a1f0dc247d036
>>>>>>> fe7e5918fafaa42a973008c9d27ed9f478fa92b7
    return mainMenuGroup;
  }
}












