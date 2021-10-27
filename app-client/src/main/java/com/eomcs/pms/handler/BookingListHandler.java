package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.util.Prompt;

public class BookingListHandler implements Command {
  BookingDao bookingDao;
  public BookingListHandler(BookingDao bookingDao) {
    this.bookingDao = bookingDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    // 로그인한 판매자의 예약업뎃을 확인한 후에 알림을 끔. 
    Loop : while(true) {

      if (ClientApp.getLoginUser().getAuthority()==Menu.ACCESS_BUYER) {

        System.out.println("[내 픽업 예약 목록]");

        Collection<Booking> bookingList = bookingDao.findAll1(nowLoginId);

        if (bookingList.size() == 0 ) {
          System.out.println("아직 예약한 상품이 없습니다.");
          return;
        }
        System.out.printf("%-3s\t%-4s\t%-4s\t%-8s\t%-8s\t%-8s\t%-8s\t%-8s\n",
            "번호", "가게명", "상품명", "주문일시", "픽업날짜", "픽업시간", "결제상태", "픽업상태");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Booking booking : bookingList) {
          System.out.printf("%-5d\t%-5s\t%-5s\t%-9s\t%-10s\t%-10s\t%-3s-%s\t%-10s\n",
              booking.getBookingNumber(), 
              booking.getCart().getStock().getSeller().getBusinessName(),
              booking.getCart().getStock().getProduct().getProductName(),
              booking.getRegisteredDate(),
              booking.getBookingDate(),
              booking.getBookingTime(),
              BookingValidation.printPaymentStatus(booking),
              BookingValidation.paymentType(booking),
              BookingValidation.bookingStatue(booking));
        } 
        System.out.println();

        while(true) {
          System.out.println("\n 예약 상세보기(R) / 상품 상세정보 보기(1) / 판매자에게 문의하기(2) / 이전(0)");
          String choose = Prompt.inputString("선택 > ");
          System.out.println();
          switch(choose) {
            case "r" :
            case "R" : request.getRequestDispatcher("/booking/detail").forward(request); continue Loop;
            case "1" : 
              String productName = Prompt.inputString("\n상품명 선택 / 이전(0) > ");
              if (productName.equals("0")) {
                return;
              } else {
                request.setAttribute("productName", productName);
              }
              request.getRequestDispatcher("/product/detail").forward(request); continue Loop;
            case "2" : request.getRequestDispatcher("/message/add").forward(request); continue Loop;
            case "0" : return;
          }
        }
      } else if (ClientApp.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {

        System.out.println("[고객 예약 목록]\n");
        Collection<Booking> bookingList = bookingDao.findAll2(nowLoginId);

        if (bookingList.size() == 0 ) {
          System.out.println("아직 예약한 고객이 없습니다.");
          return;
        }

        System.out.printf("%-3s\t%-4s\t%-4s\t%-8s\t%-8s\t%-8s\t%-8s\t%-8s\n",
            "번호","예약자", "상품명","주문일시",  "픽업날짜", "픽업시간", "결제상태", "픽업 상태");
        System.out.println("------------------------------------------------------------------------------------------------");

        for (Booking booking : bookingList) {
          System.out.printf("%-5d\t%-5s\t%-6s\t%-10s\t%-10s\t%-10s\t%-3s-%s\t%-10s\n",
              booking.getBookingNumber(),
              booking.getCart().getId(),
              booking.getCart().getStock().getProduct().getProductName(),
              booking.getRegisteredDate(),
              booking.getBookingDate(),
              booking.getBookingTime(),
              BookingValidation.printPaymentStatus(booking),
              BookingValidation.paymentType(booking),
              BookingValidation.bookingStatue(booking));
        }
      }

      System.out.println();
      while(true) {
        System.out.println("예약 상세보기(R) / 상품 상세정보 보기(1) / 예약자와 대화하기(2) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch(choose) {
          case "r" : 
          case "R" :  request.getRequestDispatcher("/booking/detail").forward(request); continue Loop;
          case "1" : 
            String productName = Prompt.inputString("\n상품명 선택 (0.이전) > ");
            if (productName.equals("0")) {
              return;
            } else {
              request.setAttribute("productName", productName);
            }
            request.getRequestDispatcher("/product/detail").forward(request); continue Loop;
          case "2" : request.getRequestDispatcher("/message/add").forward(request); continue Loop;
          case "0" : return;

        }
      }
    }
  }
}
