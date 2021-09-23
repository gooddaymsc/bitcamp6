package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.MessageList;

public class MessagePrompt {

  List<MessageList> allMessageList;
  public MessagePrompt(List<MessageList> allMessageList) {
    this.allMessageList = allMessageList;
  }

  public void addMessageListById(String id) {
    MessageList messageList = new MessageList();
    messageList.setId(id);
    allMessageList.add(messageList);
  }

  protected int getMessageIndexById(String id) {
    for (int i=0; i< allMessageList.size(); i++) {
      if (allMessageList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  public void removeMessageListById(String nowLoginid) {
    allMessageList.remove(getMessageIndexById(nowLoginid));
  }
}
