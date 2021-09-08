package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Privacy;

public abstract class AbstractPrivacyHandler implements Command {

  protected Privacy findById(String id) {
    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (Privacy member : arr) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }

  protected int removePrivateById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<App.privacyList.size(); i++) {
      if (App.privacyList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
  protected int removemanagerById(String id) {
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<App.managerList.size(); i++) {
      if (App.managerList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
}






