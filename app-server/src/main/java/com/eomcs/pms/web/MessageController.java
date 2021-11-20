package com.eomcs.pms.web;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;

@Controller
public class MessageController {
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MessageDao messageDao;

  @GetMapping("/message/list")
  public ModelAndView list(HttpSession session) throws Exception {
    Member member = (Member)session.getAttribute("loginUser");
    Collection<Message> messages = messageDao.findAll(member.getNumber());

    MessageList messageList = new MessageList(); 
    messageList.setId(member.getId());
    messageList.setMessage((List<Message>) messages);

    ModelAndView mv = new ModelAndView();
    mv.addObject("messages", messages);
    mv.addObject("pageTitle", "메세지목록");
    mv.addObject("contentUrl", "message/MessageList.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("/message/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "메시지작성");
    mv.addObject("contentUrl", "message/MessageForm.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/message/add")
  public ModelAndView add(Message messageInput, HttpSession session) throws Exception {

    Member member = (Member)session.getAttribute("loginUser");
    String memberId = messageInput.getTheOtherId();
    Collection<Message> messages = messageDao.findAll(member.getNumber());

    for (Message message : messages) {
      if (message.getTheOtherId().equals(memberId) ||
          message.getId().equals(memberId)) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("roomNo",message.getRoomNumber());
        mv.setViewName("redirect:detail?no="+message.getRoomNumber());
        return mv;
      }
    }
    messageDao.insertRoomNo(messageInput);

    messageInput.setId(member.getId());
    messageDao.insert(messageInput);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no="+messageInput.getRoomNumber());
    return mv;
  }

  @GetMapping("/message/detail")
  public ModelAndView detail(int no, HttpSession session) throws Exception {
    Member member = (Member)session.getAttribute("loginUser");
    String nowLoginId = member.getId();
    String other = "";

    Collection<Message> messages = messageDao.findByNo(no);
    for (Message message : messages) {
      if (message.getId().equals(nowLoginId)) {
        other = message.getTheOtherId();
      } else {
        other = message.getId();
      }
    }

    if (messages == null) {
      throw new Exception("해당 번호의 메세지가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("messages", messages);
    mv.addObject("roomNumber", no);
    mv.addObject("theOtherId", other);
    mv.addObject("pageTitle", "메세지상세보기");
    mv.addObject("contentUrl", "message/MessageDetail.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/message/update")
  public ModelAndView update(Message message, HttpSession session) throws Exception {
    Member member = (Member)session.getAttribute("loginUser");
    message.setId(member.getId());

    messageDao.update(message);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no="+message.getRoomNumber());
    return mv;
  }

  @GetMapping("/message/delete")
  public ModelAndView delete(int no) throws Exception {
    messageDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }
}
