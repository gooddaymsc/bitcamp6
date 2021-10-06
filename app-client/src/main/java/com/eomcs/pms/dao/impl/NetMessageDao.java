package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.request.RequestAgent;
import com.google.gson.Gson;

public class NetMessageDao implements MessageDao {

  RequestAgent requestAgent;
  public NetMessageDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }
  @Override
  public void insert(Message message, String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);
    params.put("message", new Gson().toJson(message));
    requestAgent.request("message.insert", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("예약 데이터 저장 실패");
    }
  }
  @Override
  public MessageList findAll(String id) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("id", id);
    requestAgent.request("message.selectList", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(MessageList.class);
  }

  @Override
  public void update(Message message) throws Exception {
    requestAgent.request("message.update", message);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("메세지 삭제 실패!");
    }
  }

  @Override
  public void delete(Message message) throws Exception {
    requestAgent.request("message.delete", message);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("메세지 삭제 실패!");
    }
  }
}
