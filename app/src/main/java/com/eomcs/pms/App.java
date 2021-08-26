package com.eomcs.pms;

import java.util.LinkedList;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
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

    MenuGroup mg1 = new MenuGroup("회원정보");
    mainMenuGroup.add(mg1);

    MenuGroup memberMenu = new MenuGroup("일반회원");
    mg1.add(memberMenu);

    memberMenu.add(new Menu("등록") {
      public void execute() {
        memberHandler.add(); 
      }});
    memberMenu.add(new Menu("목록") {
      public void execute() {
        memberHandler.list(); 
      }});
    memberMenu.add(new Menu("상세보기") {
      public void execute() {
        memberHandler.detail(); 
      }});
    memberMenu.add(new Menu("변경") {
      public void execute() {
        memberHandler.update(); 
      }});
    memberMenu.add(new Menu("삭제") {
      public void execute() {
        memberHandler.delete(); 
      }});

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
    return mainMenuGroup;
  }
}












