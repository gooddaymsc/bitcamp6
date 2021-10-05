package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;

public class NetMemberDao implements MemberDao{
  RequestAgent requestAgent;

  public NetMemberDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }

  @Override
  public Member findByName(String name) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("name", String.valueOf(name));

    requestAgent.request("member.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Member.class);
  }
  @Override
  public Member findById(String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", String.valueOf(id));

    requestAgent.request("member.find", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Member.class);
  }
}
