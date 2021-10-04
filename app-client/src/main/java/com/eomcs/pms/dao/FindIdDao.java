package com.eomcs.pms.dao;

import com.eomcs.pms.domain.Member;
public interface FindIdDao {
  Member findIdByName(String Name) throws Exception;
}
