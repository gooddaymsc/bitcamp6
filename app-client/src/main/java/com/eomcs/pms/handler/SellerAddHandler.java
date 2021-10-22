package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.menu.Menu;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class SellerAddHandler implements Command {
  SellerDao sellerDao;
  SqlSession sqlSession;
  public SellerAddHandler(SellerDao sellerDao, SqlSession sqlSession) {
    this.sellerDao = sellerDao;
    this.sqlSession = sqlSession;
  }  

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[판매자 등록]");

    Seller seller = new Seller();

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
    Member member = new Member();
    member.setId(id);
    member.setAuthority(Menu.ACCESS_SELLER);
    member.setName(Prompt.inputString("이름 : "));
    member.setNickname(Prompt.inputString("닉네임 : "));
    member.setEmail(Prompt.inputString("이메일 : "));
    member.setBirthday(Prompt.inputDate("생일 : "));

    String passWord = SellerValidation.checkPassword("암호 : ");
    member.setPassword(passWord);
    member.setPhoto(Prompt.inputString("사진 : "));
    member.setPhoneNumber(Prompt.inputString("전화 : "));
    //    if (memberPrompt.findDeletedByName(seller.getName()) != -1) {
    //      if (deletedMemberList.get(memberPrompt.findDeletedByName(seller.getName())).getPhoneNumber().equals(seller.getPhoneNumber()) && 
    //          deletedMemberList.get(memberPrompt.findDeletedByName(seller.getName())).getName().equals(seller.getName())) {
    //        System.out.println("탈퇴한 회원입니다. 7일후 재가입해주세요.");
    //        return;
    //      }
    //    }
    seller.setMember(member);
    seller.setBusinessName(Prompt.inputString("가게명 : "));
    seller.setBusinessNumber(Prompt.inputString("사업자번호 : "));
    seller.setBusinessAddress(Prompt.inputString("사업장주소 : "));
    seller.setBusinessPlaceNumber(Prompt.inputString("사업장번호 : "));  
    while(true) {
      try{
        seller.setBusinessOpeningTime(SellerValidation.openingTime("오픈시간(예 10:50) : "));    
        break;
      } catch(Exception e){
        System.out.println("예시대로 입력해주세요.");
      }
    }
    while(true) {
      try{
        seller.setBusinessClosingTime(SellerValidation.closingTime("마감시간(예 10:50) : "));
        break;
      } catch(Exception e){
        System.out.println("예시대로 입력해주세요.");
      }
    }

    //    // 예약리스트에 판매자 id를 갖는 bookingList add.
    //    bookingPrompt.addBookingListById(seller.getId());
    //    // 재고 리스트에 판매자 id를 갖는 stockList add.
    //    stockPrompt.addStockListById(seller.getId());

    //    messagePrompt.addMessageListById(seller.getId());
    sellerDao.insert(seller.getMember());
    sqlSession.commit();

    System.out.println("회원가입이 완료되었습니다.");

  }



}

