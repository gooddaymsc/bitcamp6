package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Product;
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
  public void execute() {
    // 로그인한 판매자의 예약업뎃을 확인한 후에 알림을 끔. 
    if (App.getLoginUser().isBookingUpdate()) {
      memberPrompt.changeCommentUpdate(App.getLoginUser().getId(), false);
    }
    if (App.getLoginUser().getAuthority()==Menu.ACCESS_BUYER) {
      System.out.println("\n[내 픽업 예약 목록]");
      BookingList bookingList = findById(App.getLoginUser().getId());

      if (bookingList.getBooking().size() == 0) {
        System.out.println("아직 예약한 상품이 없습니다.");
        return;
      }
      System.out.printf("%-6s\t%-6s\t%-6s\t%-10s\t%-10s\t%-10s\n",
          "예약번호", "가게명", "상품명", "예약일시", "픽업 예약날짜", "픽업 예약시간");
      System.out.println("--------------------------------------------------------------------------");

      for (Booking booking : bookingList.getBooking() ) {
        String sellerId = booking.getCart().getSellerId();
        System.out.printf("%-6d\t%-6s\t%-6s\t%-10s\t%-10s\t%d시 %d분\n",
            booking.getBookingNumber(),
            memberPrompt.findBySellerInfo(sellerId).getBusinessName(),
            booking.getCart().getStock().getProduct().getProductName(),
            booking.getRegisteredDate(),
            booking.getBookingDate(),
            booking.getBookingHour(), booking.getBookingMinute()
            );
      }
    } else if (App.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {
      System.out.println("\n[고객 예약 목록]");
      BookingList bookingList = findById(App.getLoginUser().getId());

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

    while(true) {
      String productName = Prompt.inputString("\n상품 상세정보 보기(이전메뉴:0) \n>> 상품명 : ");
      if (productName.equals("0")) {
        return;
      } else {
        Product bookingProduct = bookingPrompt.findBookingByProduct(productName, App.getLoginUser().getId());

        if (bookingProduct == null) {
          System.out.println("해당 상품이 없습니다.");
          return;
        }

        System.out.printf("\n주종: %s\n",  bookingProduct.getProductType());
        System.out.printf("원산지: %s\n", bookingProduct.getCountryOrigin());
        System.out.printf("품종: %s\n",  bookingProduct.getVariety());
        System.out.printf("알콜도수: %.2f\n",bookingProduct.getAlcoholLevel());
        System.out.printf("당도: %d\n",  bookingProduct.getSugerLevel());
        System.out.printf("산도: %d\n",  bookingProduct.getAcidity());

      }
    }
  }
}







