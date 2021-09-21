package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Message;

public abstract class AbstractMessageHandler implements Command {

  protected List<Message> messageList;

  public AbstractMessageHandler(List<Message> messageList) {
    this.messageList = messageList;
  }

  public Message findMessageById(String id) {

    for (Message message : messageList) {
      if (message.getRecipientId().equalsIgnoreCase(id)) {
        return message;
      }
    } //해쉬맵...
    return null;
  }

  public Message findByWriter(String writer) {

    for (Message message : messageList) {
      if (message.getWriter() == writer) {
        return message;
      }
    }
    return null;
  }



}