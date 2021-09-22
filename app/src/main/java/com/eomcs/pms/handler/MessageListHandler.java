package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Message;
import com.eomcs.util.Prompt;

public class MessageListHandler extends AbstractMessageHandler {

  MemberPrompt memberPrompt;

  public MessageListHandler(List<Message> messageList, MemberPrompt memberPrompt) {
    super(messageList);
    this.memberPrompt = memberPrompt;
  }

  public static int messageNumber = 1;
  @Override
  public void execute(CommandRequest request) throws Exception {
    if (App.getLoginUser().isMessageUpdate()) {
      memberPrompt.returnMessageUpdate(App.getLoginUser().getId());
    }

    System.out.println("[메세지 확인]");

    Message checkedIdmessage = findMessageById(App.getLoginUser().getId());
    if (checkedIdmessage == null) {
      System.out.println("받은 메세지가 없습니다.");
      return;
    }
    while(true) {
      for (Message message : messageList) {

        if (message.equals(findMessageById(App.getLoginUser().getId()))) {
          System.out.printf("1. 보낸사람 :%s\n", message.getWriter());
          System.out.printf("2. 제목 :%s\n", message.getTitle());
          System.out.printf("3. 내용 :%s\n", message.getContent());
          System.out.printf("4. 보낸 날짜 :%s\n", message.getRegistrationDate());
          System.out.println("------------------------------------");
        }
        //        request.setAttribute("writer", message.getWriter());
      }

      System.out.println("\n< 1.답장 / 2.삭제 / 0.이전  >");
      String choose = Prompt.inputString("선택 > ");

      switch (choose) {
        case "0" : return;
        case "1" : request.getRequestDispatcher("/message/add").forward(request); return;
        //        case "2" : request.getRequestDispatcher("/message/delete").forward(request); return;
        default : System.out.println("잘못입력하셨습니다"); continue;
      }
    }
  }
}
