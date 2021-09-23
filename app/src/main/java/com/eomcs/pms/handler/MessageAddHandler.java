package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.util.Prompt;

public class MessageAddHandler extends AbstractMessageHandler {

  MemberPrompt memberPrompt;

  public MessageAddHandler(List<MessageList> allMessageList, MemberPrompt memberPrompt) {
    super(allMessageList);
    this.memberPrompt = memberPrompt;
  }

  public static int messageNumber = 1;
  @Override
  public void execute(CommandRequest request) {

    //    String nowLoginId = App.getLoginUser().getId();

    System.out.println("[새 메세지]");

    Message message = new Message();
    String memberId = Prompt.inputString("대화할 상대(id) : ");
    //    Member recipient = memberPrompt.findById(memberId);
    message.setRecipientId(memberId);

    message.setMessageNumber(messageNumber++);
    message.setTitle(Prompt.inputString("제목 : "));
    message.setContent(Prompt.inputString("내용 : "));
    message.setWriter(App.getLoginUser().getId());
    message.setRegistrationDate(new Date(System.currentTimeMillis()));


    //    messageList.add(message);
    putMessageListById(memberId, message);
    memberPrompt.sendMessageUpdate(memberId);
    System.out.println("메세지를 보냈습니다.");
  }
}






