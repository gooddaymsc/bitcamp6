package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Message;

@WebServlet("/message/detail")
public class MessageDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MessageDao messageDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    messageDao = (MessageDao) 웹애플리케이션공용저장소.getAttribute("messageDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      Member member = (Member) request.getSession(false).getAttribute("loginUser");  

      String nowLoginId = member.getId();
      String other = ""; 

      int no = Integer.parseInt(request.getParameter("no"));
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
      request.setAttribute("messages", messages); 
      request.setAttribute("roomNumber", no);
      request.setAttribute("theOtherId", other); 
      request.setAttribute("pageTitle", "메세지상세보기");
      request.setAttribute("contentUrl", "/message/MessageDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);
      //      request.getRequestDispatcher("MessageDetail.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }       
  }
}

