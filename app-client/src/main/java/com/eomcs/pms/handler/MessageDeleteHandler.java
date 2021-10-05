package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.util.Prompt;

public class MessageDeleteHandler implements Command {

  MessageDao messageDao;
  public MessageDeleteHandler (MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  @Override
  public void execute(CommandRequest request) {

    String nowLoginId = App.getLoginUser().getId();

    System.out.println("[메세지 삭제]");
    int No = (Integer) request.getAttribute("MessageNo");

    MessageList messageList = findMessageListById(nowLoginId);

    for (Message message : messageList.getMessage()) {
      if (message.getMessageNumber()==No) {
        String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
        if (input.equalsIgnoreCase("y")) {
          messageList.getMessage().remove(message);
          MessageList messageList1 = findMessageListById(message.getTheOtherId());
          Message message1 = findMessageById(messageList1, nowLoginId);
          messageList1.getMessage().remove(message1);
          System.out.println("메세지를 삭제하였습니다.\n");
          return;
        }
        System.out.println("메세지 삭제를 취소하였습니다.\n");
        return;
      }
    }
  }
}






