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
        System.out.println(1);
        System.out.println(request.getAttribute("pwCheck2"));       //check2 값 못가져옴. 확인 필요
        if(request.getAttribute("pwCheck2").equals("0")) {
          System.out.println(11);       
          request.setAttribute("member_no", member.getNumber());
          request.setAttribute("pageTitle", "비밀번호 변경");
          System.out.println(2);   
          request.setAttribute("contentUrl", "/main/FindpwResult.jsp");
          request.getRequestDispatcher("/template3.jsp").forward(request, response);
        } else {
          System.out.println(3);
          request.setAttribute("pageTitle", "비밀번호 변경 실패");
          request.setAttribute("contentUrl", "/main/FindpwError.jsp");
          request.getRequestDispatcher("/template3.jsp").forward(request, response);
          System.out.println(4);
        }
      } else {
        out.printf("<script>alert('일치하는 회원정보를 찾을 수 없습니다.'); location.href='Login.jsp'</script>");
        out.flush();
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}