package com.eomcs.menu;

public abstract class Menu {


  public static final int ACCESS_LOGOUT = 0x01; //1
  public static final int ACCESS_BUYER = 0x02; //2
  public static final int ACCESS_SELLER = 0x04; //4
  public static final int ACCESS_ADMIN = 0x08; //8


  String title;

  int accessScope;

  public Menu(String title) {
    this(title, ACCESS_LOGOUT | ACCESS_BUYER | ACCESS_SELLER | ACCESS_ADMIN );
  }

  public Menu(String title, int accessScope) {
    this.title = title;
    this.accessScope = accessScope;
  }

  public int getAccessScope() {
    return this.accessScope;
  }

  public abstract void execute();
}
