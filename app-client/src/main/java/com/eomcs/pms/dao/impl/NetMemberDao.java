package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import com.eomcs.pms.ClientApp;
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

  @Override
  public Member findByEmailAndPassword(String id, String password) throws Exception {

    return null;
  }

  @Override
  public void changeBookingUpdate(String id, boolean check) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", String.valueOf(id));
    params.put("boolean", String.valueOf(check));
    requestAgent.request("member.update.booking", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("예약알림 실패!");
    }
  }

  @Override
  public void changeCommentUpdate(String id, boolean check) throws Exception {
    if (check) {
      HashMap<String, String> params = new HashMap<>();
      params.put("id", String.valueOf(id));
      params.put("boolean", String.valueOf(check));
      requestAgent.request("member.update.comment", params);
      if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
        throw new Exception("댓글알림 실패!");
      }
    } else {
      HashMap<String, String> params = new HashMap<>();
      params.put("id", String.valueOf(id));
      params.put("boolean", String.valueOf(check));
      requestAgent.request("member.update.comment", params);
      if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
        throw new Exception("댓글알림 실패!");
      }
      ClientApp.loginMember = requestAgent.getObject(Member.class);
    }
  }

  @Override
  public void changeMessageUpdate(String id, boolean check) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", String.valueOf(id));
    params.put("boolean", String.valueOf(check));
    requestAgent.request("member.update.message", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("메시지알림 실패!");
    }
  }
}
