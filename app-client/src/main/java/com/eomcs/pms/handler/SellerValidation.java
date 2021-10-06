package com.eomcs.pms.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.eomcs.util.Prompt;

public class SellerValidation {

  public static String checkPassword(String label) {

    while(true) {

      String passWord = Prompt.inputString(label);

      String regExp_symbol = "([0-9].*[!,@,#,$,%,^,&,*,(,)])|([!,@,#,$,%,^,&,*,(,)].*[0-9])";
      String regExp_alpha = "([a-z].*[A-Z])|([A-Z].*[a-z])";

      Pattern pattern_symbol = Pattern.compile(regExp_symbol);
      Pattern pattern_alpha = Pattern.compile(regExp_alpha);

      Matcher matcher_symbol = pattern_symbol.matcher(passWord);
      Matcher matcher_alpha = pattern_alpha.matcher(passWord);

      if (passWord.length() < 8 || passWord.length() > 12) {
        System.out.println("8 ~ 12자리 이내의 비밀번호를 입력하시오.");
        continue;
      } else {
        if (matcher_symbol.find() == false) {
          System.out.println("알파벳, 숫자 및 특수문자를 포함하시오.");
          continue;
        } else {
          if (matcher_alpha.find() == false) {
            System.out.println("알파벳 대소문자를 적어도 한개씩 포함하시오.");
            continue;
          }
        }
      }
      return passWord;
    }
  }

  public static int checkHour (String label) { 
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1 || num > 24) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      }           
      return num;       
    }
  }

  public static int checkMinute (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 0 || num > 59) {  
        System.out.println("입력하신 수는 유효하지 않습니다.\n"); 
        continue;
      }           
      return num;       
    }
  }

  public static int checkLevel(String label) {
    while(true) {
      int level = Prompt.inputInt(label);
      if (level<1 || level>5) {
        System.out.println("잘못된 등급입니다.\n1부터 5사이 값으로 입력해주세요.\n");
        continue;
      }
      return level;
    }
  }
}
