package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class BuyerUpdateHandler extends AbstractBuyerHandler {
  public BuyerUpdateHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute(CommandRequest request) {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 변경]\n");
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


        System.out.println("개인정보를 변경하였습니다.");
        return;
      } else {
        System.out.println("개인정보 변경을 취소하였습니다.");
        return;
      } 
    } else {
      System.out.println("[회원 변경]\n");
      //      String id = Prompt.inputString("변경할 아이디: ");

      Buyer buyer = (Buyer) request.getAttribute("buyer");
      if (buyer == null) {
        System.out.println("해당 아이디의 회원이 없습니다.");
        return;
      }
      // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
      int level = Prompt.inputInt(String.format("등급(변경 전 : %d)", buyer.getLevel()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        buyer.setLevel(level);
        System.out.println("회원 변경을 완료하였습니다.");
        return;
      }
      System.out.println("회원 변경을 취소하였습니다.");
      return;

    }
  }
}







