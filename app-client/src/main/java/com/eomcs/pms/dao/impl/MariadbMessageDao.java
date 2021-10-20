package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;

public class MariadbMessageDao implements MessageDao {

  Connection con;
  public MariadbMessageDao(Connection con) {
    this.con =  con;
  }
  @Override
  public void insert(Message message, String id) throws Exception {
  }
  @Override
  public MessageList findAll(int memberNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " mg.member_no, mg.member_no2, mg.content, mg.registeredDate,"
            + " m.id"
            + " from message mg left outer join member m on mg.member_no=m.member_no"
            + " where mg.member_no = ? or mg.member_no2 = ?"
            + " order by mg.registeredDate desc")) {
      stmt.setInt(1, memberNo);
      stmt.setInt(2, memberNo);
      ResultSet rs = stmt.executeQuery();

      MessageList messageList = new MessageList();
      messageList.setId(ClientApp.getLoginUser().getId());
      ArrayList<Message> list = new ArrayList<>();

      while (rs.next()) {
        Message message = new Message();
        message.setId(null);
        message.setTheOtherId(null);
        list.add(message);
      }

      return messageList;
    }
  }

  @Override
  public void update(Message message) throws Exception {
  }

  @Override
  public void delete(Message message) throws Exception {
  }

}
