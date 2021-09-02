package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;

public abstract class AbstractPrivacyHandler implements Command {

  protected List<Privacy> memberList;
  protected List<Manager> managerList;
  int size = 1;

  public AbstractPrivacyHandler (List<Privacy> memberList, List<Manager> managerList) {
    this.memberList = memberList;
    this.managerList = managerList;
  }

  protected Privacy findById(String id) {
    Privacy[] arr = memberList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
      if (member.getId().equals(id)) {
        return member;
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






