package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Message;

public interface MessageDao {
  //  void insert(Message message, String id) throws Exception;
  List<Message> findAll(int memberNo) throws Exception;
  List<Message> findByNo(int roomNo) throws Exception;
  void update(Message message) throws Exception;
  //  void delete(Message message) throws Exception;
}
