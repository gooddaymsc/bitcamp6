package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.util.Prompt;

public class MessageUpdateHandler implements Command {

  MessageDao messageDao;
  public MessageUpdateHandler (MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[답장]");

    int No = (Integer) request.getAttribute("MessageNo");

    MessageList messageList = messageDao.findAll(nowLoginId);

    for (Message message : messageList.getMessage()) {
      if (message.getMessageNumber()==No) {
        String newStr = message.getAllContent()+"/"+nowLoginId+" : "+Prompt.inputString("내용 : ");
        message.setAllContent(newStr);
        //        memberPrompt.sendMessageUpdate(message.getTheOtherId());
        messageDao.update(message);
      }
    }
    System.out.println("메세지를 보냈습니다.\n");
  }
}






