package com.eomcs.pms.dao;

import com.eomcs.pms.domain.Member;

public interface MemberDao {
  Member findByName(String Name) throws Exception;
  Member findById(String id) throws Exception;
  void changeBookingUpdate(String id, boolean check) throws Exception;
  void changeCommentUpdate(String id, boolean check) throws Exception;
  void changeMessageUpdate(String id, boolean check) throws Exception;
}
