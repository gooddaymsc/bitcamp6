package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;

public abstract class AbstractMessageHandler implements Command {

  protected List<MessageList> allMessageList;

  public AbstractMessageHandler(List<MessageList> allMessageList) {
    this.allMessageList = allMessageList;
  }

  public MessageList findMessageListById(String id) {
    for (MessageList messageList : allMessageList) {
      if (messageList.getId().equals(id)) {
        return messageList;
      }
    }
    return null;
  }

  public Message findMessageById(MessageList messageList, String id) {
    //id 는 받는사람.
    for (Message message : messageList.getMessage()) {
      if (message.getTheOtherId().equals(id)) {
        return message;
      }
    }
    return null;
  }

}