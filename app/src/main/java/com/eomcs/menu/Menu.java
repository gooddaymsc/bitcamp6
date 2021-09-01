package com.eomcs.menu;

// MenuItem과 MenuGroup을 같은 타입으로 묶기 위해 정의한 클래스이다.
// 이 클래스 자체는 직접 인스턴스를 만들어 사용하기 위함이 아니라
// 상속해주는 용도이기 때문에 추상 클래스로 정의한다.
public abstract class Menu {

  //  public static final int ENABLE_ALL = 0;
  public static final int ENABLE_VISITOR = 0; // 모두 접근 가능
  public static final int ENABLE_PRIVACY = 1;
  public static final int ENABLE_SELLERPRIVACY = 2;
  public static final int ENABLE_ADMIN = 3;

  int enableState;

  String title;

  public Menu(String title) {
    this.title = title;
  }

  public Menu(String title, int enableState) {
    this(title); // 메뉴 이름 설정은 기존 생성자를 통해 처리한다.
    this.enableState = enableState;
  }

  public abstract void execute();
}
