package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.eomcs.menu.Menu;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class SellerAddHandler implements Command {
  Collection<Seller> sellerList;
  RequestAgent requestAgent;
  public SellerAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[판매자 등록]");

    Member seller = new Seller();
    seller.setAuthority(Menu.ACCESS_SELLER);

    String id = Prompt.inputString("등록할 아이디: ");

    //    int listSize = memberList.size();
    //
    //    for (int i = 0; i < listSize; i++) {
    //      if (memberList.get(i).getId().equals(id)) {
    //        System.out.println("중복되는 아이디입니다.");
    //        return;
    //      }
    //    }
    seller.setId(id);

    seller.setName(Prompt.inputString("이름 : "));
    seller.setNickname(Prompt.inputString("닉네임 : "));
    seller.setEmail(Prompt.inputString("이메일 : "));
    seller.setBirthday(Prompt.inputDate("생일 : "));

    String passWord = checkPassword("암호 : ");
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
    ((Seller) seller).setBusinessName(Prompt.inputString("가게명 : "));
    ((Seller) seller).setBusinessNumber(Prompt.inputString("사업자번호 : "));
    ((Seller) seller).setBusinessAddress(Prompt.inputString("사업장주소 : "));
    ((Seller) seller).setBusinessPlaceNumber(Prompt.inputString("사업장번호 : "));
    //    ((Seller) seller).setBusinessOpeningHours(memberPrompt.checkHour("시작시간(시) : "));
    //    ((Seller) seller).setBusinessOpeningMinutes(memberPrompt.checkMinute("시작시간(분) : "));
    //    ((Seller) seller).setBusinessClosingHours(memberPrompt.checkHour("종료시간(시) : "));
    //    ((Seller) seller).setBusinessClosingMinutes(memberPrompt.checkMinute("종료시간(분) : "));
    seller.setRegisteredDate(new Date(System.currentTimeMillis()));
    //    seller.setNumber(totalNumberList.get(App.MEMBER_NUMBER_INDEX));
    //    totalNumberList.set(App.MEMBER_NUMBER_INDEX, seller.getNumber()+1);

    //    memberList.add(seller);

    //    // 예약리스트에 판매자 id를 갖는 bookingList add.
    //    bookingPrompt.addBookingListById(seller.getId());
    //    // 재고 리스트에 판매자 id를 갖는 stockList add.
    //    stockPrompt.addStockListById(seller.getId());

    //    messagePrompt.addMessageListById(seller.getId());
    requestAgent.request("seller.insert", seller);
    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("회원을 등록했습니다.");
    } else {
      System.out.println("회원 등록 실패");
    }

  }

  protected String checkPassword(String label) {

    while(true) {

      String passWord = Prompt.inputString(label);

      String regExp_symbol = "([0-9].*[!,@,#,$,%,^,&,*,(,)])|([!,@,#,$,%,^,&,*,(,)].*[0-9])";
      String regExp_alpha = "([a-z].*[A-Z])|([A-Z].*[a-z])";

      Pattern pattern_symbol = Pattern.compile(regExp_symbol);
      Pattern pattern_alpha = Pattern.compile(regExp_alpha);

      Matcher matcher_symbol = pattern_symbol.matcher(passWord);
      Matcher matcher_alpha = pattern_alpha.matcher(passWord);

      if (passWord.length() < 8 || passWord.length() > 12) {
        System.out.println("8 ~ 12자리 이내의 비밀번호를 입력하시오.");
        continue;
      } else {
        if (matcher_symbol.find() == false) {
          System.out.println("알파벳, 숫자 및 특수문자를 포함하시오.");
          continue;
        } else {
          if (matcher_alpha.find() == false) {
            System.out.println("알파벳 대소문자를 적어도 한개씩 포함하시오.");
            continue;
          }
        }
      }
      return passWord;
    }
  }


}







