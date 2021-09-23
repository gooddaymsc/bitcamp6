package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.util.Prompt;

public class MessageDetailHandler extends AbstractMessageHandler {


  public MessageDetailHandler(List<MessageList> allMessageList) {
    super(allMessageList);
  }

  public static int messageNumber = 1;
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = App.getLoginUser().getId();
    System.out.println("[대화 상세보기]");

    int No = Prompt.inputInt("대화방 번호 > ");
    Loop : while(true) {
      boolean bool = false;
      MessageList messageList = findMessageListById(nowLoginId);
      for (Message message : messageList.getMessage()) {
        if (message.getMessageNumber()==No) {
          String[] str = message.getAllContent().split("/");
          for (int i=0; i<str.length; i++) {
            System.out.printf("%-20s\t%s\n",str[i], message.getRegistrationDate());
          }
          bool = true;
        }
      }
      if (!bool) {
        System.out.println("해당 번호의 대화내용이 없습니다.\n");
        return;
      }
      request.setAttribute("MessageNo", No);
      System.out.println();
      while(true) {
        System.out.println("< 1. 답장 / 2. 삭제 / 이전(0) >");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "0" : return;
          case "1" : request.getRequestDispatcher("/message/update").forward(request); continue Loop;
          case "2" : request.getRequestDispatcher("/message/delete").forward(request); return;
          default : System.out.println("잘못입력하셨습니다"); continue;
        }
      }
    }
  }
}
