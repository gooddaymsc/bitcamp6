package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerUpdateHandler extends AbstractSellerHandler {

  public SellerUpdateHandler(List<Member> memberList) {
    super(memberList);
  }  
  @Override
  public void execute(CommandRequest request) {
    if (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN) {
      System.out.println("[개인정보 변경]");

      Member seller = (Seller) request.getAttribute("seller");

      String nickName = Prompt.inputString(String.format("닉네임(변경 전 : %s) : ", seller.getNickname()));
      String email = Prompt.inputString(String.format("이메일(변경 전 : %s) : ", seller.getEmail()));
      String password = Prompt.inputString(String.format("암호(변경 전 : %s) : ", seller.getName()));
      String photo = Prompt.inputString(String.format("사진(변경 전 : %s) : ", seller.getPhoto()));
      String tel = Prompt.inputString(String.format("전화(변경 전 : %s) : ", seller.getPhoneNumber()));
      String bussinessName = Prompt.inputString(String.format("가게명(변경 전 : %s) : ", ((Seller) seller).getBusinessName()));
      String bussinessNo = Prompt.inputString(String.format("사업자번호(변경 전 : %s) : ", ((Seller) seller).getBusinessNumber()));
      String bussinessAddress = Prompt.inputString(String.format("사업장주소(변경 전 : %s) : ", ((Seller) seller).getBusinessAddress()));
      String bussinessTel = Prompt.inputString(String.format("사업장번호(변경 전 : %s) : ", ((Seller) seller).getBusinessPlaceNumber()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

      if (input.equalsIgnoreCase("y")) {     
        seller.setNickname(nickName);
        seller.setEmail(email);
        seller.setPassword(password);
        seller.setPhoto(photo);
        seller.setPhoneNumber(tel);
        ((Seller) seller).setBusinessName(bussinessName);
        ((Seller) seller).setBusinessNumber(bussinessNo);
        ((Seller) seller).setBusinessAddress(bussinessAddress);
        ((Seller) seller).setBusinessPlaceNumber(bussinessTel);  
        System.out.println("개인 정보를 변경하였습니다.\n");
        return;
      } else {
        System.out.println("개인 정보 변경을 취소하였습니다.\n");
        return;
      }
    } else {
      System.out.println("\n[판매자 변경]");
      //      String id = Prompt.inputString("변경할 판매자 아이디: ");
      //      Member seller = findById(id);

      Member seller = (Seller) request.getAttribute("seller");

      if (seller == null) {
        System.out.println("해당 아이디의 회원이 없습니다.\n");
        return;
      }
      int level = Prompt.inputInt(String.format("등급(변경 전 : %d)", seller.getLevel()));

      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if (input.equalsIgnoreCase("y")) {
        seller.setLevel(level);
        System.out.println("회원 변경을 완료하였습니다.\n");
        return;
      }
      System.out.println("회원 변경을 취소하였습니다.\n");
      return;
    }
  }
}







