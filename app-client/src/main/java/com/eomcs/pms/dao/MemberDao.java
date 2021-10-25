package com.eomcs.pms.dao;

import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Member;

public interface MemberDao {
  Member findByName(String Name) throws Exception;
  Member findById(String id) throws Exception;
  Member findByIdAndPassword(@Param("id") String id, @Param("password") String password) throws Exception;
  void changeBookingUpdate(String id, boolean check) throws Exception;
  void changeCommentUpdate(String id, boolean check) throws Exception;
  void changeMessageUpdate(String id, boolean check) throws Exception;
}
