package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.eomcs.menu.Menu;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BuyerAddHandler implements Command {
  RequestAgent requestAgent;
  Collection<Buyer> buyerList;
  public BuyerAddHandler (RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
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
    String passWord = checkPassword("암호 : ");
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
    ((Buyer) buyer).setAddress(Prompt.inputString("주소: "));

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
    requestAgent.request("buyer.insert", buyer);
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





