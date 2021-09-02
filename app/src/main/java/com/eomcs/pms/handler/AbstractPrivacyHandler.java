package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Privacy;

public abstract class AbstractPrivacyHandler implements Command {

  protected List<Privacy> privacyList;

  int size = 1;

  public AbstractPrivacyHandler (List<Privacy> privacyList) {
    this.privacyList = privacyList;
  }

  protected Privacy findById(String id) {
    Privacy[] arr = privacyList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }
}






