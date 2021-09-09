package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;

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
}
