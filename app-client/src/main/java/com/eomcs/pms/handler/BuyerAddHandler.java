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
    Buyer buyer = new Buyer();

    String id = Prompt.inputString("등록할 아이디: ");

    //중복체크
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("id", id);

    //    requestAgent.request("buyer.checkDuplicate", params);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(requestAgent.getObject(String.class));
    //      return;
    //    }
    Member member = new Member();

    member.setAuthority(Menu.ACCESS_BUYER);
    member.setId(id);
    member.setName(Prompt.inputString("이름: "));
    member.setNickname(Prompt.inputString("닉네임: "));
    member.setEmail(Prompt.inputString("이메일: "));
    member.setBirthday(Prompt.inputDate("생일: "));
    String passWord = BuyerValidation.checkPassword("암호 : ");
    member.setPassword(passWord);
    member.setPhoto(Prompt.inputString("사진: "));
    member.setPhoneNumber(Prompt.inputString("전화: "));
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
    buyer.getCoupon().add(coupon);
    coupon = new Coupon();
    coupon.setMinimum(15000);
    coupon.setPrice(2000);
    buyer.getCoupon().add(coupon);

    buyer.setZipcode(Prompt.inputString("우편번호: "));
    buyer.setAddress(Prompt.inputString("주소: "));
    buyer.setDetailAddress(Prompt.inputString("상세주소: "));
    //    System.out.printf("이름 : %s\n", memberPrompt.findById(id).getName());
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    //    buyer.setNumber(totalNumberList.get(App.MEMBER_NUMBER_INDEX));
    //    totalNumberList.set(App.MEMBER_NUMBER_INDEX, buyer.getNumber()+1);
    //    memberList.add(buyer);
    buyer.setMember(member);

    buyerDao.insert(buyer);
    System.out.println("회원가입이 완료되었습니다.");
  }

}




