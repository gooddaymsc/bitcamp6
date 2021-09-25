package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public abstract class AbstractSellerHandler implements Command {

  protected List<Member> memberList;

  public AbstractSellerHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  int size=1;

  protected Member findById(String id) {
    for (Member seller : memberList) {
      if (seller.getId().equals(id)) {
        return seller;
      }
    }
    return null;
  }
  protected int checkLevel(String label) {
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







