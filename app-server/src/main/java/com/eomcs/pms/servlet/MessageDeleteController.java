package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MessageDao;

//@WebServlet("/message/delete")
public class MessageDeleteController  extends HttpServlet {
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
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try { 
      int no = Integer.parseInt(request.getParameter("no"));
      messageDao.delete(no);
      sqlSession.commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
} 







