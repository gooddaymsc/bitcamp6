package com.eomcs.pms.handler;

import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.util.Prompt;

public class MessageDetailHandler implements Command {

  MessageDao messageDao;
  public MessageDetailHandler (MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  public static int messageNumber = 1;
  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[대화 상세보기]");

    int No = Prompt.inputInt("대화방 번호 > ");
    Loop : while(true) {
      boolean bool = false;
      MessageList messageList = messageDao.findAll(nowLoginId);
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
        System.out.println("답장(U) / 삭제(D) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch (choose) {
          case "0" : return;
          case "U":
          case "u" : request.getRequestDispatcher("/message/update").forward(request); continue Loop;
          case "D":
          case "d" : request.getRequestDispatcher("/message/delete").forward(request); return;
          default : System.out.println("잘못입력하셨습니다"); continue;
        }
      }
    }
  }
}
