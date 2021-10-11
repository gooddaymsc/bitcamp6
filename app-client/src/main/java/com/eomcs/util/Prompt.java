package com.eomcs.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  static Scanner keyboardScan = new Scanner(System.in);

  // 메서드의 접근 범위를 설정하지 않으면 
  // 기본 접근 범위는 같은 패키지 및 하위 클래스 만이 접근할 수 있다.
  // => 다른 패키지에서도 접근할 수 있도록 하려면 public 으로 공개해야 한다.
  public static String inputString(String title) {
    System.out.print(title);
    //    while(true)
    //      if (keyboardScan.nextLine()!="\n") {
    //        return keyboardScan.nextLine();
    //      } else {
    //        System.out.println("공백이 입력됐습니다.\n");
    //      }
    return check(keyboardScan.nextLine());
  }

  public static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  public static Date inputDate(String title) {
    return Date.valueOf(inputString(title));
  }

  public static void close() {
    keyboardScan.close();
  }

  //상품등록-알콜도수 등록시 사용
  public static float inputFloat(String title) {
    return Float.parseFloat(inputString(title));
  }  
  private static String check(String input) {
    if ("\n".equals(input)) {
      System.out.println("공백이 들어갔다.");
      return "공백";
    } else {
      return input;
    }
  }
}









