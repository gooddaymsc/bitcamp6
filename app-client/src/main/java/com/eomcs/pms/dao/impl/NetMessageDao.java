package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.request.RequestAgent;

public class NetMessageDao implements MessageDao {

  RequestAgent requestAgent;
  public NetMessageDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }
  //  @Override
  //  public void insert(Message message, String id) throws Exception {
  //    HashMap<String, String> params = new HashMap<>();
  //    params.put("id", id);
  //    params.put("message", Gson().);
  //    requestAgent.request("message.insert", params);
  //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
  //      return null;
  //    }
  //
  //  }

  @Override
  public List<MessageList> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
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
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(Message message) throws Exception {
    // TODO Auto-generated method stub

  }
}
