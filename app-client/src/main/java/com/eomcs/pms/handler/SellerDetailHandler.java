package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Seller;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class SellerDetailHandler implements Command {
  RequestAgent requestAgent;
  public SellerDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }  


  @Override
  public void execute(CommandRequest request) throws Exception {
    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 상세보기]");
      String id = ClientApp.getLoginUser().getId();

      HashMap<String, String> params = new HashMap<>();
      params.put("id", id);

      requestAgent.request("seller.selectOne", params);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }

      Seller seller = requestAgent.getObject(Seller.class);

      System.out.printf("이름 : %s\n", seller.getName());
      System.out.printf("닉네임 : %s\n", seller.getNickname());
      System.out.printf("이메일 : %s\n", seller.getEmail());
      System.out.printf("생일 : %s\n", seller.getBirthday());
      System.out.printf("사진 : %s\n", seller.getPhoto());
      System.out.printf("전화 : %s\n", seller.getPhoneNumber());
      System.out.printf("가게명 : %s\n", seller.getBusinessName());
      System.out.printf("사업자번호 : %s\n", seller.getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", seller.getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", seller.getBusinessPlaceNumber());
      System.out.printf("오픈시간: %s시 %s분\n", 
          seller.getBusinessOpeningHours(), seller.getBusinessOpeningMinutes());
      System.out.printf("마감시간: %s시 %s분\n", 
          seller.getBusinessClosingHours() ,seller.getBusinessClosingMinutes());
      System.out.printf("등록일 : %s\n", seller.getRegisteredDate());
      System.out.printf("권한등급 : %d\n", seller.getAuthority());
      System.out.println();
      request.setAttribute("seller", seller);

      while(true) {
        System.out.println("개인정보변경(U) / 회원탈퇴(D) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "U":
          case "u": request.getRequestDispatcher("/seller/update").forward(request); return;
          case "D":
          case "d": request.getRequestDispatcher("/seller/delete").forward(request); return;
          case "0": return;
        }
      }
    } else {
      System.out.println("[판매자 상세보기] || 이전(0)");
      String id = (String)request.getAttribute("Id");

      HashMap<String, String> params = new HashMap<>();
      params.put("id", id);

      requestAgent.request("seller.selectOne", params);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }

      Seller seller = requestAgent.getObject(Seller.class);

      System.out.printf("이름 : %s\n", seller.getName());
      System.out.printf("닉네임 : %s\n", seller.getNickname());
      System.out.printf("등급 : %s\n", seller.getLevel());
      System.out.printf("이메일 : %s\n", seller.getEmail());
      System.out.printf("사진 : %s\n", seller.getPhoto());
      System.out.printf("전화 : %s\n", seller.getPhoneNumber());
      System.out.printf("가게명 : %s\n", seller.getBusinessName());
      System.out.printf("사업자번호 : %s\n", seller.getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", seller.getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", seller.getBusinessPlaceNumber());
      System.out.printf("오픈시간: %s시 %s분\n", 
          seller.getBusinessOpeningHours(), seller.getBusinessOpeningMinutes());
      System.out.printf("마감시간: %s시 %s분\n", 
          seller.getBusinessClosingHours() ,seller.getBusinessClosingMinutes());

      request.setAttribute("seller", seller);

      while(true) {
        System.out.println("\n등급변경(U) / 회원탈퇴(D)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "u":
          case "U": request.getRequestDispatcher("/seller/update").forward(request); return;
          case "d":
          case "D": request.getRequestDispatcher("/seller/delete").forward(request); return;
          case "0": return;
        }
      }
    }
  }
}







