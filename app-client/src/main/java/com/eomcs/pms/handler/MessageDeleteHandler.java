package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
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
  public void execute(CommandRequest request) throws Exception {

    String nowLoginId = ClientApp.getLoginUser().getId();

    System.out.println("[메세지 삭제]");
    int no = (Integer) request.getAttribute("messageNo");

    MessageList messageList = messageDao.findAll(nowLoginId);

    for (Message message : messageList.getMessage()) {
      if (message.getMessageNumber()==No) {
        String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
        if (input.equalsIgnoreCase("y")) {
          messageDao.delete(message);
          System.out.println("메세지를 삭제하였습니다.\n");
          return;
        }
        System.out.println("메세지 삭제를 취소하였습니다.\n");
        return;
      }
    }
  }
}






