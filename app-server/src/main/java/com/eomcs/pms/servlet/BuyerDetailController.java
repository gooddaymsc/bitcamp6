package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;

//@WebServlet("/buyer/detail")
public class BuyerDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BuyerDao buyerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    buyerDao = (BuyerDao) 웹애플리케이션공용저장소.getAttribute("buyerDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {   
    String page = "";

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

      String id = request.getParameter("id");
      Buyer buyer = buyerDao.findById(id);

      if (buyer == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }
      request.setAttribute("buyer", buyer);

      if (member.getAuthority() == 8) {
        page = "/buyer/BuyerUpdate.jsp";
      } else {
        page = "/buyer/BuyerDetail.jsp";
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      page = "Error.jsp";
    }
    request.setAttribute("pageTitle", "회원(구매자)상세보기");
    request.setAttribute("contentUrl", page);
    request.getRequestDispatcher("/template1.jsp").forward(request, response);
    //    request.getRequestDispatcher(page).forward(request, response);
  }
}

