package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class SellerPrivacyAddHandler extends AbstractSellerPrivacyHandler {

  int sellerPrivacyNumber = 1;

  @Override
  public void execute() {
    System.out.println("\n[판매자 등록]");
    SellerPrivacy sellerPrivacy = new SellerPrivacy();
    sellerPrivacy.setAuthority(0x04);
    sellerPrivacy.setNumber(sellerPrivacyNumber++);

    String id = Prompt.inputString("등록할 아이디: ");

    int listSize = App.managerList.size();

    for (int i = 0; i < listSize; i++) {
      if (App.managerList.get(i).getId().equals(id)) {
        System.out.println("중복되는 아이디입니다.");
        return;
      }
    }
    sellerPrivacy.setId(id);

    sellerPrivacy.setName(Prompt.inputString("이름 : "));
    sellerPrivacy.setNickname(Prompt.inputString("닉네임 : "));
    sellerPrivacy.setEmail(Prompt.inputString("이메일 : "));
    sellerPrivacy.setBirthday(Prompt.inputDate("생일 : "));
    sellerPrivacy.setPassword(Prompt.inputString("암호 : "));
    sellerPrivacy.setPhoto(Prompt.inputString("사진 : "));
    sellerPrivacy.setPhoneNumber(Prompt.inputString("전화 : "));
    sellerPrivacy.setBusinessName(Prompt.inputString("가게명 : "));
    sellerPrivacy.setBusinessNumber(Prompt.inputString("사업자번호 : "));
    sellerPrivacy.setBusinessAddress(Prompt.inputString("사업장주소 : "));
    sellerPrivacy.setBusinessPlaceNumber(Prompt.inputString("사업장번호 : "));
    sellerPrivacy.setRegisteredDate(new Date(System.currentTimeMillis()));
    App.sellerPrivacyList.add(sellerPrivacy);
    App.managerList.add(new Manager(sellerPrivacy.getId(), sellerPrivacy.getPassword(), sellerPrivacy.getAuthority()));

    StockList StockList = new StockList();
    StockList.setId(sellerPrivacy.getId());
    App.allStockList.add(StockList);
    BookingList BookingList = new BookingList();
    BookingList.setId(sellerPrivacy.getId());
    App.allBookingList.add(BookingList);
  }
}








