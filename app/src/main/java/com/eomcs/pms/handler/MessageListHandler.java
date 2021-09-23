package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.util.Prompt;

public class MessageListHandler extends AbstractMessageHandler {

  MemberPrompt memberPrompt;

  public MessageListHandler(List<MessageList> allMessageList, MemberPrompt memberPrompt) {
    super(allMessageList);
    this.memberPrompt = memberPrompt;
  }

  public static int messageNumber = 1;
  @Override
  public void execute(CommandRequest request) throws Exception {
    Loop : while(true) {
      if (App.getLoginUser().isMessageUpdate()) {
        memberPrompt.returnMessageUpdate(App.getLoginUser().getId());
      }

      String nowLoginId = App.getLoginUser().getId();
      System.out.println("[메세지 확인]");

      MessageList messageList = findMessageListById(nowLoginId);

      if (messageList.getMessage().size() == 0) {
        System.out.println("받은 메세지가 없습니다.");
      }

      for (Message message : messageList.getMessage()) {
        System.out.printf("No : %d\tID : %-6s\tRecentDate : %s\n", 
            message.getMessageNumber(), message.getTheOtherId(), message.getRegistrationDate());
      }
      System.out.println();
      while(true) {
        System.out.println("< 1. 새 메세지 / 2. 대화보기 / 이전(0) >");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "0" : return;
          case "1" : request.getRequestDispatcher("/message/add").forward(request); continue Loop;
          case "2" : request.getRequestDispatcher("/message/detail").forward(request); continue Loop;
          default : System.out.println("잘못입력하셨습니다"); continue;
        }
      }
    }
  }
}