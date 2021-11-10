package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Message;

@WebServlet("/message/add")
public class MessageAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MessageDao messageDao;
  SqlSession sqlSession;
  MemberDao memberDao;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    messageDao = (MessageDao) 웹애플리케이션공용저장소.getAttribute("messageDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      Member member = (Member) request.getSession(false).getAttribute("loginUser");
      String memberId = request.getParameter("theOtherId");

      if (memberDao.findById(memberId)==null) {
        out.printf("<script>alert('아이디를 다시 확인해주세요'); location.href='form' </script>");
        out.flush();
      }
      Collection<Message> messages = messageDao.findAll(member.getNumber());
      // 이미 상대id랑 주고 받은 대화가 있는가
      for (Message message : messages) {
        if (message.getTheOtherId().equals(memberId) ||
            message.getId().equals(memberId)) {
          request.setAttribute("roomNo", message.getRoomNumber());
          response.setHeader("Refresh", "1;url=detail?no="+message.getRoomNumber());
        }
      }
      // 없으면
      Message message = new Message();
      messageDao.insertRoomNo(message);

      message.setContent(request.getParameter("content"));
      message.setTheOtherId(request.getParameter("theOtherId"));

      message.setId(member.getId());
      messageDao.insert(message);

      sqlSession.commit();
      response.sendRedirect("detail?no="+message.getRoomNumber());
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}






