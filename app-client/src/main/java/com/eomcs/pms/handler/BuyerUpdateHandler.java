package com.eomcs.pms.handler;

import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BuyerUpdateHandler implements Command {
  RequestAgent requestAgent;
  public BuyerUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    if (ClientApp.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 변경]");

      Buyer buyer = (Buyer) request.getAttribute("buyer");

      String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s) : ", buyer.getNickname()));
      String email = Prompt.inputString(String.format("이메일(변경 전 : %s) : ", buyer.getEmail()));
      String password = Prompt.inputString(String.format("암호(변경 전 : %s) : ", buyer.getPassword()));
      String photo = Prompt.inputString(String.format("사진(변경 전 : %s) : ", buyer.getPhoto()));
      String address = Prompt.inputString(String.format("주소(변경 전 : %s) : ", buyer.getAddress()));
      String tel = Prompt.inputString(String.format("전화(변경 전 : %s) : ", buyer.getPhoneNumber()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {
        buyer.setNickname(nickName);
        buyer.setEmail(email);
        buyer.setPassword(password);
        buyer.setPhoto(photo);
        buyer.setAddress(address);
        buyer.setPhoneNumber(tel);

        requestAgent.request("member.update", buyer);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("개인정보 변경 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }
        System.out.println("개인정보를 변경하였습니다.\n");
      } 
    } else {
      System.out.println("[회원 변경]\n");
      Buyer buyer = (Buyer) request.getAttribute("buyer");

      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      int level = checkLevel(String.format("등급(변경 전 : %d) : ", buyer.getLevel())); 
      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        buyer.setLevel(level);
        requestAgent.request("member.update", buyer);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("회원 변경 실패!");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }
        System.out.println("회원정보를 변경했습니다.");
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







