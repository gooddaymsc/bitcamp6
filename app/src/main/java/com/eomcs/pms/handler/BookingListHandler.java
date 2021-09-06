package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class BookingListHandler extends AbstractBookingHandler{

  ProductPrompt productPrompt;

  public BookingListHandler(ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT || App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN) {
      System.out.println("권한이 없습니다. 구매자 또는 판매자 기능입니다.");
      return;
    }

    System.out.println();

    if (App.getLoginUser().getAuthority() == (Menu.ACCESS_PRIVACY | Menu.ACCESS_SELLER)) {
      System.out.println("[내 픽업 예약 목록]");
      BookingList bookingList = findById(App.getLoginUser().getId());

      if (bookingList.getBooking().size() == 0) {
        System.out.println("아직 예약한 상품이 없습니다.");
        return;
      }

      int i = 1;
      for(Booking booking : bookingList.getBooking() ) {
        System.out.print(i++);
        System.out.printf("예약번호 : %s \n", booking.getBookingNumber());
        System.out.printf("예약일시 : %s \n", booking.getRegisteredDate());
        System.out.printf("상품명 : %s \n", booking.getCart().getStock().getProduct().getProductName());

        if(booking.getCart().getCartPrice() == 0) {
          System.out.printf("결제상태 : 미결제 \n" );
        } else {
          System.out.printf("결제상태 : 결제완료 \n" );
        }
        System.out.printf("픽업 예약날짜 : %s\n", booking.getBookingDate());
        System.out.printf("픽업 예약시간: %d시 %d분 \n", booking.getBookingHour(), booking.getBookingMinute());

        System.out.println("==================================");
        String input = Prompt.inputString("상품 상세정보 보기(이전메뉴:0) >>");
        Product bookingProduct = productPrompt.findByProduct(Prompt.inputString("상품명 : "));
        if (bookingProduct == null) {
          System.out.println("해당 상품이 없습니다.");
          return;
        }

        if (!input.equals("0")) {
          System.out.printf("주종: %s\n",  bookingProduct.getProductType());
          System.out.printf("원산지: %s\n", bookingProduct.getCountryOrigin());
          System.out.printf("품종: %s\n",  bookingProduct.getVariety());
          System.out.printf("알콜도수: %.2f\n",bookingProduct.getAlcoholLevel());
          System.out.printf("당도: %d\n",  bookingProduct.getSugerLevel());
          System.out.printf("산도: %d\n",  bookingProduct.getAcidity());
          return;
        } else {
          return;
        }
      }
    }


  }
}








