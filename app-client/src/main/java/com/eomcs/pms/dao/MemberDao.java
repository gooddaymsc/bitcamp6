package com.eomcs.pms.dao;

import com.eomcs.pms.domain.Member;

public interface MemberDao {
  Member findByName(String Name) throws Exception;
}
