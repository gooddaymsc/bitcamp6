package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import com.eomcs.pms.dao.FindIdDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;

public class NetFindIdDao implements FindIdDao {

  RequestAgent requestAgent;

  public NetFindIdDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }

  @Override
  public Member findIdByName(String Name) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("Name", String.valueOf(Name));

    requestAgent.request("member.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Member.class);
  }
}
