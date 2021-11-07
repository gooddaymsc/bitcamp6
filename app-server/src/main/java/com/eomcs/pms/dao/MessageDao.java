package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Message;

public interface MessageDao {
  void insertRoomNo(Message message) throws Exception;
  void insert(Message message) throws Exception;
  List<Message> findAll(int memberNo) throws Exception;
  List<Message> findByNo(int roomNumber) throws Exception;
  void update(Message message) throws Exception;
  void delete(int roomNumber) throws Exception;
}
