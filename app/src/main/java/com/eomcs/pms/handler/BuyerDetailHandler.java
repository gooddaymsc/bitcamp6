package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerDetailHandler extends AbstractBuyerHandler {

  public BuyerDetailHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    if (App.getLoginUser().getAuthority()!=Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 상세보기]\n");

      Buyer buyer = (Buyer) findById(App.getLoginUser().getId());
      System.out.printf("이름 : %s\n", buyer.getName());
      System.out.printf("닉네임 : %s\n", buyer.getNickname());
      System.out.printf("이메일 : %s\n", buyer.getEmail());
      System.out.printf("생일 : %s\n", buyer.getBirthday());
      System.out.printf("사진 : %s\n", buyer.getPhoto());
      System.out.printf("전화 : %s\n", buyer.getPhoneNumber());
      System.out.printf("주소 : %s\n", buyer.getAddress());
      System.out.printf("등록일 : %s\n", buyer.getRegisteredDate());
      System.out.printf("권한등급 : %d\n", buyer.getAuthority());
      System.out.println();
      request.setAttribute("buyer", buyer);

      while(true) {
        System.out.println("\n 개인정보변경(U) / 회원탈퇴(D) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        switch (choose) {
          case "u":
          case "U": request.getRequestDispatcher("/buyer/update").forward(request);return;
          case "d":
          case "D": request.getRequestDispatcher("/buyer/delete").forward(request);return;
          case "0": return;
        }
      }

    } else {
      System.out.println("[회원 상세보기]\n");

      Buyer buyer = (Buyer) findById(Prompt.inputString("상세보기할 아이디: "));

      if (buyer == null) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }
      System.out.printf("회원번호 : %s\n", buyer.getNumber());
      System.out.printf("이름 : %s\n", buyer.getName());
      System.out.printf("닉네임 : %s\n", buyer.getNickname());
      System.out.printf("등급 : %s\n", buyer.getLevel());
      System.out.printf("이메일 : %s\n", buyer.getEmail());
      System.out.printf("사진 : %s\n", buyer.getPhoto());
      System.out.printf("전화 : %s\n", buyer.getPhoneNumber());
      System.out.printf("주소 : %s\n", buyer.getAddress());
      System.out.printf("등록일 : %s\n", buyer.getRegisteredDate());
      System.out.println();
      request.setAttribute("buyer", buyer);

      while(true) {
        System.out.println("\n 등급변경(U) / 회원탈퇴(D) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        switch (choose) {
          case "u":
          case "U": request.getRequestDispatcher("/buyer/update").forward(request); return;
          case "d":
          case "D": request.getRequestDispatcher("/buyer/delete").forward(request); return;
          case "0": return;
        }
      }
    }
  }
}







