package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MessageDao;
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

    String nowLoginId = "admin";
    String other = request.getParameter("otherId");
    System.out.println(0);
    int no = Integer.parseInt(request.getParameter("roomNo"));
    Message message = new Message();
    System.out.println(00);
    message.setRoomNumber(no);
    System.out.println(000);
    message.setContent(request.getParameter("content"));
    System.out.println(0000);
    message.setId(nowLoginId);
    System.out.println(00000);
    message.setTheOtherId(other);

    try {
      System.out.println(1);
      messageDao.update(message);
      System.out.println(2);
      sqlSession.commit();
      System.out.println(3);
      response.setHeader("Refresh", "1;url=detail");

    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}






