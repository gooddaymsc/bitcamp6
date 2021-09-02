package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.SellerPrivacy;

public abstract class AbstractSellerPrivacyHandler implements Command {

  protected List<SellerPrivacy> memberList;
  protected List<Manager> managerList;
  int size=1;

  public AbstractSellerPrivacyHandler(List<SellerPrivacy> memberList, List<Manager> managerList) {
    this.memberList = memberList;
    this.managerList = managerList;
  }

  protected SellerPrivacy findById(String id) {
    SellerPrivacy[] arr = memberList.toArray(new SellerPrivacy[0]);
    for (SellerPrivacy member : arr) {
      if (member.getId() == id) {
        return member;
      }
    }
    return null;
  }

  public Manager addMember(String label, int size) {
    int i;
    for (i=0; i<size; i++) {
      if (managerList.get(i).getId().equals(label)) {
        System.out.println("중복되는 아이디입니다.");
        return managerList.get(i);
      }
    }
    return null;
  }
  public Manager delMember(String label) {
    while (true) {
      for (int i=0; i<managerList.size(); i++) {
        if (managerList.get(i).getId().equals(label)) {
          return managerList.get(i);
        }
      } /*else if (label.length() == 0) {
          System.out.println("아이디를 입력해 주세요.");
          return null;
        }*/
      System.out.println("일치하는 아이디가 없습니다.");
      return null;
    }
  }
}







