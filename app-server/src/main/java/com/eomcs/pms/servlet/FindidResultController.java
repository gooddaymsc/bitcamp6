package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

@WebServlet("/main/findidResult")
public class FindidResultController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  } 

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      Member member = memberDao.findByName(request.getParameter("name"));

      if (member.getPhoneNumber().equals(request.getParameter("phoneOrEmail")) || (member.getEmail().equals(request.getParameter("phoneOrEmail")))) {
        String id = member.getId();
        request.setAttribute("id", id);

      }  else {
        String id = "해당하는 아이디가 없습니다";
        request.setAttribute("id", id);
      }

      request.getRequestDispatcher("FindidResult.jsp").forward(request, response);

    }  catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}



