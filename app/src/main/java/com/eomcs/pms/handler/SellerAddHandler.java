package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerAddHandler extends AbstractSellerHandler {

  BookingPrompt bookingPrompt;
  StockPrompt stockPrompt;
  public SellerAddHandler(List<Member> memberList, 
      BookingPrompt bookingPrompt, StockPrompt stockPrompt) {
    super(memberList);
    this.bookingPrompt = bookingPrompt;
    this.stockPrompt = stockPrompt;
  }

  public static int sellerNumber = 1;

  @Override
  public void execute() {
    System.out.println("\n[판매자 등록]");
    Member seller = new Seller();
    seller.setAuthority(Menu.ACCESS_SELLER);
    seller.setNumber(sellerNumber++);

    String id = Prompt.inputString("등록할 아이디: ");

    int listSize = memberList.size();

    for (int i = 0; i < listSize; i++) {
      if (memberList.get(i).getId().equals(id)) {
        System.out.println("중복되는 아이디입니다.");
        return;
      }
    }
    seller.setId(id);

    seller.setName(Prompt.inputString("이름 : "));
    seller.setNickname(Prompt.inputString("닉네임 : "));
    seller.setEmail(Prompt.inputString("이메일 : "));
    seller.setBirthday(Prompt.inputDate("생일 : "));
    seller.setPassword(Prompt.inputString("암호 : "));
    seller.setPhoto(Prompt.inputString("사진 : "));
    seller.setPhoneNumber(Prompt.inputString("전화 : "));
    ((Seller) seller).setBusinessName(Prompt.inputString("가게명 : "));
    ((Seller) seller).setBusinessNumber(Prompt.inputString("사업자번호 : "));
    ((Seller) seller).setBusinessAddress(Prompt.inputString("사업장주소 : "));
    ((Seller) seller).setBusinessPlaceNumber(Prompt.inputString("사업장번호 : "));
    seller.setRegisteredDate(new Date(System.currentTimeMillis()));
    memberList.add(seller);

    // 예약리스트에 판매자 id를 갖는 bookingList add.
    bookingPrompt.addBookingListById(seller.getId());
    // 장바구니리스트에 판매자 id를 갖는 cartList add.
    stockPrompt.addStockListById(seller.getId());
  }
}








