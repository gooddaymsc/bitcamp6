package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class MemberPrompt {
  RequestAgent requestAgent;
  public MemberPrompt(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }
  protected int getMemberIndexById(String id) {
    for (int i=0; i< memberList.size(); i++) {
      if (memberList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  //---------삭제된 회원 찾기
  public int findDeletedByName(String name) {
    for (int i=0; i< deleteMemberList.size(); i++) {
      if (deleteMemberList.get(i).getName().equals(name)) {
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


  public Buyer findByBuyerInfo (String Id) {
    for (Member seller : memberList) {
      if (seller.getId().equals(Id)){
        return (Buyer) seller;
      }
    }
    return null;
  }

  // 판매자의 아이디를 이용해 영업시간 알아내서 시 비교하기
  protected int checkHours (String label, String sellerId) {
    Seller seller = findBySellerInfo(sellerId);
    while(true) {
      int hours = Prompt.inputInt(label);
      if(hours < seller.getBusinessOpeningHours() || hours > seller.getBusinessClosingHours()) {  
        System.out.println("영업시간이 아닙니다.\n"); 
        System.out.printf("오픈시간: %s시 %s분\n", 
            seller.getBusinessOpeningHours(), seller.getBusinessOpeningMinutes());
        System.out.printf("마감시간: %s시 %s분\n", 
            seller.getBusinessClosingHours() ,seller.getBusinessClosingMinutes());
        continue;
      }           
      return hours;       
    }
  }

  // 판매자의 아이디를 이용해 영업시간 알아내서 분 비교하기
  protected int checkMinutes (String label, int hours, String sellerId) {
    Seller seller = findBySellerInfo(sellerId);
    while(true) {
      int minutes = Prompt.inputInt(label);
      if (hours == seller.getBusinessOpeningHours()) {
        if((minutes < seller.getBusinessOpeningMinutes() || minutes > 59)) {
          System.out.println("영업시간이 아닙니다.\n"); 
          System.out.printf("오픈시간: %s시 %s분\n", 
              seller.getBusinessOpeningHours(), seller.getBusinessOpeningMinutes());
          System.out.printf("마감시간: %s시 %s분\n", 
              seller.getBusinessClosingHours() ,seller.getBusinessClosingMinutes());
          continue;
        } 
      }
      if (hours == seller.getBusinessClosingHours()) {
        if (minutes > seller.getBusinessClosingMinutes() || minutes <0) {
          System.out.println("영업시간이 아닙니다.\n"); 
          System.out.printf("오픈시간: %s시 %s분\n", 
              seller.getBusinessOpeningHours(), seller.getBusinessOpeningMinutes());
          System.out.printf("마감시간: %s시 %s분\n", 
              seller.getBusinessClosingHours() ,seller.getBusinessClosingMinutes());
          continue;
        }
      }
      return minutes;
    }
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

  public void returnMessageUpdate(String memberId) {
    for (Member member : memberList) {
      if (member.getId().equals(memberId)) {
        member.setMessageUpdate(false);
      }
    }
  }
  public void sendMessageUpdate(String memberId) {
    for (Member member : memberList) {
      if (member.getId().equals(memberId)) {
        member.setMessageUpdate(true);
      }
    }
  }

  public HashMap<String, Seller> findByAdress (String address) {
    HashMap<String, Seller> hashMap = new HashMap<>();
    for (Member seller : memberList) {
      if(seller instanceof Seller) {
        String[] arr = address.split(" ");
        if((((Seller)seller).getBusinessAddress().contains(arr[2])) && 
            (((Seller)seller).getBusinessAddress().contains(arr[1]))) {
          hashMap.put(((Seller)seller).getId(), (Seller) seller);
          return hashMap;
        } 
      }
    }
    return null;
  }
}