package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;

@WebServlet("/seller/detail")
public class SellerDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SellerDao sellerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String page = "";

    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/drinker/login/menu");
      return;
    }
    try {
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      String id = request.getParameter("id");
      Seller seller = sellerDao.findById(id);

      if (seller == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }
      request.setAttribute("seller", seller);

      if (member.getAuthority() == 8) {
        page = "SellerUpdate.jsp";
      } else {
        page = "SellerDetail.jsp";
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      page = "Error.jsp";
    }
    request.getRequestDispatcher(page).forward(request, response);
  }
}

