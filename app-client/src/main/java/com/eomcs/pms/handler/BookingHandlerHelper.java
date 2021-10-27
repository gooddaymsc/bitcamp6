package com.eomcs.pms.handler;

import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class BookingHandlerHelper {

  BookingDao bookingDao;
  public BookingHandlerHelper (BookingDao bookingDao) {
    this.bookingDao = bookingDao;
  }

  public String checkTime (String label, Stock stock) throws Exception {
    Seller seller = stock.getSeller();
    while(true) {
      String time = Prompt.inputString(label);
      String temp[] = time.split(":");                              //구매자 입력시간
      String temp2[] = seller.getBusinessOpeningTime().split(":");  //판매자 오픈시간
      String temp3[] = seller.getBusinessClosingTime().split(":");  //판매자 마감시간
      int hour = Integer.parseInt(temp[0]);             
      int minute = Integer.parseInt(temp[1]);
      int sellerOpeningHour = Integer.parseInt(temp2[0]);      
      int sellerOpeningMinute = Integer.parseInt(temp2[1]);
      int sellerClosingHour = Integer.parseInt(temp3[0]);   
      int sellerClosingMinute = Integer.parseInt(temp3[1]);
      if(minute != 30 && minute != 0 ) {
        System.out.println(" 30분 단위로 입력가능합니다. \n");
        continue;
      } 
      if(hour < sellerOpeningHour || hour > sellerClosingHour)  {          
        System.out.println(" 영업시간이 아닙니다.\n"); 
        System.out.printf("영업시간: %s - %s \n", seller.getBusinessOpeningTime(), 
            seller.getBusinessClosingTime());
        continue;
      } else if(hour == sellerOpeningHour || hour == sellerClosingHour)      {   
        if(minute < sellerOpeningMinute && minute > sellerClosingMinute) {
          System.out.println(" 영업시간이 아닙니다.\n"); 
          System.out.printf("영업시간: %s - %s \n", seller.getBusinessOpeningTime(), 
              seller.getBusinessClosingTime());
        }
      }
      return time;       
    }
  }
}
