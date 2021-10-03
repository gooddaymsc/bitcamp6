package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import com.eomcs.pms.dao.FindPasswordDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;

public class NetFindPasswordDao implements FindPasswordDao {

  RequestAgent requestAgent;

  public NetFindPasswordDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }

  public Member findPasswordByName(String Name) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("Name", String.valueOf(Name));

    requestAgent.request("member.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Member.class);
  }
}
