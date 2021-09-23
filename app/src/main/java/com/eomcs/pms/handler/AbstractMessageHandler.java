package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;

public abstract class AbstractMessageHandler implements Command {

  protected List<MessageList> allMessageList;

  public AbstractMessageHandler(List<MessageList> allMessageList) {
    this.allMessageList = allMessageList;
  }

  protected void addMessageListById(String memberId) {
    MessageList messageList = new MessageList();
    messageList.setReceivedId(memberId);
    allMessageList.add(messageList);
  }

  protected void putMessageListById(String memberId, Message message) {
    for (MessageList messageList : allMessageList) {
      if (messageList.getReceivedId().equals(memberId)) {
        int messageListNumber = messageList.getMessageListNumber();
        message.setMessageNumber(messageListNumber);
        messageList.getMessage().add(message);
        messageList.setMessageListNumber(++messageListNumber);
      }
    }
  }

  protected int findMessageById(String id) {
    for (int i = 0; i < allMessageList.size(); i++) {
      if (allMessageList.get(i).getReceivedId().equalsIgnoreCase(id)) {
        return i;
      }
    }
    return -1;
  }

  //  protected Message checkMessageById(String id) {
  //    for (MessageList messageList : allMessageList) {
  //      if (messageList.getReceivedId().equals(id)) {
  //        return messageList;
  //      }
  //    }
  //    return null;
  //  }

  protected boolean removeMessageById(String id, String memberId) {
    MessageList messageList = allMessageList.get(findMessageById(memberId));
    for (int i = 0; i < messageList.getMessage().size(); i++) {
      if (messageList.getMessage().get(i).getRecipientId().equals(memberId)) {
        allMessageList.get(findMessageById(id)).getMessage().remove(i);
        return true;
      }
    }
    return false;
  }


}