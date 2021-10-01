package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Seller;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class SellerUpdateHandler implements Command {
  RequestAgent requestAgent;
  public SellerUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }  

  @Override
  public void execute(CommandRequest request) throws Exception {
    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 변경]");
      String id = ClientApp.getLoginUser().getId();

      HashMap<String, String> params = new HashMap<>();
      params.put("id", id);

      requestAgent.request("seller.selectOne", params);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      Seller seller = requestAgent.getObject(Seller.class);

      String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s) : ", seller.getNickname()));
      String email = Prompt.inputString(String.format("이메일(변경 전 : %s) : ", seller.getEmail()));
      String password = Prompt.inputString(String.format("암호(변경 전 : %s) : ", seller.getName()));
      String photo = Prompt.inputString(String.format("사진(변경 전 : %s) : ", seller.getPhoto()));
      String tel = Prompt.inputString(String.format("전화(변경 전 : %s) : ", seller.getPhoneNumber()));
      String bussinessName = Prompt.inputString(String.format("가게명(변경 전 : %s) : ", seller.getBusinessName()));
      String bussinessNo = Prompt.inputString(String.format("사업자번호(변경 전 : %s) : ", seller.getBusinessNumber()));
      String bussinessAddress = Prompt.inputString(String.format("사업장주소(변경 전 : %s) : ", seller.getBusinessAddress()));
      String bussinessTel = Prompt.inputString(String.format("사업장번호(변경 전 : %s) : ", seller.getBusinessPlaceNumber()));

      int BusinessOpeningHours = Prompt.inputInt(String.format("영업시간(시)(변경 전 : %s) : ", seller.getBusinessOpeningHours()));
      int BusinessOpeningMinutes= Prompt.inputInt(String.format("영업시간(분)(변경 전 : %s) : ", seller.getBusinessOpeningMinutes()));
      int BusinessClosingHours = Prompt.inputInt(String.format("마감시간(시)(변경 전 : %s) : ", seller.getBusinessClosingHours()));
      int BusinessClosingMinutes= Prompt.inputInt(String.format("마감시간(분)(변경 전 : %s) : ", seller.getBusinessClosingMinutes()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {     
        seller.setNickname(nickName);
        seller.setEmail(email);
        seller.setPassword(password);
        seller.setPhoto(photo);
        seller.setPhoneNumber(tel);
        seller.setBusinessName(bussinessName);
        seller.setBusinessNumber(bussinessNo);
        seller.setBusinessAddress(bussinessAddress);
        seller.setBusinessPlaceNumber(bussinessTel);  
        seller.setBusinessOpeningHours(BusinessOpeningHours);  
        seller.setBusinessOpeningMinutes(BusinessOpeningMinutes);  
        seller.setBusinessClosingHours(BusinessClosingHours);  
        seller.setBusinessClosingMinutes(BusinessClosingMinutes);  

        requestAgent.request("seller.update", seller);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("개인 정보 변경 실패!\n");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }
        System.out.println("개인 정보를 변경하였습니다.\n");
      }
      System.out.println("개인 정보 변경을 취소하였습니다.\n");

    } else {
      System.out.println("[판매자 변경]");
      Seller seller1 = (Seller) request.getAttribute("seller");
      String id = seller1.getId();
      HashMap<String, String> params = new HashMap<>();
      params.put("id", id);

      requestAgent.request("seller.selectOne", params);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 회원이 없습니다.");
        return;
      }

      Seller seller = requestAgent.getObject(Seller.class);

      int level = checkLevel(String.format("등급(변경 전 : %d) : ", seller.getLevel())); 
      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        seller.setLevel(level);
        requestAgent.request("seller.update", seller);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("회원 변경을 취소하였습니다.\n");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }
        System.out.println("회원 변경을 완료하였습니다.\n");
        return;
      }
    }
  }

  private int checkLevel(String label) {
    while(true) {
      int level = Prompt.inputInt(label);
      if (level<1 || level>5) {
        System.out.println("잘못된 등급입니다.\n1부터 5사이 값으로 입력해주세요.\n");
        continue;
      }
      return level;
    }
  }
}
