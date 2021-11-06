package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

@WebServlet("/main/findpwResult")
public class FindfwResultController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      Member member = memberDao.findByName(request.getParameter("name"));

      String id = member.getId();
      request.setAttribute("id", id);


      if ((member.getPhoneNumber().equals(request.getParameter("phoneOrEmail")) || (member.getEmail().equals(request.getParameter("phoneOrEmail")))
          && member.getId().equals(request.getParameter("id")))){

        request.getRequestDispatcher("FindpwResult.jsp").forward(request, response);

        member.setPassword(request.getParameter("password"));

        memberDao.update(member);
        sqlSession.commit();

      } else {
        request.getRequestDispatcher("FindError.jsp").forward(request, response);
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

