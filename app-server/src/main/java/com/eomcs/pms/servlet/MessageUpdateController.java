package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Message;

@WebServlet("/message/update")
public class MessageUpdateController extends HttpServlet {
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
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/drinker/login/menu");
      return;
    }

    try {
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      String other = request.getParameter("theOtherId");
      System.out.println(request.getParameter("theOtherId"));
      System.out.println(request.getParameter("no"));
      int no = Integer.parseInt(request.getParameter("no"));
      Message message = new Message();
      System.out.println(00);
      message.setRoomNumber(no);
      System.out.println(000);
      message.setContent(request.getParameter("content"));
      System.out.println(0000);
      message.setId(member.getId());
      System.out.println(00000);
      message.setTheOtherId(other);


      messageDao.update(message);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=detail");

    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}






