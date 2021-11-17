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
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      Member member = memberDao.findByName(request.getParameter("name"));

      if (member.getPhoneNumber().equals(request.getParameter("phoneOrEmail")) || (member.getEmail().equals(request.getParameter("phoneOrEmail")))) {
        String id = member.getId();
        request.setAttribute("id", id);

      } else{
        out.printf("<script>alert('일치하는 회원정보를 찾을 수 없습니다.'); location.href='../main/menu'</script>");
        out.flush();
      }

      request.setAttribute("pageTitle", "아이디찾기 결과");
      request.setAttribute("contentUrl", "/main/FindidResult.jsp");
      request.getRequestDispatcher("/template3.jsp").forward(request, response);

    }  catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}



