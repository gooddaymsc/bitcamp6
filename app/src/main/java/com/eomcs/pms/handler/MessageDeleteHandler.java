package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Message;
import com.eomcs.util.Prompt;

public class MessageDeleteHandler extends AbstractMessageHandler {

  public MessageDeleteHandler(List<Message> messageList) {
    super(messageList);
  }

  @Override
  public void execute(CommandRequest request) {

    System.out.println("[메세지 삭제]");
    //    String writer = (String) request.getAttribute("writer");
    String writer = Prompt.inputString("메세지 삭제할 상대 아이디 : ");

    Message message = findMessageById(writer);

    if (message == null) {
      System.out.println("삭제 가능한 메세지가 없습니다.");
      return;
    }

    if (!((message.getWriter().equals(App.getLoginUser().getId())) ||
        (App.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN))) {
      System.out.println("작성자가 아니므로 삭제할 수 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      messageList.remove(message);
      System.out.println("메세지를 삭제하였습니다.");
      return;
    }
    System.out.println("메세지 삭제를 취소하였습니다.");
    return;
  }

}






