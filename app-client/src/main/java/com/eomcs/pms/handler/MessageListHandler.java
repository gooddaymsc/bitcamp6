package com.eomcs.pms.handler;

import java.util.Collection;
import java.util.List;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.util.Prompt;

public class MessageListHandler implements Command {

  MessageDao messageDao;
  public MessageListHandler (MessageDao messageDao) {
    this.messageDao = messageDao;
  }

  public static int messageNumber = 1;
  @Override
  public void execute(CommandRequest request) throws Exception {
    int nowLoginNo = ClientApp.getLoginUser().getNumber();
    String nowLoginId = ClientApp.getLoginUser().getId();
    Loop : while(true) {
      if (ClientApp.getLoginUser().isMessageUpdate()) {
        //메시지 알림 보내기
        //        memberPrompt.returnMessageUpdate(nowLoginId);
      }

      System.out.println("[메세지 확인]");
      Collection<Message> messages = messageDao.findAll(nowLoginNo);
      MessageList messageList = new MessageList();
      messageList.setId(nowLoginId);
      messageList.setMessage((List<Message>) messages);
      if (messageList.getMessage().size() == 0) {
        System.out.println("받은 메세지가 없습니다.");
      }

      for (Message message : messageList.getMessage()) {
        String other = "";
        if (message.getId().equals(nowLoginId)) {
          other = message.getTheOtherId();
        } else {
          other = message.getId();
        }
        System.out.printf("No : %d\tID : %s\tContent : %s\tRecentDate : %s\n", 
            message.getRoomNumber(), other, message.getContent(), message.getRegistrationDate());
      }
      System.out.println();
      while(true) {
        System.out.println("새 메세지(A) / 대화보기(R) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        if (choose.equalsIgnoreCase("r")) {
          int no = Prompt.inputInt("대화방 번호 > ");
          request.setAttribute("roomNo", no);
        }
        System.out.println();
        switch (choose) {
          case "0" : return;
          case "A":
          case "a" : request.getRequestDispatcher("/message/add").forward(request); continue Loop;
          case "R":
          case "r" : request.getRequestDispatcher("/message/detail").forward(request); continue Loop;
          default : System.out.println("잘못입력하셨습니다"); continue;
        }
      }
    }
  }
}