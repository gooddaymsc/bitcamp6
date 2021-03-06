package com.eomcs.pms.web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

//@WebServlet("/main/login")
public class LoginController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    memberDao = (MemberDao) servletContext.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String id = request.getParameter("id");
      String password = request.getParameter("password");

      Member member = memberDao.findByIdAndPassword(id, password);

      if (member != null) {
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", member);

        System.out.println("Login ID : "+ member.getId());
        response.sendRedirect("./menu");
      } else {
        System.out.println("Error");
        request.setAttribute("pageTitle", "로그인실패");
        request.setAttribute("contentUrl", "/main/LoginError.jsp");
        request.getRequestDispatcher("/template3.jsp").forward(request, response);
      }

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}