package com.eomcs.pms.dao;

import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;

public interface MessageDao {
  void insert(Message message, String id) throws Exception;
  MessageList findAll(String id) throws Exception;
  void update(Message message) throws Exception;
  void delete(Message message) throws Exception;
}
