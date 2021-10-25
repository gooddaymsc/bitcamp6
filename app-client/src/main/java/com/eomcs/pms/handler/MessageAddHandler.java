package com.eomcs.pms.handler;

import java.util.Collection;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Message;
import com.eomcs.util.Prompt;

public class MessageAddHandler implements Command {

  MessageDao messageDao;
  SqlSession sqlSession;
  MemberDao memberDao;
  public MessageAddHandler (MessageDao messageDao, SqlSession sqlSession, MemberDao memberDao) {
    this.messageDao = messageDao;
    this.sqlSession = sqlSession;
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = ClientApp.getLoginUser().getId();
    int nowLoginNo = ClientApp.getLoginUser().getNumber();
    System.out.println("[새 메세지]");

    String memberId = Prompt.inputString("대화할 상대(id) : ");
    if (memberDao.findById(memberId)==null) {
      System.out.println("없는 Id 입니다.\n");
      return;
    }

    Collection<Message> messages = messageDao.findAll(nowLoginNo);
    // 이미 상대id랑 주고 받은 대화가 있는가

    for (Message message : messages) {
      if (message.getTheOtherId().equals(memberId) ||
          message.getId().equals(memberId)) {
        request.setAttribute("roomNo", message.getRoomNumber());
        request.getRequestDispatcher("/message/detail").forward(request);
      }
    }
    // 없으면

    Message message = new Message();
    try {
      messageDao.insertRoomNo(message);
      message.setContent(Prompt.inputString("내용 : "));
      message.setTheOtherId(memberId);
      message.setId(nowLoginId);
      messageDao.insert(message);
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
    }

    //    memberPrompt.sendMessageUpdate(memberId);
    System.out.println("메세지를 보냈습니다.\n");
  }
}






