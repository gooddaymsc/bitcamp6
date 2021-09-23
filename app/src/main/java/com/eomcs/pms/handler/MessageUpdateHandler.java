package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.util.Prompt;

public class MessageUpdateHandler extends AbstractMessageHandler {

  MemberPrompt memberPrompt;

  public MessageUpdateHandler(List<MessageList> allMessageList, MemberPrompt memberPrompt) {
    super(allMessageList);
    this.memberPrompt = memberPrompt;
  }

  @Override
  public void execute(CommandRequest request) {

    System.out.println("[답장]");

    int No = (Integer) request.getAttribute("MessageNo");

    MessageList messageList = findMessageListById(App.getLoginUser().getId());

    for (Message message : messageList.getMessage()) {
      if (message.getMessageNumber()==No) {
        String newStr = Prompt.inputString("내용 : ");
        message.setAllContent(newStr);
        memberPrompt.sendMessageUpdate(message.getTheOtherId());

        MessageList messageList1 = findMessageListById(message.getTheOtherId());
        Message message1 = findMessageById(messageList1, App.getLoginUser().getId());
        message1.setAllContent(newStr);

      }
    }

    System.out.println("메세지를 보냈습니다.\n");
  }
}






