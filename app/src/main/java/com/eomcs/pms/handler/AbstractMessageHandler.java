package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;

public abstract class AbstractMessageHandler implements Command {

  protected List<MessageList> allMessageList;

  public AbstractMessageHandler(List<MessageList> allMessageList) {
    this.allMessageList = allMessageList;
  }

  protected void putMessageListById(String memberId, Message message) {
    for (MessageList messageList : allMessageList) {
      if (messageList.getRecipientId().equals(memberId)) {
        int messageListNumber = messageList.getMessageListNumber();
        message.setMessageNumber(messageListNumber);
        messageList.getMessage().add(message);
        messageList.setMessageListNumber(++messageListNumber);
      }
    }
  }

  public int findMessageById(String id) {

    for (int i = 0; i < allMessageList.size(); i++) {
      if (allMessageList.get(i).getRecipientId().equalsIgnoreCase(id)) {
        return i;
      }
    }
    return -1;

    //    for (MessageList message : allMessageList) {
    //      if (message.getRecipientId().equalsIgnoreCase(id)) {
    //        return message;
    //      }
    //    }
    //    return null;
  }

  //  public Message findByWriter(String writer) {
  //
  //    for (Message message : allMessageList) {
  //      if (message.getWriter() == writer) {
  //        return message;
  //      }
  //    }
  //    return null;
  //  }

  protected boolean removeMessageById(String id, String memberId) {
    MessageList messageList = allMessageList.get(findMessageById(id));
    for (int i = 0; i < messageList.getMessage().size(); i++) {
      if (messageList.getMessage().get(i).getRecipientId().equals(memberId)) {
        allMessageList.get(findMessageById(id)).getMessage().remove(i);
        return true;
      }
    }
    return false;
  }


}