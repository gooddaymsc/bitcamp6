package com.eomcs.pms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.CartHandler;
import com.eomcs.util.Prompt;

public class App {
  List<Board> boardList = new ArrayList<>();
  List<Cart> cartList = new LinkedList<>();

  BoardHandler boardHandler = new BoardHandler(boardList);
  Cart cartHandler = new Cart(cartList);

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

    MenuGroup boardMenu = new MenuGroup("게시판");
    mainMenuGroup.add(boardMenu);

    boardMenu.add(new Menu("등록") {
      public void execute() {
        boardHandler.add(); 
      }});
    boardMenu.add(new Menu("목록") {
      public void execute() {
        boardHandler.list(); 
      }});
    boardMenu.add(new Menu("상세보기") {
      public void execute() {
        boardHandler.detail(); 
      }});
    boardMenu.add(new Menu("변경") {
      public void execute() {
        boardHandler.update(); 
      }});
    boardMenu.add(new Menu("삭제") {
      public void execute() {
        boardHandler.delete(); 
      }});

    MenuGroup memberMenu = new MenuGroup("회원");
    mainMenuGroup.add(memberMenu);

    memberMenu.add(new Menu("등록") {
      public void execute() {
        cartHandler.add(); 
      }});
    memberMenu.add(new Menu("목록") {
      public void execute() {
        cartHandler.list(); 
      }});
    memberMenu.add(new Menu("상세보기") {
      public void execute() {
        cartHandler.detail(); 
      }});
    memberMenu.add(new Menu("변경") {
      public void execute() {
        cartHandler.update(); 
      }});
    memberMenu.add(new Menu("삭제") {
      public void execute() {
        cartHandler.delete(); 
      }});

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    mainMenuGroup.add(projectMenu);

    MenuGroup taskMenu = new MenuGroup("작업");
    mainMenuGroup.add(taskMenu);

    MenuGroup mg1 = new MenuGroup("관리1");
    mainMenuGroup.add(mg1);

    MenuGroup mg2 = new MenuGroup("관리2");
    mg1.add(mg2);

    MenuGroup mg3 = new MenuGroup("관리3");
    mg2.add(mg3);

    return mainMenuGroup;
  }
}












