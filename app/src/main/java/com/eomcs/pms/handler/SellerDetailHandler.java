package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerDetailHandler extends AbstractSellerHandler{

  public SellerDetailHandler(List<Member> memberList) {
    super(memberList);
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 상세보기]");

      Member seller = findById(App.getLoginUser().getId());

      System.out.printf("이름 : %s\n", seller.getName());
      System.out.printf("닉네임 : %s\n", seller.getNickname());
      System.out.printf("이메일 : %s\n", seller.getEmail());
      System.out.printf("생일 : %s\n", seller.getBirthday());
      System.out.printf("사진 : %s\n", seller.getPhoto());
      System.out.printf("전화 : %s\n", seller.getPhoneNumber());
      System.out.printf("가게명 : %s\n", ((Seller) seller).getBusinessName());
      System.out.printf("사업자번호 : %s\n", ((Seller) seller).getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", ((Seller) seller).getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", ((Seller) seller).getBusinessPlaceNumber());
      System.out.printf("등록일 : %s\n", seller.getRegisteredDate());
      System.out.printf("권한등급 : %d\n", seller.getAuthority());
      System.out.println();
      request.setAttribute("seller", seller);

      while(true) {
        System.out.println("개인정보변경(U) / 회원탈퇴(D) / 이전(0)");
        int choose = Prompt.inputInt("선택 > ");
        System.out.println();
        switch (choose) {
          case 1: request.getRequestDispatcher("/seller/update").forward(request); return;
          case 2: request.getRequestDispatcher("/seller/delete").forward(request); return;
          case 0: return;
        }
      }
    } else {
      System.out.println("[판매자 상세보기]");

      Member seller = findById(Prompt.inputString("상세보기할 판매자 아이디: "));

      if (seller == null) {
        System.out.println("해당 아이디의 판매자가 없습니다.\n");
        return;
      }
      System.out.printf("이름 : %s\n", seller.getName());
      System.out.printf("닉네임 : %s\n", seller.getNickname());
      System.out.printf("등급 : %s\n", seller.getLevel());
      System.out.printf("이메일 : %s\n", seller.getEmail());
      System.out.printf("사진 : %s\n", seller.getPhoto());
      System.out.printf("전화 : %s\n", seller.getPhoneNumber());
      System.out.printf("가게명 : %s\n", ((Seller) seller).getBusinessName());
      System.out.printf("사업자번호 : %s\n", ((Seller) seller).getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", ((Seller) seller).getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", ((Seller) seller).getBusinessPlaceNumber());
      System.out.println();
      request.setAttribute("seller", seller);

      while(true) {
        System.out.println("등급변경(U) / 회원탈퇴(D) / 이전(0)");
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







