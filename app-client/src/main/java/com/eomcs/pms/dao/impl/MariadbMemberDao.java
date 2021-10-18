package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

public class MariadbMemberDao implements MemberDao{

  Connection con;

  public MariadbMemberDao(Connection con) {
    this.con =  con;
  }

  @Override
  public Member findByName(String name) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,email,phoneNumber,registeredDate from member"
            + "where name = ?")) {
      stmt.setString(1, name);

      try(ResultSet rs = stmt.executeQuery()){
        if(!rs.next()) {
          return null;
        }
        Member member = new Member();
        member.setNumber(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPhoto(rs.getString("photo"));
        member.setPhoneNumber(rs.getString("phoneNumber"));
        member.setRegisteredDate(rs.getDate("registeredDate"));
        return member;
      }
    }
  }

  @Override
  public Member findById(String id) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("id", String.valueOf(id));
    //    requestAgent.request("member.find", params);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      return null;
    //    }
    //    return requestAgent.getObject(Member.class);
    return null;
  }

  @Override
  public Member findByEmailAndPassword(String id, String password) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,email,photo,phoneNumber, registeredDate from member"
            + "where id=? and password = password(?)")){
      stmt.setString(1, id);
      stmt.setString(2, password);

      try(ResultSet rs = stmt.executeQuery()){
        if(!rs.next()) {
          return null;
        }
        Member member = new Member();
        member.setNumber(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPhoto(rs.getString("photo"));
        member.setPhoneNumber(rs.getString("phoneNumber"));
        member.setRegisteredDate(rs.getDate("registeredDate"));
        return member;
      }
    }
  }

  @Override
  public void changeBookingUpdate(String id, boolean check) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("id", String.valueOf(id));
    //    params.put("boolean", String.valueOf(check));
    //    requestAgent.request("member.update.booking", params);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      throw new Exception("예약알림 실패!");
    //    }
  }

  @Override
  public void changeCommentUpdate(String id, boolean check) throws Exception {
    //    if (check) {
    //      HashMap<String, String> params = new HashMap<>();
    //      params.put("id", String.valueOf(id));
    //      params.put("boolean", String.valueOf(check));
    //      requestAgent.request("member.update.comment", params);
    //      if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //        throw new Exception("댓글알림 실패!");
    //      }
    //    } else {
    //      HashMap<String, String> params = new HashMap<>();
    //      params.put("id", String.valueOf(id));
    //      params.put("boolean", String.valueOf(check));
    //      requestAgent.request("member.update.comment", params);
    //      if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //        throw new Exception("댓글알림 실패!");
    //      }
    //      ClientApp.loginMember = requestAgent.getObject(Member.class);
    //    }
  }

  @Override
  public void changeMessageUpdate(String id, boolean check) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("id", String.valueOf(id));
    //    params.put("boolean", String.valueOf(check));
    //    requestAgent.request("member.update.message", params);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      throw new Exception("메시지알림 실패!");
    //    }
  }
}
