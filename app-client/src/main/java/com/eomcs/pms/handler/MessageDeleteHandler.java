package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.util.Prompt;

public class MessageDeleteHandler implements Command {

  MessageDao messageDao;
  SqlSession sqlSession;
  public MessageDeleteHandler (MessageDao messageDao, SqlSession sqlSession) {
    this.messageDao = messageDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[대화방 삭제]");
    int no = (Integer) request.getAttribute("roomNo");
    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      messageDao.delete(no);
      sqlSession.commit();
      System.out.println("메세지를 삭제하였습니다.\n");
      return;
    }
    System.out.println("메세지 삭제를 취소하였습니다.\n");
    return;
  }
}






