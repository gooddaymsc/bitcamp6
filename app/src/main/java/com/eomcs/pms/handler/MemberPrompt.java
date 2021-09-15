package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;

public class MemberPrompt {
  List<Member> memberList;
  public MemberPrompt(List<Member> memberList) {
    this.memberList = memberList;
  }
  protected int getMemberIndexById(String id) {
    for (int i=0; i< memberList.size(); i++) {
      if (memberList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  public int removeMemberById(String nowLoginid) {
    memberList.remove(getMemberIndexById(nowLoginid));
    return getMemberIndexById(nowLoginid);
  }

  public void changeBookingUpdate(String sellerId, boolean check) {
    for (Member member : memberList) {
      if (member.getId().equals(sellerId)) {
        member.setBookingUpdate(check);
      }
    }
  }

  public void changeCommentUpdate(String boardId, boolean check) {
    for (Member member : memberList) {
      if (member.getId().equals(boardId)) {
        member.setCommentUpdate(check);
      }
    }
  }


  public Member findIdByName(String Name) {
    for (Member member : memberList) {
      if (member instanceof Buyer || member instanceof Seller) {
        if (member.getName().equals(Name)) {
          return member;
        }
      }
    }
    return null;
  }
  public Member findById(String id) {
    for (Member member : memberList) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }
  public Seller findBySellerInfo (String SellerId) {
    for (Member seller : memberList) {
      if (seller.getId().equals(SellerId)){
        return (Seller) seller;
      }
    }
    return null;
  }


  public Seller findByPlaceName (String storeName) {
    for (Member seller : memberList) {
      if (seller instanceof Seller) {
        if (((Seller) seller).getBusinessName().equals(storeName)){
          return (Seller) seller;
        }
      }
    }
    return null;
  }


  //입력한 문자열을 포함하면 adress 리턴.
  public HashMap<String, Seller> findByAdress (String adress) {
    HashMap<String, Seller> hashMap = new HashMap<>();
    for (Member seller : memberList) {
      if((((Seller) seller).getBusinessAddress()).contains(adress)) {
        System.out.println("\n[해당 주소 근처 판매처] ");
        hashMap.put(seller.getId(), (Seller) seller);
      }
      else if (!(((Seller) seller).getBusinessAddress()).contains(adress)){
        System.out.println("해당 지역에는 판매처가 없습니다.");
        break;
      }
    }
    return hashMap;
  }
}
