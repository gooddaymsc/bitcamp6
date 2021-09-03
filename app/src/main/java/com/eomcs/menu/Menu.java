package com.eomcs.menu;

public abstract class Menu {


  public static final int ACCESS_LOGOUT = 0x01;
  public static final int ACCESS_PRIVACY = 0x02;
  public static final int ACCESS_SELLER = 0x04;
  public static final int ACCESS_ADMIN = 0x08;


  String title;

  int accessScope;

  public Menu(String title) {
    this(title, ACCESS_LOGOUT | ACCESS_PRIVACY | ACCESS_SELLER | ACCESS_ADMIN );
  }

  public Menu(String title, int accessScope) {
    this.title = title;
    this.accessScope = accessScope;
  }

  public abstract void execute();
}
