package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;

public interface MessageDao {
  void insert(Message message, String id) throws Exception;
  List<MessageList> findAll() throws Exception;
  MessageList findAll(String id) throws Exception;
  void update(Message message) throws Exception;
  void delete(Message message) throws Exception;
}
