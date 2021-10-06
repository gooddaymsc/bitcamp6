package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.menu.Menu;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerAddHandler implements Command {
  SellerDao sellerDao;
  public SellerAddHandler(SellerDao sellerDao) {
    this.sellerDao = sellerDao;
  }  

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[판매자 등록]");

    Seller seller = new Seller();
    seller.setAuthority(Menu.ACCESS_SELLER);

    String id = Prompt.inputString("등록할 아이디: ");

    //중복체크
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("id", id);
    //
    //    requestAgent.request("member.checkDuplicate", params);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(requestAgent.getObject(String.class));
    //      return;
    //    }

    seller.setId(id);

    seller.setName(Prompt.inputString("이름 : "));
    seller.setNickname(Prompt.inputString("닉네임 : "));
    seller.setEmail(Prompt.inputString("이메일 : "));
    seller.setBirthday(Prompt.inputDate("생일 : "));

    String passWord = SellerValidation.checkPassword("암호 : ");
    seller.setPassword(passWord);

    seller.setPhoto(Prompt.inputString("사진 : "));
    seller.setPhoneNumber(Prompt.inputString("전화 : "));
    //    if (memberPrompt.findDeletedByName(seller.getName()) != -1) {
    //      if (deletedMemberList.get(memberPrompt.findDeletedByName(seller.getName())).getPhoneNumber().equals(seller.getPhoneNumber()) && 
    //          deletedMemberList.get(memberPrompt.findDeletedByName(seller.getName())).getName().equals(seller.getName())) {
    //        System.out.println("탈퇴한 회원입니다. 7일후 재가입해주세요.");
    //        return;
    //      }
    //    }
    seller.setBusinessName(Prompt.inputString("가게명 : "));
    seller.setBusinessNumber(Prompt.inputString("사업자번호 : "));
    seller.setBusinessAddress(Prompt.inputString("사업장주소 : "));
    seller.setBusinessPlaceNumber(Prompt.inputString("사업장번호 : "));
    seller.setBusinessOpeningHours(SellerValidation.checkHour("시작시간(시) : "));
    seller.setBusinessOpeningMinutes(SellerValidation.checkMinute("시작시간(분) : "));
    seller.setBusinessClosingHours(SellerValidation.checkHour("종료시간(시) : "));
    seller.setBusinessClosingMinutes(SellerValidation.checkMinute("종료시간(분) : "));
    seller.setRegisteredDate(new Date(System.currentTimeMillis()));
    //    // 예약리스트에 판매자 id를 갖는 bookingList add.
    //    bookingPrompt.addBookingListById(seller.getId());
    //    // 재고 리스트에 판매자 id를 갖는 stockList add.
    //    stockPrompt.addStockListById(seller.getId());

    //    messagePrompt.addMessageListById(seller.getId());
    sellerDao.insert(seller);

    System.out.println("회원가입이 완료되었습니다.");

  }



}

