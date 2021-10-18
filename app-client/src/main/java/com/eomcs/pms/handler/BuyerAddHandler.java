package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.menu.Menu;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Coupon;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerAddHandler implements Command {
  BuyerDao buyerDao;
  public BuyerAddHandler (BuyerDao buyerDao) {
    this.buyerDao = buyerDao;
  } 

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 등록]");
    Member buyer = new Buyer();
    buyer.setAuthority(Menu.ACCESS_BUYER);

    String id = Prompt.inputString("등록할 아이디: ");

    //중복체크
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("id", id);

    //    requestAgent.request("buyer.checkDuplicate", params);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(requestAgent.getObject(String.class));
    //      return;
    //    }

    buyer.setId(id);
    buyer.setName(Prompt.inputString("이름: "));
    buyer.setNickname(Prompt.inputString("닉네임: "));
    buyer.setEmail(Prompt.inputString("이메일: "));
    buyer.setBirthday(Prompt.inputDate("생일: "));
    String passWord = BuyerValidation.checkPassword("암호 : ");
    buyer.setPassword(passWord);
    buyer.setPhoto(Prompt.inputString("사진: "));
    buyer.setPhoneNumber(Prompt.inputString("전화: "));
    //    if (findDeletedByName(buyer.getName()) != -1) {
    //      if (deletedMemberList.get(memberPrompt.findDeletedByName(buyer.getName())).getPhoneNumber().equals(buyer.getPhoneNumber()) && 
    //          deletedMemberList.get(memberPrompt.findDeletedByName(buyer.getName())).getName().equals(buyer.getName())) {
    //        System.out.println("탈퇴한 회원입니다. 7일후 재가입해주세요.");
    //        return;
    //      }
    //    }

    // 회원가입할때 쿠폰지급하기 (2개)
    Coupon coupon = new Coupon();
    coupon.setMinimum(10000);
    coupon.setPercent(10);
    ((Buyer)buyer).getCoupon().add(coupon);
    coupon = new Coupon();
    coupon.setMinimum(15000);
    coupon.setPrice(2000);
    ((Buyer)buyer).getCoupon().add(coupon);

    ((Buyer)buyer).setAddress(Prompt.inputString("주소: "));

    //    System.out.printf("이름 : %s\n", memberPrompt.findById(id).getName());
    buyer.setRegisteredDate(new Date(System.currentTimeMillis()));
    //    buyer.setNumber(totalNumberList.get(App.MEMBER_NUMBER_INDEX));
    //    totalNumberList.set(App.MEMBER_NUMBER_INDEX, buyer.getNumber()+1);
    //    memberList.add(buyer);

    //    //    // 예약리스트에 구매자 id를 갖는 bookingList add.
    //    requestAgent.request("cart.insert", buyer.getId());
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("장바구니 생성 실패!");
    //      return;
    //    }
    //    //    // 장바구니리스트에 구매자 id를 갖는 cartList add.
    //    requestAgent.request("booking.insert", buyer.getId());
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("예약 생성 실패!");
    //      return;
    //    }
    //    requestAgent.request("message.insert", buyer.getId());
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println("메신저 생성 실패!");
    //      return;
    //    }
    //    messagePrompt.addMessageListById(buyer.getId());
    //    //    memberList.add(new Member(buyer.getId(), buyer.getPassword(), buyer.getAuthority()));

    buyerDao.insert(buyer);
    System.out.println("회원가입이 완료되었습니다.");
  }


  //
  //  public int findDeletedByName(String name) {
  //    for (int i=0; i< deleteMemberList.size(); i++) {
  //      if (deleteMemberList.get(i).getName().equals(name)) {
  //        return i;
  //      }
  //    }
  //    return -1;
  //  }

}




