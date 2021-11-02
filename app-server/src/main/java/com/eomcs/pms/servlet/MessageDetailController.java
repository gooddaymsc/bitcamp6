package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Message;

@WebServlet("/message/detail")
public class MessageDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MessageDao messageDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    messageDao = (MessageDao) 웹애플리케이션공용저장소.getAttribute("messageDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      Member member = new Member();
      member.setId("admin");;

      String nowLoginId = member.getId();

      int no = Integer.parseInt(request.getParameter("no"));
      Collection<Message> messages = messageDao.findByNo(no);

      if (messages == null) {
        throw new Exception("해당 번호의 메세지가 없습니다.");
      }

      request.setAttribute("messages", messages);   
      request.setAttribute("nowLoginId", nowLoginId); 
      request.getRequestDispatcher("/message/MessageDetail.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }       
  }
}

