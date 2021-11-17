package com.eomcs.pms.web;

import java.io.IOException;
import java.io.PrintWriter;
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
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }

    try {
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      String other = request.getParameter("other");
      int no = Integer.parseInt(request.getParameter("no"));
      Message message = new Message();

      message.setRoomNumber(no);
      message.setContent(request.getParameter("content"));
      message.setId(member.getId());
      message.setTheOtherId(other);

      messageDao.update(message);
      response.sendRedirect("detail?no="+no);
    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}






