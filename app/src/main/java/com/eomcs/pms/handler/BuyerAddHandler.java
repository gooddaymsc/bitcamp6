package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerAddHandler extends AbstractBuyerHandler {
  List<Buyer> buyerList;
  List<Member> memberList;
  List<CartList> allCartList;
  List<BookingList> allBookingList;
  public BuyerAddHandler (List<Buyer> buyerList, List<Member> memberList, 
      List<CartList> allCartList, List<BookingList> allBookingList) {
    this.buyerList = buyerList;
    this.memberList = memberList;
    this.allCartList = allCartList;
    this.allBookingList = allBookingList;
  }
  int buyerNumber = 1;

  @Override
  public void execute() {
    System.out.println("\n[회원 등록]");

    Buyer buyer = new Buyer();
    buyer.setAuthority(Menu.ACCESS_BUYER);
    buyer.setNumber(buyerNumber++);

    //아이디가 중복되면 다시 아이디 재설정.
    String id = Prompt.inputString("등록할 아이디: ");

    int listSize = memberList.size();

    for (int i=0; i<listSize; i++) {

      if (memberList.get(i).getId().equals(id)) {
        System.out.println("중복되는 아이디입니다.");
        return;
      }
    }

    buyer.setId(id);
    buyer.setName(Prompt.inputString("이름: "));
    buyer.setNickname(Prompt.inputString("닉네임: "));
    buyer.setEmail(Prompt.inputString("이메일: "));
    buyer.setBirthday(Prompt.inputDate("생일: "));
    buyer.setPassword(Prompt.inputString("암호: "));
    buyer.setPhoto(Prompt.inputString("사진: "));
    buyer.setPhoneNumber(Prompt.inputString("전화: "));
    buyer.setAddress(Prompt.inputString("주소: "));
    buyer.setRegisteredDate(new Date(System.currentTimeMillis()));

    BookingList BookingList = new BookingList();
    BookingList.setId(buyer.getId());
    allBookingList.add(BookingList);

    CartList CartList = new CartList();
    CartList.setId(buyer.getId());
    allCartList.add(CartList);

    buyerList.add(buyer);
    memberList.add(new Member(buyer.getId(), buyer.getPassword(), buyer.getAuthority()));
  }
}





