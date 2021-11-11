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
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

@WebServlet("/main/FindpwError")
public class FindpwErrorController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member member = new Member();
    member.setPassword(request.getParameter("password"));
    member.setNumber(Integer.parseInt(request.getParameter("member_no")));

    try {

      if() {
        memberDao.update(member);
        sqlSession.commit();


        request.setAttribute("pageTitle", "로그인");
        request.setAttribute("contentUrl", "/main/Login.jsp");
        request.getRequestDispatcher("/template3.jsp").forward(request, response);

      } else {
        request.setAttribute("pageTitle", "비밀번호 변경 실패");
        request.setAttribute("contentUrl", "/main/FindpwError.jsp");
        request.getRequestDispatcher("/template3.jsp").forward(request, response);        
      }

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}