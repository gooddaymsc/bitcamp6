package com.eomcs.pms.servlet;

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
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;

@WebServlet("/buyer/update")
public class BuyerUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  BuyerDao buyerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    buyerDao = (BuyerDao) 웹애플리케이션공용저장소.getAttribute("buyerDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }
    System.out.println("1");
    try {
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      String id = request.getParameter("id");
      Buyer buyer = buyerDao.findById(id);
      System.out.println("2");

      if (member.getId().equals(buyer.getMember().getId())) {

        buyer.getMember().setNickname(request.getParameter("nickname"));
        buyer.getMember().setPassword(request.getParameter("password"));
        buyer.getMember().setPhoto(request.getParameter("photo"));
        buyer.getMember().setPhoneNumber(request.getParameter("phoneNumber"));
        buyer.setZipcode(request.getParameter("zipcode"));
        buyer.setAddress(request.getParameter("address"));
        buyer.setDetailAddress(request.getParameter("detailAddress"));
        System.out.println("3");

      } else if (member.getAuthority() == 8) {
        buyer.getMember().setLevel(Integer.parseInt(request.getParameter("level")));
        System.out.println("4");
      }


      buyerDao.update(buyer);
      sqlSession.commit();

      request.setAttribute("pageTitle", "개인정보변경");
      request.setAttribute("contentUrl", "/main/Menu.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}


