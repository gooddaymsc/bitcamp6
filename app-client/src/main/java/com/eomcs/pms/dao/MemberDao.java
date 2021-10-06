package com.eomcs.pms.dao;

import com.eomcs.pms.domain.Member;

public interface MemberDao {
  Member findByName(String Name) throws Exception;
  Member findById(String id) throws Exception;

}
