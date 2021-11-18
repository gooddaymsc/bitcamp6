package com.eomcs.pms.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
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
import com.eomcs.pms.domain.MessageList;

@WebServlet("/message/list")
public class MessageListController extends HttpServlet{

  private static final long serialVersionUID = 1L;

  MessageDao messageDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    messageDao = (MessageDao) 웹애플리케이션공용저장소.getAttribute("messageDao");
  }

  //  public static int messageNumber = 1;

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
      int nowLoginNo = member.getNumber();
      String nowLoginId = member.getId();

      //      if (ClientApp.getLoginUser().isMessageUpdate()) {
      //        //메시지 알림 보내기
      //        //        memberPrompt.returnMessageUpdate(nowLoginId);
      //      }


      Collection<Message> messages = messageDao.findAll(nowLoginNo);

      MessageList messageList = new MessageList(); 
      messageList.setId(nowLoginId);
      messageList.setMessage((List<Message>) messages);

      request.setAttribute("messages", messages);
      request.setAttribute("pageTitle", "메세지목록");
      request.setAttribute("contentUrl", "/message/MessageList.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);
      //      request.getRequestDispatcher("MessageList.jsp").forward(request, response);

    }catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}


