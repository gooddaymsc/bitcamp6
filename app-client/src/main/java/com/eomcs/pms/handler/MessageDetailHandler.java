package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.util.Prompt;

public class MessageDetailHandler implements Command {

  MessageDao messageDao;
  public MessageDetailHandler (MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[대화 상세보기]");
    String nowLoginId = ClientApp.getLoginUser().getId();
    String other = "";
    int no = Prompt.inputInt("대화방 번호 > ");
    Loop : while(true) {
      boolean bool = false;
      Collection<Message> messages = messageDao.findByNo(no);
      for (Message message : messages) {
        System.out.printf("ID : %s\tContent : %15s\tRecentDate : %s\n", 
            message.getId(), message.getContent(), message.getRegistrationDate());
        if (message.getId().equals(nowLoginId)) {
          other = message.getTheOtherId();
        } else {
          other = message.getId();
        }
        bool = true;
      }
      if (!bool) {
        System.out.println("해당 번호의 대화내용이 없습니다.\n");
        return;
      }
      request.setAttribute("otherId", other);
      request.setAttribute("messageNo", no);
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
