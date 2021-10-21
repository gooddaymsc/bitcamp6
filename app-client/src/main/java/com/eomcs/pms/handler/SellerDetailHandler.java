package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerDetailHandler implements Command {
  SellerDao sellerDao;
  public SellerDetailHandler(SellerDao sellerDao) {
    this.sellerDao = sellerDao;
  } 


  @Override
  public void execute(CommandRequest request) throws Exception {
    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 상세보기]");
      String id = ClientApp.getLoginUser().getId();

      Seller seller = sellerDao.findById(id);

      System.out.printf("이름 : %s\n", seller.getMember().getName());
      System.out.printf("닉네임 : %s\n", seller.getMember().getNickname());
      System.out.printf("등급 : %d\n", seller.getMember().getLevel());
      System.out.printf("이메일 : %s\n", seller.getMember().getEmail());
      System.out.printf("생일 : %s\n", seller.getMember().getBirthday());
      System.out.printf("사진 : %s\n", seller.getMember().getPhoto());
      System.out.printf("전화 : %s\n", seller.getMember().getPhoneNumber());
      System.out.printf("가게명 : %s\n", seller.getBusinessName());
      System.out.printf("사업자번호 : %s\n", seller.getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", seller.getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", seller.getBusinessPlaceNumber());
      System.out.printf("오픈시간: %s\n", seller.getBusinessOpeningTime());
      System.out.printf("마감시간: %s\n", seller.getBusinessClosingTime());
      System.out.printf("등록일 : %s\n", seller.getMember().getRegisteredDate());
      System.out.printf("권한 : %d\n", seller.getMember().getAuthority());
      System.out.println();

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
      String id = (String)request.getAttribute("id");

      Seller seller = sellerDao.findById(id);

      if (seller == null) {
        System.out.println("해당 아이디를 갖는 회원이 없습니다.\n");
        return;
      }
      System.out.printf("이름 : %s\n", seller.getMember().getName());
      System.out.printf("닉네임 : %s\n", seller.getMember().getNickname());
      System.out.printf("등급 : %s\n", seller.getMember().getLevel());
      System.out.printf("이메일 : %s\n", seller.getMember().getEmail());
      System.out.printf("사진 : %s\n", seller.getMember().getPhoto());
      System.out.printf("전화 : %s\n", seller.getMember().getPhoneNumber());
      System.out.printf("가게명 : %s\n", seller.getBusinessName());
      System.out.printf("사업자번호 : %s\n", seller.getBusinessNumber());
      System.out.printf("사업장주소 : %s\n", seller.getBusinessAddress());
      System.out.printf("사업장번호 : %s\n", seller.getBusinessPlaceNumber());
      System.out.printf("오픈시간: %s\n", seller.getBusinessOpeningTime());
      System.out.printf("마감시간: %s\n", seller.getBusinessClosingTime());

      request.setAttribute("id", seller.getMember().getId());

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

