package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.util.Prompt;

public class BookingDetailHandler implements Command {
  BookingDao bookingDao;
  BuyerDao buyerDao;

  public BookingDetailHandler(BookingDao bookingDao, BuyerDao buyerDao) {
    this.bookingDao = bookingDao;
    this.buyerDao = buyerDao;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    if (ClientApp.getLoginUser().getAuthority()==Menu.ACCESS_BUYER) {
      System.out.println("[내 픽업 예약 상세보기]");

      int No = Prompt.inputInt("예약번호 :");
      Booking booking = bookingDao.findByNo1(No, nowLoginId);

      if (booking == null) {
        System.out.println("해당 상품의 예약이 없습니다.");
        return;
      }

      String sellerId = booking.getCart().getStock().getSeller().getMember().getId();

      System.out.printf("가게명: %s\n", booking.getCart().getStock().getSeller().getBusinessName());
      System.out.printf("판매자: %s\n", sellerId);
      System.out.printf("가게주소: %s\n", booking.getCart().getStock().getSeller().getBusinessAddress());
      System.out.printf("가게번호: %s\n", booking.getCart().getStock().getSeller().getBusinessPlaceNumber());
      System.out.printf("상품명: %s\n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("수량: %d\n", booking.getBookingStocks());
      System.out.printf("금액: %d원\n",  booking.getBookingPrice());
      System.out.println();

      request.setAttribute("bookingNo", No);

      while(true) {
        System.out.println(" 예약변경(U) / 예약취소(D) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch(choose) {
          case "u" :
          case "U" : if(booking.isConfirm() == false) {
            request.getRequestDispatcher("/booking/update").forward(request); return;
          } { System.out.println("이미 픽업을 완료한 상품입니다."); return;}
          case "d" :
          case "D" : if(booking.isConfirm() == false) {
            request.getRequestDispatcher("/booking/delete").forward(request); return;
          } { System.out.println("이미 픽업을 완료한 상품입니다."); return;}
          case "0" : return;
        }
      }


    } else if (ClientApp.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {
      System.out.println("[고객 예약 상세보기]");

      int No = Prompt.inputInt("예약번호 :");
      String id =ClientApp.getLoginUser().getId();
      Booking booking = bookingDao.findByNo2(No, id);

      if (booking == null) {
        System.out.println("해당 상품의 예약이 없습니다.");
        return;
      }

      Buyer buyer = buyerDao.findById(booking.getCart().getId());

      System.out.printf("예약자: %s\n", booking.getCart().getId());
      System.out.printf("연락처: %s\n", buyer.getMember().getPhoneNumber());
      System.out.printf("상품명: %s\n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("수량: %d\n", booking.getBookingStocks());
      System.out.printf("금액: %d원\n",  booking.getBookingPrice());

      System.out.println();

      request.setAttribute("bookingNo", No);
      while(true) {
        System.out.println("\n 예약변경(U) / 예약확정(C) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch(choose) {
          case "u" :
          case "U" : if(booking.isConfirm()==false) {
            request.getRequestDispatcher("/booking/update").forward(request); return;
          } else {
            System.out.println("픽업이 완료된 예약은 변경이 불가합니다."); continue; }
          case "c" :
          case "C" :
            request.getRequestDispatcher("/booking/confirm").forward(request); return;
          case "0" : return;
        }
      }
    }
  }
}
