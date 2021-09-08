package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class BookingListHandler extends AbstractBookingHandler{

  @Override
  public void execute() {

    if (App.getLoginUser().getAuthority()==Menu.ACCESS_PRIVACY) {
      System.out.println("\n[내 픽업 예약 목록]");
      BookingList bookingList = findById(App.getLoginUser().getId());

      if (bookingList.getBooking().size() == 0) {
        System.out.println("아직 예약한 상품이 없습니다.");
        return;
      }

      for (Booking booking : bookingList.getBooking() ) {
        String sellerId = booking.getCart().getSellerId();
        System.out.printf("가게명 : %s \n", findSellerInfo(sellerId).getBusinessName());
        System.out.printf("예약번호 : %s \n", booking.getBookingNumber());
        System.out.printf("예약일시 : %s \n", booking.getRegisteredDate());
        System.out.printf("상품명 : %s \n", booking.getCart().getStock().getProduct().getProductName());
        System.out.printf("픽업 예약날짜 : %s\n", booking.getBookingDate());
        System.out.printf("픽업 예약시간: %d시 %d분 \n", booking.getBookingHour(), booking.getBookingMinute());

        System.out.println("==================================");
      }
    } else if (App.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {
      System.out.println("\n[고객 예약 목록]");
      BookingList bookingList = findById(App.getLoginUser().getId());

      if (bookingList.getBooking().size() == 0) {
        System.out.println("아직 예약한 고객이 없습니다.");
        return;
      }

      for (Booking booking : bookingList.getBooking() ) {
        System.out.printf("예약자 : %s \n", booking.getBuyerId());
        System.out.printf("예약번호 : %s \n", booking.getBookingNumber());
        System.out.printf("예약일시 : %s \n", booking.getRegisteredDate());
        System.out.printf("상품명 : %s \n", booking.getCart().getStock().getProduct().getProductName());
        System.out.printf("픽업 예약날짜 : %s\n", booking.getBookingDate());
        System.out.printf("픽업 예약시간: %d시 %d분 \n", booking.getBookingHour(), booking.getBookingMinute());

        System.out.println("==================================");
      }
    }

    while(true) {

      String input = Prompt.inputString("\n상품 상세정보 보기(이전메뉴:0) \n>> 상품명 : ");
      if (input.equals("0")) {
        return;
      } else {
        Product bookingProduct = ProductPrompt.findByProduct(input);

        if (bookingProduct == null) {
          System.out.println("해당 상품이 없습니다.");
          return;
        }

        System.out.printf("주종: %s\n",  bookingProduct.getProductType());
        System.out.printf("원산지: %s\n", bookingProduct.getCountryOrigin());
        System.out.printf("품종: %s\n",  bookingProduct.getVariety());
        System.out.printf("알콜도수: %.2f\n",bookingProduct.getAlcoholLevel());
        System.out.printf("당도: %d\n",  bookingProduct.getSugerLevel());
        System.out.printf("산도: %d\n",  bookingProduct.getAcidity());

      }
    }
  }
}







