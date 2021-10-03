package com.eomcs.pms.dao;

import com.eomcs.pms.domain.Member;
public interface FindPasswordDao {
  Member findPasswordByName(String Name) throws Exception;
}
