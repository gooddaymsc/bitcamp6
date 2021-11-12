package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class FindpwResultController extends HttpServlet {
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
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      Member member = memberDao.findById(request.getParameter("id"));


      if (((member.getPhoneNumber().equals(request.getParameter("phoneOrEmail")) || member.getEmail().equals(request.getParameter("phoneOrEmail"))))
          && (member.getId().equals(request.getParameter("id")))){

        request.setAttribute("member_no", member.getNumber());
        request.setAttribute("pageTitle", "비밀번호 변경");
        request.setAttribute("contentUrl", "/main/FindpwResult.jsp");
        request.getRequestDispatcher("/template3.jsp").forward(request, response);

      } else {
        out.printf("<script>alert('일치하는 회원정보를 찾을 수 없습니다.'); location.href='../main/menu'</script>");
        out.flush();
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
