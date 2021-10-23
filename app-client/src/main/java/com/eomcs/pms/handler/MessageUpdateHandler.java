package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.util.Prompt;

public class MessageUpdateHandler implements Command {

  MessageDao messageDao;
  SqlSession sqlSession;
  public MessageUpdateHandler (MessageDao messageDao, SqlSession sqlSession) {
    this.messageDao = messageDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    System.out.println("[답장]");
    String other = (String) request.getAttribute("otherId");
    int no = (Integer) request.getAttribute("messageNo");
    Message message = new Message();
    message.setRoomNumber(no);
    message.setContent(Prompt.inputString("내용 : "));
    message.setId(nowLoginId);
    message.setTheOtherId(other);

    //        memberPrompt.sendMessageUpdate(message.getTheOtherId());
    messageDao.update(message);
    sqlSession.commit();
    System.out.println("메세지를 보냈습니다.\n");
  }
}






