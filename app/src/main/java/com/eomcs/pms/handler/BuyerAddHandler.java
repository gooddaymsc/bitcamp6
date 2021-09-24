package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerAddHandler extends AbstractBuyerHandler {
  CartPrompt cartPrompt;
  BookingPrompt bookingPrompt;
  MemberPrompt memberPrompt;
  List<Integer> totalNumberList;
  MessagePrompt messagePrompt;
  public BuyerAddHandler (List<Member> memberList, CartPrompt cartPrompt, 
      BookingPrompt bookingPrompt,MemberPrompt memberPrompt, List<Integer> totalNumberList, MessagePrompt messagePrompt) {
    super(memberList);
    this.cartPrompt = cartPrompt;
    this.bookingPrompt = bookingPrompt;
    this.memberPrompt = memberPrompt;
    this.totalNumberList = totalNumberList;
    this.messagePrompt = messagePrompt;
  } 

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[회원 등록]");

    Member buyer = new Buyer();
    buyer.setAuthority(Menu.ACCESS_BUYER);

    //아이디가 중복되면 다시 아이디 재설정.
    String id = Prompt.inputString("등록할 아이디: ");

    if (memberPrompt.findById(id)!=null) {
      System.out.println("중복되는 아이디입니다.");
      return;
    }

    buyer.setId(id);
    buyer.setName(Prompt.inputString("이름: "));
    buyer.setNickname(Prompt.inputString("닉네임: "));
    buyer.setEmail(Prompt.inputString("이메일: "));
    buyer.setBirthday(Prompt.inputDate("생일: "));
    buyer.setPassword(Prompt.inputString("암호: "));
    buyer.setPhoto(Prompt.inputString("사진: "));
    buyer.setPhoneNumber(Prompt.inputString("전화: "));
    ((Buyer) buyer).setAddress(Prompt.inputString("주소: "));
    buyer.setRegisteredDate(new Date(System.currentTimeMillis()));
    buyer.setNumber(totalNumberList.get(App.MEMBER_NUMBER_INDEX));
    totalNumberList.set(App.MEMBER_NUMBER_INDEX, buyer.getNumber()+1);
    memberList.add(buyer);

    // 예약리스트에 구매자 id를 갖는 bookingList add.
    bookingPrompt.addBookingListById(buyer.getId());
    // 장바구니리스트에 구매자 id를 갖는 cartList add.
    cartPrompt.addCartListById(buyer.getId());

    messagePrompt.addMessageListById(buyer.getId());
    //    memberList.add(new Member(buyer.getId(), buyer.getPassword(), buyer.getAuthority()));
  }
}





