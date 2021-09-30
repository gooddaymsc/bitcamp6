package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class MessageAddHandler implements Command {

  RequestAgent requestAgent;
  public MessageAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[새 메세지]");

    String memberId = Prompt.inputString("대화할 상대(id) : ");
    if (memberPrompt.findById(memberId)==null) {
      System.out.println("없는 Id 입니다.\n");
      return;
    }

    MessageList messageList = findMessageListById(App.getLoginUser().getId());
    // 이미 상대id랑 주고 받은 대화가 있는가
    Message messageIs = findMessageById(messageList, memberId);

    // 없으면
    if (messageIs!=null) {
      System.out.println("이미 있는 대화방입니다.\n");
      request.getRequestDispatcher("/message/list").forward(request);
      return;
    }
    Message message = new Message();
    message.setMessageNumber(messageList.getMessageListNumber());
    messageList.setMessageListNumber(messageList.getMessageListNumber()+1);
    String newStr = Prompt.inputString("내용 : ");
    message.setAllContent(newStr);
    message.setTheOtherId(memberId);
    message.setRegistrationDate(new Date(System.currentTimeMillis()));

    messageList.getMessage().add(message);

    MessageList messageList1 = findMessageListById(memberId);
    Message message1 = new Message();
    message1.setMessageNumber(messageList1.getMessageListNumber());
    messageList1.setMessageListNumber(messageList1.getMessageListNumber()+1);
    message1.setAllContent(newStr);
    message1.setTheOtherId(App.getLoginUser().getId());
    message1.setRegistrationDate(new Date(System.currentTimeMillis()));

    messageList1.getMessage().add(message1);

    memberPrompt.sendMessageUpdate(memberId);
    System.out.println("메세지를 보냈습니다.\n");
  }
}






