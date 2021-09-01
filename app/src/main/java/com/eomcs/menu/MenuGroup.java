package com.eomcs.menu;

import java.util.ArrayList;
import java.util.Stack;
import com.eomcs.pms.App;
import com.eomcs.util.Prompt;

public class MenuGroup extends Menu {


  static Stack<Menu> breadCrumb = new Stack<>();

  Menu[] childs = new Menu[100];
  int size;
  boolean disablePrevMenu;
  String prevMenuTitle = "이전 메뉴";

  private static class PrevMenu extends Menu {
    public PrevMenu() {
      super("");
    }
    @Override
    public void execute() {      
    }
  }
  static PrevMenu prevMenu = new PrevMenu();


  public MenuGroup(String title) {
    super(title);
  }

  public MenuGroup(String title, boolean disablePrevMenu) {
    super(title);
    this.disablePrevMenu = disablePrevMenu;
  }

  public void setPrevMenuTitle(String prevMenuTitle) {
    this.prevMenuTitle = prevMenuTitle;
  }

  // MenuGroup이 포함하는 하위 Menu를 다룰 수 있도록 메서드를 정의한다.
  public void add(Menu child) {
    if (this.size == this.childs.length) {
      return; // 하위 메뉴를 저장하는 배열이 꽉 찼다면 더이상 저장해서는 안된다.
    }
    this.childs[this.size++] = child; 
  }

  // 배열에 들어 있는 Menu 객체를 찾아 제거한다.
  public Menu remove(Menu child) {
    int index = indexOf(child);
    if (index == -1) {
      return null;
    }
    for (int i = index + 1; i < this.size; i++) {
      this.childs[i - 1] = this.childs[i];
    }
    childs[--this.size] = null;
    return child;
  }

  // 배열에 들어 있는 Menu 객체의 인덱스를 알아낸다.
  public int indexOf(Menu child) {
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i] == child) {
        return i;
      }
    }
    return -1;
  }

  // 배열에 들어 있는 Menu 객체를 찾는다.
  public Menu getMenu(String title) { 
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i].title.equals(title)) {
        return this.childs[i];
      }
    }
    return null;
  }

  @Override // 컴파일러에게 오버라이딩을 제대로 하는지 조사해 달라고 요구한다.
  public void execute() {
    // 현재 실행하는 메뉴를 스택에 보관한다.
    breadCrumb.push(this);

    while (true) {
      ArrayList<Menu> menuList = new ArrayList<>();
      System.out.printf("\n[%s]\n", getBreadCrumb());

      for (int i = 0; i < this.size; i++) {

        if (this.childs[i].enableState == Menu.ENABLE_PRIVACY &&
            App.getLoginUser().getAuthority() == 1) {
          menuList.add(this.childs[i]);

        } else if (this.childs[i].enableState == Menu.ENABLE_SELLERPIVACY &&
            App.getLoginUser().getAuthority() == 2) {
          menuList.add(this.childs[i]);

        } else if (this.childs[i].enableState == Menu.ENABLE_ADMIN &&
            App.getLoginUser().getAuthority() == 3) {
          menuList.add(this.childs[i]);

        } else if (this.childs[i].enableState == Menu.ENABLE_VISITOR) {
          menuList.add(this.childs[i]);
        }

        //        System.out.printf("%d. %s\n", i + 1, this.childs[i].title);
      }

      int i = 1;
      for (Menu menu : menuList) {
        System.out.printf("%d. %s\n", i++, menu.title);
      }

      if (!disablePrevMenu) {
        System.out.printf("0. %s\n", this.prevMenuTitle);
      }

      //      try {
      int menuNo = Prompt.inputInt("선택> ");
      if (menuNo == 0 && !disablePrevMenu) {
        // 현재 메뉴에서 나갈 때 스택에서 제거한다.
        breadCrumb.pop();
        return;
      }

      if (menuNo < 0 || menuNo > this.size) {
        System.out.println("무효한 메뉴 번호입니다.");
        continue;
      }

      this.childs[menuNo - 1].execute();


      //      menuList.get(menuNo - 1).execute();



      //    } 
      //      catch (Exception e) {
      //        // try 블록 안에 있는 코드를 실행하다가 예외가 발생하면
      //        // 다음 문장을 실행한 후 시스템을 멈추지 않고 실행을 계속한다.
      //        System.out.println("--------------------------------------------------------------");
      //        System.out.printf("오류 발생: %s\n", e.getClass().getName());
      //        System.out.println("--------------------------------------------------------------");
      //      }
    }
  }

  private String getBreadCrumb() {
    String path = "";

    for (int i = 0; i < breadCrumb.size(); i++) {
      if (path.length() > 0) {
        path += " / ";
      }
      Menu menu = breadCrumb.get(i); 
      path += menu.title;
    }

    return path;
  }

}







