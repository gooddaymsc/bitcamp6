package com.eomcs.menu;

public abstract class Menu {

  public static final int ENABLE_VISITOR = 0;
  public static final int ENABLE_PRIVACY = 1;
  public static final int ENABLE_SELLERPIVACY = 2;
  public static final int ENABLE_ADMIN = 3;


  String title;

  int enableState;

  public Menu(String title) {
    this.title = title;
  }

  public Menu(String title, int enableState) {
    this(title);
    this.enableState = enableState;
  }

  public abstract void execute();
}
