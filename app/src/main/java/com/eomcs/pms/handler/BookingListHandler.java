package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;

public class BookingListHandler extends AbstractBookingHandler{

  BookingPrompt bookingPrompt;
  MemberPrompt memberPrompt;
  public BookingListHandler(List <BookingList> allBookingList, BookingPrompt bookingPrompt, 
      MemberPrompt memberPrompt) {
    super(allBookingList);
    this.bookingPrompt = bookingPrompt;
    this.memberPrompt = memberPrompt;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    // 로그인한 판매자의 예약업뎃을 확인한 후에 알림을 끔. 

    if (App.getLoginUser().getAuthority()==Menu.ACCESS_BUYER) {
      System.out.println("[내 픽업 예약 목록]\n");
      BookingList bookingList = bookingPrompt.findBookingList(App.getLoginUser().getId());

      if (bookingList.getBooking().size() == 0) {
        System.out.println("아직 예약한 상품이 없습니다.");
        return;
      }
      System.out.printf("%-6s\t%-6s\t%-6s\t%-6s\t%-10s\t%-10s\t%-10s\n",
          "예약번호", "가게명", "판매자", "상품명", "예약일시", "픽업 예약날짜", "픽업 예약시간");
      System.out.println("--------------------------------------------------------------------------");
      for (Booking booking : bookingList.getBooking() ) {
        String sellerId = booking.getCart().getSellerId();
        System.out.printf("%-6d\t%-6s\t%-6s\t%-6s\t%-10s\t%-10s\t%d시 %d분\n",
            booking.getBookingNumber(),
            memberPrompt.findBySellerInfo(sellerId).getBusinessName(),
            booking.getCart().getSellerId(),
            booking.getCart().getStock().getProduct().getProductName(),
            booking.getRegisteredDate(),
            booking.getBookingDate(),
            booking.getBookingHour(), booking.getBookingMinute());
      }

      System.out.println();

      while(true) {
        System.out.println("\n 예약 상세보기(U) / 상품 상세정보 보기(1) / 판매자에게 문의하기(2) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch(choose) {
          case "u" :
          case "U" : request.getRequestDispatcher("/booking/detail").forward(request); return;
          case "1" : 
            String productName = Prompt.inputString("\n상품명 선택 / 이전(0) > ");
            if (productName.equals("0")) {
              return;
            } else {
              request.setAttribute("productName", productName);
            }
            request.getRequestDispatcher("/product/detail").forward(request); break;
          case "2" : request.getRequestDispatcher("/message/add").forward(request); break;
          case "0" : return;
        }
      }

    } else if (App.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {
      memberPrompt.changeBookingUpdate(App.getLoginUser().getId(), false);

      System.out.println("[고객 예약 목록]\n");
      BookingList bookingList = bookingPrompt.findBookingList(App.getLoginUser().getId());

      if (bookingList.getBooking().size() == 0) {
        System.out.println("아직 예약한 고객이 없습니다.");
        return;
      }

      System.out.printf("%-6s\t%-6s\t%-6s\t%-10s\t%-10s\t%-10s\n",
          "예약번호","예약자", "상품명", "예약일시",  "픽업 예약날짜", "픽업 예약시간");
      System.out.println("--------------------------------------------------------------------------");

      for (Booking booking : bookingList.getBooking() ) {
        System.out.printf("%-6d\t%-6s\t%-6s\t%-10s\t%-10s\t%d시 %d분\n",
            booking.getBookingNumber(),
            booking.getBuyerId(),
            booking.getCart().getStock().getProduct().getProductName(),
            booking.getRegisteredDate(),
            booking.getBookingDate(),
            booking.getBookingHour(), booking.getBookingMinute()
            );
      }
    }
    System.out.println();
    while(true) {
      System.out.println("\n 예약 상세보기(U) / 상품 상세정보 보기(1) / 예약자와 대화하기(2) / 이전(0)");
      String choose = Prompt.inputString("선택 > ");
      System.out.println();
      switch(choose) {
        case "u" : 
        case "U" : request.getRequestDispatcher("/booking/detail").forward(request); return;
        case "1" : 
          String productName = Prompt.inputString("\n상품명 선택 (0.이전) > ");
          if (productName.equals("0")) {
            return;
          } else {
            request.setAttribute("productName", productName);
          }
          request.getRequestDispatcher("/product/detail").forward(request); break;
        case "2" : request.getRequestDispatcher("/message/add").forward(request); break;
        case "0" : return;

      }
    }

  }
}







